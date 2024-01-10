package com.bezkoder.spring.hibernate.onetomany.model;

public class JwtResponse {
    String token;
    private String type = "Bearer";
    int userid ;
    String username ;

    public JwtResponse() {
    }

    public JwtResponse(String token,int userid,String username) {
        this.token = token;
        this.userid = userid ;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public String getTokenType() {
        return type;
     }

      public void setTokenType(String tokenType) {
        this.type = tokenType;
     }
    
    
    public int getUserId() {
		return userid;
	}

	public void setUserId(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
