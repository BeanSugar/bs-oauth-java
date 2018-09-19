package org.scripton.oauth.storage.redis;

import org.scriptonbasestar.oauth.client.core.token.TokenStorage;
import redis.clients.jedis.Jedis;

public class RedisTokenStorage implements TokenStorage {

	private Jedis jedis;
	public RedisTokenStorage(Jedis client){
		this.jedis = client;
	}

	@Override
	public String load(String id) {
		return jedis.get(id);
	}

	@Override
	public void store(String id, String token) {
		jedis.set(id, token);
	}

	@Override
	public void drop(String id) {
		jedis.del(id);
	}
}
