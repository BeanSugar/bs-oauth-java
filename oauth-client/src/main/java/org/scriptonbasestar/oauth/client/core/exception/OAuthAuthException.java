package org.scriptonbasestar.oauth.client.core.exception;

/**
 * @author archmagece
 * @since 2016-10-24
 */
public class OAuthAuthException extends OAuthException {

	private static final String DEFAULT_MSG = "인증/권한 오류";

	public OAuthAuthException() {
		super(DEFAULT_MSG);
	}

	public OAuthAuthException(String message) {
		super(message);
	}
}
