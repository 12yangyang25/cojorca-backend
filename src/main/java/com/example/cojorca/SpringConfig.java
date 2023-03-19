package com.example.cojorca;

import com.example.cojorca.service.UserInfoService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilder(){
        return new RestTemplateBuilder();
    }
    @Bean
    public UserInfoService userInfoService(){
        return new UserInfoService(restTemplateBuilder());
    }

    public OAuth2AuthorizedClientService clientService(){
        return new OAuth2AuthorizedClientService() {
            @Override
            public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
                return null;
            }

            @Override
            public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {

            }

            @Override
            public void removeAuthorizedClient(String clientRegistrationId, String principalName) {

            }
        };
    }


}
