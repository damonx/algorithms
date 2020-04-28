package com.damonx.oauth.service;

import com.damonx.model.AemGetTokenResponse;
import com.damonx.model.AemUserProfile;

import org.springframework.stereotype.Service;

@Service
public interface AemOauthService
{
    AemGetTokenResponse getAemAccessToken(final String authorisationCode);

    AemUserProfile lookupUser(final String accessToken);
}
