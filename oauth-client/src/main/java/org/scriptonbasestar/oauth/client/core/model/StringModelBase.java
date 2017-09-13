package org.scriptonbasestar.oauth.client.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.scriptonbasestar.tool.core.check.Check;

/**
 * @author archmagece
 * @since 2016-10-25 22
 */
@EqualsAndHashCode
public abstract class StringModelBase {
	@Getter
	protected final String value;

	public StringModelBase(String value) {
		Check.notNullOrEmptyString(value, "StringModelBase extends value must not null or empty");
		this.value = value;
	}
}
