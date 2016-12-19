package org.beansugar.oauth.o20.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author archmagece
 * @since 2016-10-24
 */
@AllArgsConstructor
public enum OAuth20CallbackParamType {
	//google,naver
	ACCESS_TOKEN("access_token"),
	//google,naver
	REFRESH_TOKEN("refresh_token"),
	//google,naver
	TOKEN_TYPE("token_type"),
	//google,naver
	EXPIRES_IN("expires_in"),
	//facebook
	EXPIRES("expires"),
	//google
	ID_TOKEN("id_token");

	@Getter
	public String value;

	public String toString() {
		return value;
	}

}
