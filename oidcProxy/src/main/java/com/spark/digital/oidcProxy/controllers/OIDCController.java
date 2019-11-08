package com.spark.digital.oidcProxy.controllers;

import com.spark.digital.oidcProxy.models.AuthenticationResponse;
import com.spark.digital.oidcProxy.models.UserInfo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
    @ApiOperation(value = "Request Authroisation code endpoint", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched authorisation code"),
            @ApiResponse(code = 400, message = "Bad request, required parameters are missing"),
            @ApiResponse(code = 500, message = "Internal Server Errors")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/oidc/authorise")
    public void authorise(
            @RequestParam(value = "response_type", required = true) final String responseType,
            @RequestParam(value = "callbackUri", required = true) final String callbackUri,
            @RequestParam(value = "state", required = true) final String state) {

        // TODO: Move them to a configuration file
        final String url = "http://localhost:8083/openam/oauth2/authorize?";
        final String clientId = "";
        final String clientSecret = "";
        final String scope = "";


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
    @ApiOperation(value = "Request access token endpoint", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched access token"),
            @ApiResponse(code = 400, message = "Bad request, required parameters are missing"),
            @ApiResponse(code = 500, message = "Internal Server Errors")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/oidc/accessToken")
    public ResponseEntity<AuthenticationResponse> accessToken(
            @RequestParam(value = "customerUsername", required = true) final String customerUsername,
            @RequestParam(value = "customerPassword", required = true) final String customerPassword) {

        // TODO: Move them to configurations.
        final String url = "http://localhost:8083/openam/oauth2/access_token?";
        final String clientUsername = "";
        final String clientPassword = "";
        final String scope = "openid profile";
        final String grantType = "password";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientUsername, clientPassword);

        final HttpEntity entity = new HttpEntity(headers);

        // Query parameters
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("grant_type", grantType)
                .queryParam("username", customerUsername)
                .queryParam("password", customerPassword)
                .queryParam("scope", scope);

        // System.out.println(builder.buildAndExpand().toUri());

        final RestTemplate restTemplate = new RestTemplate();

        final ResponseEntity<AuthenticationResponse> response =
                restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.POST, entity, AuthenticationResponse.class);

        response.getHeaders().getLocation();
        response.getStatusCode();

        return response;
    }

    /* In addition, authorized clients can access end user information through the OpenID Connect 1.0 Userinfo_endpoint*/
    @ApiOperation(value = "Request user profile info endpoint", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched user info"),
            @ApiResponse(code = 400, message = "Bad request, required parameters are missing"),
            @ApiResponse(code = 500, message = "Internal Server Errors")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/oidc/userInfo")
    public ResponseEntity<UserInfo> userInfo(@RequestParam(value = "accessToken", required = true) final String accessToken ) {
        // System.out.println(("Request made to userInfo service"));

        final String url = "http://localhost:8083/openam/oauth2/userinfo?";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);

        final HttpEntity entity = new HttpEntity(headers);

        // Query parameters
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        final RestTemplate restTemplate = new RestTemplate();

        final ResponseEntity<UserInfo> response = restTemplate.exchange(builder.buildAndExpand().toUri() , HttpMethod.GET, entity, UserInfo.class);

        response.getHeaders().getLocation();
        response.getStatusCode();

        return response;
    }
}
