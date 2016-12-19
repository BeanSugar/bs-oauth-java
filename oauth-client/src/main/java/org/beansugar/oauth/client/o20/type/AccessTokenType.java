package org.beansugar.oauth.client.o20.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author archmagece
 * @since 2016-10-24
 */
@AllArgsConstructor
public enum AccessTokenType {

	BEARER("Bearer"),
	MAC("Mac");

	@Getter
	public String value;

	public String toString() {
		return value;
	}

//	public AccessTokenType from(String value){
//		return this.value.toUpperCase().equals(value.toUpperCase());
//	}
}
