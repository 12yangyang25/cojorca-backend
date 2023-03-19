package com.example.cojorca.controller;


import com.example.cojorca.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    private final UserInfoService userInfoService;
    private final OAuth2AuthorizedClientService clientService;

    @Autowired
    public AuthController(UserInfoService userInfoService, OAuth2AuthorizedClientService clientService) {
        this.userInfoService = userInfoService;
        this.clientService = clientService;
    }

    @GetMapping("/auth/callback")
    public String OAuthLogin(Authentication authentication){
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
        String token = client.getAccessToken().getTokenValue();

        UserInfoDTO userInfoDTO =userInfoService.getUserInfo(token);
        System.out.println(userInfoDTO.getName());
        System.out.println(userInfoDTO.getEmail());

        return "/";
    }
}
