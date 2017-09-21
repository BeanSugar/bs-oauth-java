package org.scriptonbasestar.oauth.client.core.exception;

/**
 * @author archmagece
 * @since 2016-11-01
 */
public class OAuthParamException extends OAuthException {

	private static final String DEFAULT_MSG = "파라미터 오류";

	public OAuthParamException() {
		super(DEFAULT_MSG);
	}

	public OAuthParamException(String message) {
		super(message);
	}
}
