package org.scriptonbasestar.oauth.connector.facebook;

public class OAuth2FacebookInfo {
	private static final String DEFAULT_AUTHORIZE = "";
	private static final String DEFAULT_TOKEN = "";

	private final String authorize;
	private final String token;

	public OAuth2FacebookInfo() {
		this.authorize = DEFAULT_AUTHORIZE;
		this.token = DEFAULT_TOKEN;
	}

	public OAuth2FacebookInfo(String authorize, String token) {
		this.authorize = authorize;
		this.token = token;
	}
}
