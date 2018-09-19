package org.scriptonbasestar.oauth.client.o20;

import org.scriptonbasestar.oauth.client.core.token.TokenManager;
import org.scriptonbasestar.oauth.client.core.token.TokenStorage;
import org.scriptonbasestar.oauth.client.o20.client.OAuth20Client;

public class OAuth2TokenManager implements TokenManager {

	private TokenStorage tokenStorage;
	private OAuth20Client oauthClient;

	public OAuth2TokenManager(TokenStorage tokenStorage, OAuth20Client oauthClient){
		this.tokenStorage = tokenStorage;
		this.oauthClient = oauthClient;
	}

	@Override
	public String find(String id) {
		String token = tokenStorage.load(id);
		if(token == null || token.isEmpty()){
			oauthClient.generateState();
		}

	}

	@Override
	public String delete(String id) {
		oauthClient.
	}
}
