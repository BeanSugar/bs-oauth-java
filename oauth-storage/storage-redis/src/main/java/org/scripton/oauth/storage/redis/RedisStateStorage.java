package org.scripton.oauth.storage.redis;

import org.scriptonbasestar.oauth.client.core.token.StateStorage;
import redis.clients.jedis.Jedis;

public class RedisStateStorage implements StateStorage {

	private Jedis jedis;
	public RedisStateStorage(Jedis client){
		this.jedis = client;
	}

	@Override
	public void add(String userid, String state) {

	}

	@Override
	public void exists(String userid, String state) {

	}
}
