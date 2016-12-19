package org.beansugar.oauth.core.exception;

/**
 * @author archmagece
 * @since 2016-10-24
 */
public class OAuthNetworkRemoteException extends OAuthException {

	private static final String MSG = "원격 서버 접속 오류";

	public OAuthNetworkRemoteException() {
		super(MSG);
	}

	public OAuthNetworkRemoteException(String message) {
		super(message);
	}
}
