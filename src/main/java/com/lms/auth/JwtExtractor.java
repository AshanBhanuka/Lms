package com.lms.auth;

import java.util.UUID;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;



@Component
public class JwtExtractor {

    public static JwtTokenDetails extraction(Authentication authentication) {
        if (authentication instanceof JwtAuthentication) {
            JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;
            return jwtAuthentication.getJwtTokenDetails();
        }
        throw new AccessDeniedException("User not authenticated");
    }
    
    public static UUID userid(Authentication authentication) {
        if (authentication instanceof JwtAuthentication) {
            JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;
            return jwtAuthentication.getJwtTokenDetails().getUserId();
        }
        throw new AccessDeniedException("User not authenticated");    
     }
}