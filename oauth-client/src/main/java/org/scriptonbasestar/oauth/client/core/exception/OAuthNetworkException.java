package org.scriptonbasestar.oauth.client.core.exception;

/**
 * @author archmagece
 * @since 2016-10-24
 */
public class OAuthNetworkException extends OAuthException {

	private static final String DEFAULT_MSG = "네트워크 오류";

	public OAuthNetworkException() {
		super(DEFAULT_MSG);
	}

	public OAuthNetworkException(String message) {
		super(message);
	}
}
