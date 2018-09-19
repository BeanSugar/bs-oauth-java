package org.scriptonbasestar.oauth.connector.naver;

import lombok.Data;

@Data
public class OAuth2NaverTokenRes {
	private String accessToken;
	private String refreshToken;
	private String tokenType;
	private String expiresIn;
	private String error;
	private String errorDescription;
}
