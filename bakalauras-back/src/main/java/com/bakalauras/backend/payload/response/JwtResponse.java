package com.bakalauras.backend.payload.response;

import lombok.Data;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private final String role;

	public JwtResponse(String accessToken, Long id, String username, String role) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.role = role;
	}

}
