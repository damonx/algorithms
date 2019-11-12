package com.spark.digital.oidcProxy.service;

import com.spark.digital.oidcProxy.models.AccessTokenResponse;
import com.spark.digital.oidcProxy.models.ConnectedClientInfo;
import com.spark.digital.oidcProxy.models.UserInfo;

import javax.validation.constraints.NotNull;

/**
 * Internal service which communicates with OpenAM OAuth2 REST endpoints to fetch authentication,
 * authorization and user info based on RFC 6749.
 *
 * @author Damon Xu
 *
 */
public interface OpenAmOauthService {

    /**
     * Fetches access token via the access token REST Endpoint exposed by OpenAM.
     *
     * @param customerUsername - username passed from the connected application
     * @param customerPassword - password passed from the connected application
     * @param connectedClientInfo - request payload containing info which is needed by OAuth2
     * @return {@link AccessTokenResponse} Response containing access_token, refresh_token
     */
    AccessTokenResponse fetchAccessToken(@NotNull final String customerUsername, @NotNull final String customerPassword,
            @NotNull final ConnectedClientInfo connectedClientInfo);

    /**
     * Fetches user info via the user info REST endpoint exposed by OpenAM.
     *
     * @param accessToken - access token
     * @return {@link UserInfo} user info payload returned from the REST endpoint
     */
    UserInfo fetchUserInfo(@NotNull final String accessToken);
}
