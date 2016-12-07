package org.scriptonbasestar.oauth.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.beansugar.tools.core.check.Check;

/**
 * @Author archmagece
 * @CreatedAt 2016-10-25 22
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
