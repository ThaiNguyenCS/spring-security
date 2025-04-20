package com.spring_boot_template.demo.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value("${client.redirect-url}")
    public String redirectUrl;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        System.out.println("OAuth2 login success: " + authentication.getName());

        var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        System.out.println("OAuth2 user: " + oauthUser.getAttributes());
        //TODO: add logic here
        response.sendRedirect(redirectUrl);
    }
}