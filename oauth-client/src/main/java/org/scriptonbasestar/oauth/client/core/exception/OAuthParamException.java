package org.scriptonbasestar.oauth.client.core.exception;

/**
 * @author archmagece
 * @since 2016-11-01
 */
public class OAuthParamException extends OAuthException {

	private static final String MSG = "파라미터 오류";

	public OAuthParamException() {
		super(MSG);
	}

	public OAuthParamException(String message) {
		super(message);
	}
}
