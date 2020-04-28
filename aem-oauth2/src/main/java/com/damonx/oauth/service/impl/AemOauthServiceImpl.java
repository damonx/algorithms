package com.damonx.oauth.service.impl;

import com.damonx.model.AemGetTokenResponse;
import com.damonx.model.AemUserProfile;
import com.damonx.oauth.service.AemOauthService;
import com.damonx.util.HttpClientBuilder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AemOauthServiceImpl implements AemOauthService
{

    @Value("${aem.oauth.accesstoken.url}")
    private String accessTokenRestEndpoint;

    @Value("${aem.oauth.profile.url}")
    private String userProfileUrl;

    @Value("${aem.oauth.grant.type}")
    private String grantType;

    @Value("${aem.redirect.uri}")
    private String redirectUri;

    @Value("${aem.client.id}")
    private String clientId;

    @Value("${aem.client.secret}")
    private String clientSeceret;

    private static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";

    @Override
    public AemGetTokenResponse getAemAccessToken(final String authorisationCode)
    {
        try {
            final CloseableHttpClient httpClient = new HttpClientBuilder().build();
            final HttpPost httpPost = new HttpPost(accessTokenRestEndpoint);
            final ObjectMapper mapper = new ObjectMapper();

            final UrlEncodedFormEntity postEntity = buildHttpPostEntity(authorisationCode);
            httpPost.setEntity(postEntity);

            final CloseableHttpResponse response = httpClient.execute(httpPost);
            final String responseStr = EntityUtils.toString(response.getEntity(), DEFAULT_CHARACTER_ENCODING);
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(responseStr, AemGetTokenResponse.class);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return AemGetTokenResponse.ofEmpty();
    }

    @Override
    public AemUserProfile lookupUser(final String accessToken)
    {
        final CloseableHttpClient httpClient = new HttpClientBuilder().build();

        final HttpGet httpGet = new HttpGet(userProfileUrl);
        httpGet.addHeader("Authorization", "Bearer " + accessToken);
        httpGet.addHeader("Content-Type", "application/json");
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final CloseableHttpResponse response = httpClient.execute(httpGet);
            final String responseStr = EntityUtils.toString(response.getEntity(), DEFAULT_CHARACTER_ENCODING);
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(responseStr, AemUserProfile.class);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return AemUserProfile.ofEmpty();
    }


    private UrlEncodedFormEntity buildHttpPostEntity(final String authorisationCode) throws UnsupportedEncodingException
    {
        final List<BasicNameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("grant_type", grantType));
        formparams.add(new BasicNameValuePair("client_id", clientId));
        formparams.add(new BasicNameValuePair("client_secret", clientSeceret));
        formparams.add(new BasicNameValuePair("redirect_uri", redirectUri));
        formparams.add(new BasicNameValuePair("code", authorisationCode));
        return new UrlEncodedFormEntity(formparams, DEFAULT_CHARACTER_ENCODING);
    }
}
