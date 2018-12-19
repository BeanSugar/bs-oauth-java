package org.scriptonbasestar.oauth.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author archmagece
 * @since 2016-10-24
 */
//@UtilityClass
@AllArgsConstructor
public enum OAuth20Constants {

	//OAuth 2.0
	ACCESS_TOKEN("access_token"),
	REFRESH_TOKEN("refesh_token"),
	//revoke token
	DELETE("revoke"),
	CLIENT_ID("client_id"),
	CLIENT_SECRET("client_secret"),
	REDIRECT_URI("redirect_uri"),
	CODE("code"),
	RESPONSE_TYPE("response_type"),
	GRANT_TYPE("grant_type"),
	STATE("state"),

	SCOPE("scope");

	@Getter
	public String value;

}
