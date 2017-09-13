package org.scriptonbasestar.oauth.client.o20.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author archmagece
 * @since 2016-10-31
 */
@AllArgsConstructor
public enum ResponseFormatType {
	CODE("code"),
	TOKEN("token");

	@Getter
	public String value;

	public String toString() {
		return value;
	}

}
