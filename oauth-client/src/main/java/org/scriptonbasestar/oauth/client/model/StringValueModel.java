package org.scriptonbasestar.oauth.client.model;

import lombok.Data;
import org.scriptonbasestar.tool.core.check.Check;

/**
 * @author archmagece
 * @since 2016-10-25 22
 */
@Data
public abstract class StringValueModel implements ValueModel {
	protected final String value;
	protected StringValueModel(String value) {
		Check.notNullOrEmptyString(value, "value must not null or empty");
		this.value = value;
	}
}
