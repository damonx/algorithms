package com.spark.digital.oidcProxy.service.impl;

import com.spark.digital.oidcProxy.models.AccessTokenResponse;
import com.spark.digital.oidcProxy.models.ConnectedClientInfo;
import com.spark.digital.oidcProxy.models.UserInfo;
import com.spark.digital.oidcProxy.service.OpenAmOauthService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;

@Service
public class OpenAmOauthServiceImpl implements OpenAmOauthService {

    @Override
    public AccessTokenResponse fetchAccessToken(@NotNull final String customerUsername, @NotNull final String customerPassword,
            @NotNull final ConnectedClientInfo connectedClientInfo) {
        // TODO: Move them to configurations.
        final String url = "http://localhost:8083/openam/oauth2/access_token?";
        final String clientUsername = StringUtils.defaultIfBlank(connectedClientInfo.getClientUsername(), StringUtils.EMPTY);
        final String clientPassword = StringUtils.defaultIfBlank(connectedClientInfo.getClientPassword(), StringUtils.EMPTY);;
        final String scope = "openid profile";
        final String grantType = "password";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientUsername, clientPassword);

        final HttpEntity<Object> entity = new HttpEntity<>(headers);

        // Query parameters
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                // Add query parameter
                .queryParam("grant_type", grantType)
                .queryParam("username", customerUsername)
                .queryParam("password", customerPassword)
                .queryParam("scope", scope);

        final RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<AccessTokenResponse> response = null;
        try {
            response = restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.POST, entity, AccessTokenResponse.class);
        } catch (final RestClientException e) {
            // TODO throw Spark's internal exception.
        }
        return response.getBody();
    }

    @Override
    public UserInfo fetchUserInfo(@NotNull final String accessToken) {
        final String url = "http://localhost:8083/openam/oauth2/userinfo?";

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);

        final HttpEntity<Object> entity = new HttpEntity<>(headers);

        // Query parameters
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        final RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<UserInfo> response = null;
        try {
            response = restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, entity, UserInfo.class);
        } catch (final RestClientException e) {
            // TODO throw Spark's internal exception
        }
        return response.getBody();
    }
}
