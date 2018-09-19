package org.scriptonbasestar.oauth.client.core.nobi;

import lombok.extern.slf4j.Slf4j;
import org.scriptonbasestar.oauth.client.core.model.State;
import org.scriptonbasestar.oauth.client.core.token.StateGenerator;
import org.scriptonbasestar.tool.core.check.Check;

/**
 * @author archmagece
 * @since 2016-10-26 16
 */
@Slf4j
public class RandomStringStateGenerator implements StateGenerator {

	private final char separator;

	public RandomStringStateGenerator() {
		this.separator = '-';
	}

	@Override
	public String generate(String... values) {
		//TODO tools.core.Check 에 추가
		if (values == null || values.length == 0) {
			throw new IllegalArgumentException("random string state generator need one argument");
		}
		return values[0] + separator + System.currentTimeMillis();
	}
}
