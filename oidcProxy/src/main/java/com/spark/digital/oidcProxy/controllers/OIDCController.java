package com.spark.digital.oidcProxy.controllers;

import com.spark.digital.oidcProxy.models.AccessTokenResponse;
import com.spark.digital.oidcProxy.models.ConnectedClientInfo;
import com.spark.digital.oidcProxy.models.UserInfo;
import com.spark.digital.oidcProxy.service.OpenAmOauthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class OIDCController {

    @Autowired
    private OpenAmOauthService openAmOauthService;

    // /* Authorization endpoint defined in RFC 6749, used to obtain an Authorization Grant from the
    // Resource Owner: Example: */
    // @ApiOperation(value = "Request Authroisation code endpoint", response = ResponseEntity.class)
    // @ApiResponses(value = {
    // @ApiResponse(code = 200, message = "Successfully fetched authorisation code"),
    // @ApiResponse(code = 400, message = "Bad request, required parameters are missing"),
    // @ApiResponse(code = 500, message = "Internal Server Errors")
    // })
    // @RequestMapping(method = RequestMethod.POST, value = "/oidc/authorise")
    // public void authorise(
    // @RequestParam(value = "response_type", required = true) final String responseType,
    // @RequestParam(value = "callbackUri", required = true) final String callbackUri,
    // @RequestParam(value = "state", required = true) final String state) {
    //
    // // TODO: Move them to a configuration file
    // final String url = "http://localhost:8083/openam/oauth2/authorize?";
    // final String clientId = "";
    // final String clientSecret = "";
    // final String scope = "";
    //
    //
    // final HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    //
    // final HttpEntity entity = new HttpEntity(headers);
    //
    // // Query parameters
    // final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
    // // Add query parameter
    // .queryParam("client_id", clientId)
    // .queryParam("response_type", responseType)
    // .queryParam("client_secret", clientSecret)
    // .queryParam("redirect_uri", callbackUri)
    // .queryParam("scope", scope)
    // .queryParam("state", clientSecret);
    //
    // System.out.println(builder.buildAndExpand().toUri());
    //
    // final RestTemplate restTemplate = new RestTemplate();
    //
    // restTemplate.exchange(builder.buildAndExpand().toUri() , HttpMethod.GET,
    // entity, AuthenticationResponse.class);
    //
    // }

    /*Token Endpoint defined in RFC 6749, used to obtain an access token from the authorization server Example:*/
    @ApiOperation(value = "Request access token endpoint according to RFC 6749", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched access token"),
            @ApiResponse(code = 400, message = "Bad request, required parameters are missing"),
            @ApiResponse(code = 500, message = "Internal Server Errors")
    })
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/oidc/accessToken")
    public AccessTokenResponse accessToken(
            @RequestParam(value = "customerUsername", required = true) final String customerUsername,
            @RequestParam(value = "customerPassword", required = true) final String customerPassword,
            @RequestBody final ConnectedClientInfo connectedClientInfo) {

        return openAmOauthService.fetchAccessToken(customerUsername, customerPassword, connectedClientInfo);
    }

    /* In addition, authorized clients can access end user information through the OpenID Connect 1.0 Userinfo_endpoint*/
    @ApiOperation(value = "Request user profile info endpoint", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched user info"),
            @ApiResponse(code = 400, message = "Bad request, required parameters are missing"),
            @ApiResponse(code = 500, message = "Internal Server Errors")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/oidc/userInfo")
    public UserInfo userInfo(@RequestParam(value = "accessToken", required = true) final String accessToken) {
        return openAmOauthService.fetchUserInfo(accessToken);
    }
}
