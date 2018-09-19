package org.scriptonbasestar.oauth.connector.naver;

import lombok.Data;

@Data
public class OAuth2NaverTokenReq {
	private String grantType;
	private String clientId;
	private String clientSecret;
	private String code;
	private String state;
	private String refreshToken;
	private String accessToken;
	private String serviceProvider;
}
