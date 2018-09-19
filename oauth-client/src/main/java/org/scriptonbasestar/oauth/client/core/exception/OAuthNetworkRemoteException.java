package org.scriptonbasestar.oauth.client.core.exception;

/**
 * @author archmagece
 * @since 2016-10-24
 */
public class OAuthNetworkRemoteException extends OAuthException {

	private static final String DEFAULT_MSG = "원격 서버 접속 오류";

	public OAuthNetworkRemoteException() {
		super(DEFAULT_MSG);
	}

	public OAuthNetworkRemoteException(String message) {
		super(message);
	}
}
