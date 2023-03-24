package com.example.cojorca;

import com.example.cojorca.domain.User;
import com.example.cojorca.repository.CafeRepository;
import com.example.cojorca.repository.UserRepository;
import com.example.cojorca.service.CafeService;
import com.example.cojorca.service.CustomOAuth2UserService;
import com.example.cojorca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final CafeRepository cafeRepository;
    private final UserRepository userRepository;

    @Autowired
    public SpringConfig(CafeRepository cafeRepository, UserRepository userRepository) {
        this.cafeRepository = cafeRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public CustomOAuth2UserService customOAuth2UserService() {
        return new CustomOAuth2UserService(restTemplateBuilder(), userService());
    }

    @Bean
    public CafeService cafeService() {
        return new CafeService(cafeRepository);
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository);
    }

}
