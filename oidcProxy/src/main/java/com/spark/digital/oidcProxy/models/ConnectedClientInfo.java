package com.spark.digital.oidcProxy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectedClientInfo {
    @JsonProperty("client_id")
    String clientId;

    @JsonProperty("client_secret")
    String clientSecret;

    @JsonProperty("client_username")
    String clientUsername;

    @JsonProperty("client_password")
    String clientPassword;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(final String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(final String clientPassword) {
        this.clientPassword = clientPassword;
    }
}
