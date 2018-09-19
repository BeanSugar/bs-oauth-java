package org.scriptonbasestar.oauth.connector.google;

public class OAuth2GoogleInfo {
	private static final String DEFAULT_AUTHORIZE = "";
	private static final String DEFAULT_TOKEN = "";

	private final String authorize;
	private final String token;

	public OAuth2GoogleInfo() {
		this.authorize = DEFAULT_AUTHORIZE;
		this.token = DEFAULT_TOKEN;
	}

	public OAuth2GoogleInfo(String authorize, String token) {
		this.authorize = authorize;
		this.token = token;
	}
}
