package org.scriptonbasestar.oauth.client;

import org.scriptonbasestar.oauth.client.model.State;

@FunctionalInterface
public interface OAuth2GenerateAuthorizeUrlFunction {
	String generate(State state);
}
