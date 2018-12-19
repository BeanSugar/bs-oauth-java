package org.scriptonbasestar.oauth.client.nobi.state;

import lombok.extern.slf4j.Slf4j;
import org.scriptonbasestar.oauth.client.model.State;
import org.scriptonbasestar.tool.core.check.Check;

/**
 * @author archmagece
 * @since 2016-10-26 16
 */
@Slf4j
public class RandomStringStateGenerator
		implements StateGenerator {

	private final char separator;
//	private final String serviceName;

	public RandomStringStateGenerator(String serviceName) {
		Check.notEmptyString(serviceName, "serviceName must not null");
		this.separator = '-';
//		this.serviceName = serviceName;
	}

	@Override
	public State generate(String... values) {
		return new State(values[0] + separator + System.currentTimeMillis());
	}
}
