package com.example.cojorca.service;

import com.example.cojorca.DTO.EmailDTO;
import com.example.cojorca.DTO.UserInfoDTO;
import com.example.cojorca.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final RestTemplateBuilder restTemplateBuilder;
    private final UserService userService;

    @Autowired
    public CustomOAuth2UserService(RestTemplateBuilder restTemplateBuilder, UserService userService) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        HttpHeaders httpHeaders = createHeaders(userRequest.getAccessToken().getTokenValue());

        UserInfoDTO userInfoDTO = getUserInfo(httpHeaders);
        User user = new User(userInfoDTO.getLogin(), userInfoDTO.getName(), userInfoDTO.getHtml_url(), formattedDate);
        setEmailIfPresent(httpHeaders, user);

        System.out.println(user.getLoginId());
        System.out.println("회원 등록");
        userService.registerUser(user);


        return new DefaultOAuth2User(AuthorityUtils.createAuthorityList("ROLE_USER"), oAuth2User.getAttributes(), "name");
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        httpHeaders.set("Accept", "application/vnd.github+json");
        return httpHeaders;
    }

    private UserInfoDTO getUserInfo(HttpHeaders httpHeaders) {
        RequestEntity<Void> infoRequest = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create("https://api.github.com/user"));
        ResponseEntity<UserInfoDTO> infoResponse = restTemplateBuilder.build().exchange(infoRequest, UserInfoDTO.class);
        return infoResponse.getBody();
    }

    private void setEmailIfPresent(HttpHeaders httpHeaders, User user) {
        RequestEntity<Void> emailRequest = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create("https://api.github.com/user/emails"));
        ResponseEntity<EmailDTO[]> emailResponse = restTemplateBuilder.build().exchange(emailRequest, EmailDTO[].class);
        Optional<String> email = Arrays.stream(emailResponse.getBody())
                .filter(e -> e.isPrimary() && "public".equals(e.getVisibility()))
                .map(EmailDTO::getEmail)
                .findFirst();

        email.ifPresent(user::setEmail);
    }

}