package com.lms.auth;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;



public class JwtAuthentication extends AbstractAuthenticationToken {

    private String principal;
    private JwtTokenDetails jwtTokenDetails;

    public JwtAuthentication(String principal, JwtTokenDetails jwtTokenDetails, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.jwtTokenDetails = jwtTokenDetails;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public JwtTokenDetails getJwtTokenDetails() {
        return jwtTokenDetails;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public void setJwtTokenDetails(JwtTokenDetails jwtTokenDetails) {
        this.jwtTokenDetails = jwtTokenDetails;
    }
}
