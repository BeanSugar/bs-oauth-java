package org.scriptonbasestar.oauth.core.exception;

/**
 * @author archmagece
 * @since 2016-10-24
 */
public class OAuthAuthException extends OAuthException {

	private static final String MSG = "인증/권한 오류";

	public OAuthAuthException() {
		super(MSG);
	}

	public OAuthAuthException(String message) {
		super(message);
	}
}
