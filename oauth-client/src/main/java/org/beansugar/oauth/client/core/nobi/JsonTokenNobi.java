package org.beansugar.oauth.client.core.nobi;

import com.google.gson.Gson;
import org.beansugar.oauth.client.o20.model.Token20;
import org.beansugar.oauth.client.o20.type.AccessTokenType;

import java.util.Map;

/**
 * @author archmagece
 * @since 2016-10-27
 */
public class JsonTokenNobi implements TokenNobi {
	private Gson gson = new Gson();
	@Override
	public Token20 extract(String socialResponse) {
		Map<String,String> map = gson.fromJson(socialResponse, Map.class);
		return new Token20(map.get("access_token"), Integer.parseInt(map.get("expires_in")), AccessTokenType.valueOf(map.get("token_type").toUpperCase()), map.get("refresh_token"));
	}
}
