package co.nz.spark.oauthrestapi;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import co.nz.spark.oauthrestapi.controller.GreetingController;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = OauthrestapiApplication.class)
public class GreetingControllerTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @InjectMocks
    GreetingController controller;

    private MockMvc mvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void greetingUnauthorized() throws Exception {
        mvc.perform(get("/greeting")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.error", is("unauthorized")));
    }

    private String getAccessToken(final String username, final String password) throws Exception {
        final String authorization = "Basic "
                + new String(Base64Utils.encode("clientapp:123456".getBytes()));
        final String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";

        final String content = mvc
                .perform(
                        post("/oauth/token")
                        .header("Authorization", authorization)
                        .contentType(
                                MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", username)
                        .param("password", password)
                        .param("grant_type", "password")
                        .param("scope", "read write")
                        .param("client_id", "clientapp")
                        .param("client_secret", "123456"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.access_token", is(notNullValue())))
                .andExpect(jsonPath("$.token_type", is(equalTo("bearer"))))
                .andExpect(jsonPath("$.refresh_token", is(notNullValue())))
                .andExpect(jsonPath("$.expires_in", is(greaterThan(4000))))
                .andExpect(jsonPath("$.scope", is(equalTo("read write"))))
                .andReturn().getResponse().getContentAsString();

        return content.substring(17, 53);
    }

    @Test
    public void greetingAuthorized() throws Exception {
        final String accessToken = getAccessToken("damonx", "spring");

        mvc.perform(get("/greeting")
                .header("Authorization", "Bearer " + accessToken))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.content", is("Hi there, Damon!")));

        mvc.perform(get("/greeting")
                .header("Authorization", "Bearer " + accessToken))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(2)))
        .andExpect(jsonPath("$.content", is("Hi there, Damon!")));

        mvc.perform(get("/greeting")
                .header("Authorization", "Bearer " + accessToken))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(3)))
        .andExpect(jsonPath("$.content", is("Hi there, Damon!")));
    }

    @Test
    public void usersEndpointAuthorized() throws Exception {
        mvc.perform(get("/users")
                .header("Authorization", "Bearer " + getAccessToken("damonx", "spring")))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void usersEndpointAccessDenied() throws Exception {
        mvc.perform(get("/users")
                .header("Authorization", "Bearer " + getAccessToken("craig", "spring")))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }
}
