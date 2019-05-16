package com.damonx.oauth.service;

import org.springframework.stereotype.Service;

import com.damonx.model.AemGetTokenResponse;
import com.damonx.model.AemUserProfile;

@Service
public interface AemOauthService {
   public AemGetTokenResponse getAemAccessToken(final String authorisationCode);
   public AemUserProfile lookupUser(final String accessToken);
}
