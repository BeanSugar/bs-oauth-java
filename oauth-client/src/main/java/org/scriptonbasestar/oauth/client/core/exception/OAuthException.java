package org.scriptonbasestar.oauth.client.core.exception;

/**
 * @author archmagece
 * @since 2016-10-24
 */
public abstract class OAuthException extends RuntimeException {

	private static final String DEFAULT_MSG = "알 수 없는 실패";

	public OAuthException() {
		super(DEFAULT_MSG);
	}

	public OAuthException(String message) {
		super(message);
	}
}
