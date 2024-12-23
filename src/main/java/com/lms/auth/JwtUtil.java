package com.lms.auth;

import java.security.Permission;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.lms.user.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {

    private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());
    private final String secret_key = "lmsaVeryLongAndSecureKeyThatYouShouldReplacelmsaVeryLongAndSecureKeyThatYouShouldReplace";
    private final int accessTokenValidity = 120; // 2 hours in minutes
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    String encodedString = Base64.getEncoder().encodeToString(secret_key.getBytes());

    public JwtUtil() {
    }
    
    public String createToken(User user) {
    	

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("id", user.getId().toString());
        
       // claims.put("role", user.getRole());

        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(tokenCreateTime)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, encodedString)
                .compact();

        return token;
    }


    private Claims parseJwtClaims(String token) {
        return Jwts.parser().setSigningKey(encodedString).parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
//        logger.info("bearer Token : " +bearerToken);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
        	return  bearerToken.substring(7);    
        }
        return null;
    }
    
    public JwtTokenDetails extractTokenDetails(String token) {
        Claims claims = Jwts.parser().setSigningKey(encodedString).parseClaimsJws(token).getBody();

        List<Map<String, Object>> roles = (List<Map<String, Object>>) claims.get("role");

        String subject = claims.getSubject();
        String userId = (String) claims.get("id"); // Change the type to String
      
      
        Long expiration = claims.getExpiration().getTime();
		return new JwtTokenDetails(subject, UUID.fromString(userId));
    }



    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }

//    public Collection<? extends GrantedAuthority> createAuthoritiesFromTokenDetails(JwtTokenDetails jwtTokenDetails) {
//        // Extract permissions from JwtTokenDetails
//        Collection<Permission> permissions = jwtTokenDetails;
//        logger.info("Permissions extracted from JwtTokenDetails: " + permissions);
//
//        // Extract only the 'name' property from permissions and convert to authorities
//        Collection<? extends GrantedAuthority> authorities = permissions.stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
//                .collect(Collectors.toList());
//        
//        logger.info("Authorities created from permissions: " + authorities);
//
//        return authorities;
//    }


	
}