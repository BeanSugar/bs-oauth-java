package org.scriptonbasestar.oauth.o20.model;

import lombok.Getter;
import org.beansugar.tools.core.check.Check;

/**
 * @author archmagece
 * @date 2016-10-24
 */
@Getter
public class OAuth20PersonalConfig {
	private final String apiKey;
	//카카오만 api-secret null
	private final String apiSecret;

	public OAuth20PersonalConfig(String apiKey, String apiSecret){
		Check.notNullOrEmptyString(apiKey, "apiKey should not null or empty");
		Check.notEmptyString(apiSecret, "apiSecret should not empty");
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
	}
}
