package org.scriptonbasestar.oauth.client.o20.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.scriptonbasestar.oauth.client.model.ValueModel;

/**
 * @author archmagece
 * @since 2016-10-24
 */
@AllArgsConstructor
public enum OAuth20CallbackParamType implements ValueModel {
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
	public final String value;

	public String toString() {
		return value;
	}

}
