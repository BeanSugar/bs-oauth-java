package org.scriptonbasestar.oauth.core.exception;

/**
 * @author archmagece
 * @date 2016-10-24
 */
public abstract class OAuthException extends RuntimeException {

	private static final String MSG = "알 수 없는 실패";

	public OAuthException() {
		super(MSG);
	}

	public OAuthException(String message) {
		super(message);
	}
}
