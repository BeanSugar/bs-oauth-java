package org.scriptonbasestar.oauth.client.core.token;

/**
 * @author archmagece
 * @since 2018-09-19
 */
public interface OAuthClient {

	String generateAuthorizeUrl();
	String issueToken();
	String issueTokenBearer();
	String refreshToken();
	String deleteToken();

	String accessResource(String accessToken, String resourceUrl);

}
