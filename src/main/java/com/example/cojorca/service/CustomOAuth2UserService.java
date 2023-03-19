package com.example.cojorca.service;

import com.example.cojorca.controller.EmailDTO;
import com.example.cojorca.controller.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public CustomOAuth2UserService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + userRequest.getAccessToken().getTokenValue());
        httpHeaders.set("Accept", "application/vnd.github+json");


        RequestEntity<Void> infoRequest = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create("https://api.github.com/user"));
        ResponseEntity<UserInfoDTO> infoResponse = restTemplateBuilder.build().exchange(infoRequest, UserInfoDTO.class);
        UserInfoDTO userInfoDTO = infoResponse.getBody();

        RequestEntity<Void> emailRequest = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create("https://api.github.com/user/emails"));
        ResponseEntity<EmailDTO[]> emailResponse = restTemplateBuilder.build().exchange(emailRequest, EmailDTO[].class);
        Optional<String> email = Arrays.stream(emailResponse.getBody())
                .filter(e -> e.isPrimary() && "public".equals(e.getVisibility()))
                .map(EmailDTO::getEmail)
                .findFirst();

        email.ifPresent(userInfoDTO::setEmail);

        System.out.println(userInfoDTO.getName());

        return new DefaultOAuth2User(
                AuthorityUtils.createAuthorityList("ROLE_USER"),
                oAuth2User.getAttributes(),
                "name" // 사용자 이름 속성
        );
    }
}