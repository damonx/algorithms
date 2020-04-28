package com.damonx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.damonx.model.AemUserProfile;
import com.damonx.oauth.service.AemOauthService;

@Controller
public class OAuth2Controller {
    @Autowired
    private AemOauthService aemOauthService;

    @GetMapping("/test")
    @ResponseBody
    public AemUserProfile printAemUserProfile(@RequestParam(name="code", required=true) String code, @RequestParam(name="state", required=true) String state) {
         final String accessToken = aemOauthService.getAemAccessToken(code).getAccessToken();
         return aemOauthService.lookupUser(accessToken);
    }
}
