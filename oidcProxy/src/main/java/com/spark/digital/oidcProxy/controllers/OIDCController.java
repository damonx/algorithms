package com.spark.digital.oidcProxy.controllers;

import com.spark.digital.oidcProxy.models.AuthenticationResponse;
import com.spark.digital.oidcProxy.models.UserInfo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class OIDCController {

    /* Authorization endpoint defined in RFC 6749, used to obtain an Authorization Grant from the Resource Owner: Example: */
    @RequestMapping(method = RequestMethod.POST, value = "/oidc/authorise")
    public void authorise(
            @RequestParam(value = "clientId") final String clientId,
            @RequestParam(value = "response_type") final String responseType,
            @RequestParam(value = "clientSecret") final String clientSecret,
            @RequestParam(value = "callbackUri") final String callbackUri,
            @RequestParam(value = "scope") final String scope,
            @RequestParam(value = "state") final String state)   {


        final String url = "http://localhost:8083/openam/oauth2/authorize?";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final HttpEntity entity = new HttpEntity(headers);

        // Query parameters
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("client_id", clientId)
                .queryParam("response_type", responseType)
                .queryParam("client_secret", clientSecret)
                .queryParam("redirect_uri", callbackUri)
                .queryParam("scope", scope)
                .queryParam("state", clientSecret);

        System.out.println(builder.buildAndExpand().toUri());

        final RestTemplate restTemplate = new RestTemplate();

        restTemplate.exchange(builder.buildAndExpand().toUri() , HttpMethod.GET,
                entity, AuthenticationResponse.class);

    }

    /*Token Endpoint defined in RFC 6749, used to obtain an access token from the authorization server Example:*/
    @RequestMapping(method = RequestMethod.POST, value = "/oidc/accessToken")
    public ResponseEntity<AuthenticationResponse> accessToken(
            @RequestParam(value = "grantType") final String grantType,
            @RequestParam(value = "clientUsername") final String username,
            @RequestParam(value = "clientPassword") final String password,
            @RequestParam(value = "scope") final String scope,
            @RequestParam(value = "customerUsername") final String customerUsername,
            @RequestParam(value = "customerPassword") final String customerPassword ) {

        final String url = "http://localhost:8083/openam/oauth2/access_token?";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(username, password);

        final HttpEntity entity = new HttpEntity(headers);

        // Query parameters
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("grant_type", grantType)
                .queryParam("username", customerUsername)
                .queryParam("password", customerPassword)
                .queryParam("scope", scope);

        System.out.println(builder.buildAndExpand().toUri());

        final RestTemplate restTemplate = new RestTemplate();

        final ResponseEntity<AuthenticationResponse> response =
                restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.POST, entity, AuthenticationResponse.class);

        response.getHeaders().getLocation();
        response.getStatusCode();

        return response;
    }

    /*Endpoint not defined in RFC 6749, used to validate tokens, and to retrieve information such as scopes
      Given an Access Token, a Resource Server can perform an HTTP GET on /oauth2/tokeninfo?access_token=token-id to retrieve a JSON object indicating token_type, expires_in, scope, and the access_token ID.*/
    @RequestMapping(method = RequestMethod.GET, value = "/oidc/tokenInfo")
    public void tokenInfo(@RequestParam(value = "accessToken") final String accessToken ) {
        System.out.println(("Request made to token info service"));
    }

    /* In addition, authorized clients can access end user information through the OpenID Connect 1.0 Userinfo_endpoint*/
    @RequestMapping(method = RequestMethod.GET, value = "/oidc/userInfo")
    public ResponseEntity<UserInfo> userInfo(@RequestParam(value = "accessToken") final String accessToken ) {
        System.out.println(("Request made to userInfo service"));

        final String url = "http://localhost:8083/openam/oauth2/userinfo?";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);

        final HttpEntity entity = new HttpEntity(headers);

        // Query parameters
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        System.out.println(builder.buildAndExpand().toUri());

        final RestTemplate restTemplate = new RestTemplate();

        final ResponseEntity<UserInfo> response = restTemplate.exchange(builder.buildAndExpand().toUri() , HttpMethod.GET, entity, UserInfo.class);

        response.getHeaders().getLocation();
        response.getStatusCode();

        return response;
    }
}
