//package com.example.cojorca.service;
//
//import com.example.cojorca.controller.EmailDTO;
//import com.example.cojorca.controller.UserInfoDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//
//import java.net.URI;
//import java.util.Arrays;
//import java.util.Optional;
//
//public class UserInfoService {
//    private final RestTemplateBuilder restTemplateBuilder;
//
//    @Autowired
//    public UserInfoService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplateBuilder = restTemplateBuilder;
//    }
//    public UserInfoDTO getUserInfo(String token){
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization", "Bearer " + token);
//        httpHeaders.set("Accept", "application/vnd.github+json");
//
//        RequestEntity<Void> infoRequest = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create("https://api.github.com/user"));
//        ResponseEntity<UserInfoDTO> infoResponse = restTemplateBuilder.build().exchange(infoRequest, UserInfoDTO.class);
//
//        UserInfoDTO userInfoDTO = infoResponse.getBody();
//
//        //이메일 정보를 요청
//        RequestEntity<Void> emailRequest = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create("https://api.github.com/user/emails"));
//        ResponseEntity<EmailDTO[]> emailResponse = restTemplateBuilder.build().exchange(emailRequest, EmailDTO[].class);
//
//        Optional<String> email = Arrays.stream(emailResponse.getBody())
//                .filter(e -> e.isPrimary() && "public".equals(e.getVisibility()))
//                .map(EmailDTO::getEmail)
//                .findFirst();
//
//        email.ifPresent(userInfoDTO::setEmail);
//
//
//        return userInfoDTO;
//    }
//}
