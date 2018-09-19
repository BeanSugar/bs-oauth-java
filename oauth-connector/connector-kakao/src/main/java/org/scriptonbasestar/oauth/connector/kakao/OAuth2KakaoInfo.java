package org.scriptonbasestar.oauth.connector.kakao;

public class OAuth2KakaoInfo {
	private static final String DEFAULT_AUTHORIZE = "";
	private static final String DEFAULT_TOKEN = "";

	private final String authorize;
	private final String token;

	public OAuth2KakaoInfo() {
		this.authorize = DEFAULT_AUTHORIZE;
		this.token = DEFAULT_TOKEN;
	}

	public OAuth2KakaoInfo(String authorize, String token) {
		this.authorize = authorize;
		this.token = token;
	}
}
