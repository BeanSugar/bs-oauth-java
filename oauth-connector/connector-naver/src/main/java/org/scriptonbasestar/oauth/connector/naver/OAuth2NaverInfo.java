package org.scriptonbasestar.oauth.connector.naver;

import org.scriptonbasestar.tool.core.check.Check;

public class OAuth2NaverInfo {
	private static final String DEFAULT_AUTHORIZE = "https://nid.naver.com/oauth2.0/authorize";
	private static final String DEFAULT_TOKEN = "https://nid.naver.com/oauth2.0/token";

	private final String authorize;
	private final String token;

	public OAuth2NaverInfo() {
		this(DEFAULT_AUTHORIZE, DEFAULT_TOKEN);
	}

	public OAuth2NaverInfo(String authorize, String token) {
		Check.urlDomainPattern(authorize, "authorize uri must not null or empty, and must full uri string");
		Check.urlDomainPattern(authorize, "token uri must not null or empty, and must full uri string");
		this.authorize = authorize;
		this.token = token;
	}
}
