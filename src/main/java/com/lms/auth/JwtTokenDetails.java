package com.lms.auth;

import java.security.Permission;
import java.util.Collection;
import java.util.UUID;





public class JwtTokenDetails {
	
	private String username; 
    private UUID userId;
   // private String code;
//    private Collection<Permission> permissions;
    
    public JwtTokenDetails(String subject, UUID userId) {
        this.username = subject;
        this.userId = userId;
       // this.code = code;
//        this.permissions = permissions;
    }

	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}

//	public Collection<Permission> getPermissions() {
//		return permissions;
//	}
//
//	public void setPermissions(Collection<Permission> permissions) {
//		this.permissions = permissions;
//	}

}
