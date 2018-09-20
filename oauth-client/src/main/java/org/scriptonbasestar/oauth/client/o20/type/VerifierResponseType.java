package org.scriptonbasestar.oauth.client.o20.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.scriptonbasestar.oauth.client.model.ValueModel;

/**
 * @author archmagece
 * @since 2016-10-31
 */
@AllArgsConstructor
public enum VerifierResponseType implements ValueModel {
	CODE("code"),
	TOKEN("token");

	@Getter
	public final String value;

	public String toString() {
		return value;
	}

}
