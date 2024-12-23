package com.lms.auth;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper;
    private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());


    public JwtAuthorizationFilter(JwtUtil jwtUtil, ObjectMapper mapper) {
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }
    
    /**   
     * Custom filter to handle JWT authentication. Extracts the JWT token from the incoming request,
     * validates the token, and sets the authentication in the SecurityContextHolder if the token is valid.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        Map<String, Object> errorDetails = new HashMap<>();
        try {
            String accessToken = jwtUtil.resolveToken(request);
            if (accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }
            logger.info("token:" + accessToken);

            JwtTokenDetails jwtTokenDetails = jwtUtil.extractTokenDetails(accessToken);
            logger.info("jwtTokenDetails: " + accessToken);
            Claims claims = jwtUtil.resolveClaims(request);

            if (claims != null && jwtUtil.validateClaims(claims)) {
                String username = claims.getSubject();
                logger.info("user name: " + username);

                JwtAuthentication jwtAuthentication = new JwtAuthentication(username, jwtTokenDetails, null);
                SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                // Log authentication details
                logger.info("Authentication details: " + authentication);
            }

        } catch (Exception e) {
        	e.printStackTrace();
            errorDetails.put("message", "Authentication Error");
            errorDetails.put("details", e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), errorDetails);
        }
        filterChain.doFilter(request, response);
    }

	
}