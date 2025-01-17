package com.victorpalha.aspop_spring.security;

import com.victorpalha.aspop_spring.providers.JWTProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JWTProvider jwtProvider;
    public SecurityConfig(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    // Public routes
                    auth.requestMatchers("/api/member/signup").permitAll();
                    // Add auth process to all others
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(new JWTAuthenticationFilter(jwtProvider), BasicAuthenticationFilter.class);
        return http.build();
    }
}
