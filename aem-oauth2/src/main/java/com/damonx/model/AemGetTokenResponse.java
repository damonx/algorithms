package com.damonx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AemGetTokenResponse
{

    public static AemGetTokenResponse ofEmpty()
    {
        return new AemGetTokenResponse();
    }

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private String expiresIn;

    public String getAccessToken()
    {
        return accessToken;
    }

    public String getExpiresIn()
    {
        return expiresIn;
    }

}
