package org.scriptonbasestar.oauth.core.exception;

/**
 * @author archmagece
 * @date 2016-10-24
 */
public class OAuthInitException extends OAuthException {

	private static final String MSG = "초기화 실패";

	public OAuthInitException() {
		super(MSG);
	}

	public OAuthInitException(String message) {
		super(message);
	}
}
