package org.scriptonbasestar.oauth.client.core.model;

import lombok.Getter;
import org.scriptonbasestar.tool.core.check.Check;

/**
 * @author archmagece
 * @since 2016-10-24
 */
@Getter
public class OAuthPersonalConfig {
	private final String apiKey;
	//카카오만 api-secret null
	private final String apiSecret;

	public OAuthPersonalConfig(String apiKey, String apiSecret){
		Check.notNullOrEmptyString(apiKey, "apiKey should not null or empty");
		Check.notEmptyString(apiSecret, "apiSecret should not empty");
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
	}
}
