package com.spark.digital.oidcProxy.controllers;

import com.spark.digital.oidcProxy.models.AccessTokenResponse;
import com.spark.digital.oidcProxy.models.ConnectedClientInfo;
import com.spark.digital.oidcProxy.service.OpenAmOauthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class LoginController {

    @Autowired
    private OpenAmOauthService openAmOauthService;

    @CrossOrigin(origins = "*")
    @ApiOperation(value = "User login with OpenAM identitiy", response = ResponseEntity.class)
    @RequestMapping(method = RequestMethod.POST, value = "/identity/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully logged in"),
            @ApiResponse(code = 400, message = "Bad request, required parameters are missing"),
            @ApiResponse(code = 500, message = "Internal Server Errors")
    })
    public AccessTokenResponse tokenInfo(
            @RequestParam(value = "username", required = true) final String username,
            @RequestParam(value = "password", required = true) final String password,
            @RequestBody final ConnectedClientInfo connectedClientInfo) {

        return openAmOauthService.fetchAccessToken(username, password, connectedClientInfo);
    }
}
