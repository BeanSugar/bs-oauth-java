package org.scriptonbasestar.oauth.client.config;

import lombok.Getter;
import org.scriptonbasestar.tool.core.check.Check;

/**
 * @author archmagece
 * @since 2016-10-24
 */
@Getter
public class OAuthPersonalConfig {
	private final String clientId;
	//카카오만 api-secret null
	private final String clientSecret;

	public OAuthPersonalConfig(String clientId, String clientSecret){
		Check.notEmptyString(clientId, "clientId should not null or empty");
//		Check.notEmptyString(clientSecret, "clientSecret should not empty");
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
}
