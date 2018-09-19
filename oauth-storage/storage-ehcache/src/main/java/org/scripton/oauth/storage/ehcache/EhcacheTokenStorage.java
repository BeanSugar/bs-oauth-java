package org.scripton.oauth.storage.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.scriptonbasestar.oauth.client.core.token.TokenStorage;

public class EhcacheTokenStorage implements TokenStorage {

	private Cache cache;
	public EhcacheTokenStorage(Cache cache){
		this.cache = cache;
	}

	@Override
	public String load(String id) {
		return (String) cache.get(id).getObjectValue();
	}

	@Override
	public void store(String id, String token) {
		cache.put(new Element(id, token));
	}

	@Override
	public void drop(String id) {
		cache.remove(id);
	}
}
