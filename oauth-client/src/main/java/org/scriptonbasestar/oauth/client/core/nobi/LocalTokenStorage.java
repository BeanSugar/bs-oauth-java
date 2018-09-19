package org.scriptonbasestar.oauth.client.core.nobi;

import org.scriptonbasestar.oauth.client.core.token.TokenStorage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LocalTokenStorage implements TokenStorage {

	private ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

	@Override
	public String load(String id) {
		return null;
	}

	@Override
	public void store(String id, String token) {

	}

	@Override
	public void drop(String id) {

	}
}
