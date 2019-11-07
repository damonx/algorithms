package com.spark.digital.oidcProxy.controllers;

import com.spark.digital.oidcProxy.entity.Client;
import com.spark.digital.oidcProxy.models.AuthenticationResponse;
import com.spark.digital.oidcProxy.repository.ClientRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class LoginController {

    @Autowired
    private ClientRepository clientRepo;

    @CrossOrigin(origins = "*")
    @ApiOperation(value = "User login with OpenAM identitiy", response = ResponseEntity.class)
    @RequestMapping(method = RequestMethod.POST, value = "/identity/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully logged in"),
            @ApiResponse(code = 400, message = "Bad request, country code missing"),
            @ApiResponse(code = 500, message = "Internal Server Errors")
    })
    public ResponseEntity<AuthenticationResponse> tokenInfo(
            @RequestParam(value = "username") final String username,
            @RequestParam(value = "password") final String password,
            @RequestParam(value = "client") final String client,
            @RequestParam(value = "redirectUri") final String redirectUri) {

        final String url = "http://localhost:8083/openam/oauth2/access_token?";

        //Get Client credentials from DB
        final Client clientDetails = clientRepo.findClientByName(client);

        // Set auth header for requesting client
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientDetails.getUsername(), clientDetails.getSecret());

        final HttpEntity<Object> entity = new HttpEntity<>(headers);

        // Query parameters
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("grant_type", "password")
                .queryParam("username", username)
                .queryParam("password", password)
                .queryParam("scope", "openid profile");

        System.out.println(builder.buildAndExpand().toUri());

        final RestTemplate restTemplate = new RestTemplate();

        final ResponseEntity<AuthenticationResponse> response =
                restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.POST, entity, AuthenticationResponse.class);

        response.getHeaders().getLocation();
        response.getStatusCode();

        return response;
    }
}
