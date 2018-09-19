package org.scriptonbasestar.oauth.client.core.token;

public interface TokenStorage {
	String load(String id);

	void store(String id, String token);

	void drop(String id);
}
