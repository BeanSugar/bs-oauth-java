package org.scriptonbasestar.oauth.client.exception;

/**
 * @author archmagece
 * @since 2016-10-24
 */
public abstract class OAuthException extends RuntimeException {

	private static final String DEFAULT_MSG = "알 수 없는 실패";

	public OAuthException(String message){
		super(message);
	}

	public OAuthException(Throwable e) {
		super(DEFAULT_MSG, e);
	}

	public OAuthException(String message, Throwable e) {
		super(message, e);
	}
}
