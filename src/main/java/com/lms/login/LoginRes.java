package com.lms.login;

public class LoginRes {
	private String username;
    private String accessToken;

    public LoginRes(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
