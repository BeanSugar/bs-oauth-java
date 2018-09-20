package org.scriptonbasestar.oauth.client;

public interface OAuth2ResourceFunction<T> {
	T run(String accessToken);
}
