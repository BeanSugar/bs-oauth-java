package org.beansugar.oauth.client.core.nobi;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.client.core.model.State;

/**
 * @author archmagece
 * @since 2016-10-26 16
 */
@Slf4j
public class DefaultStateNobi implements StateNobi {

	private final String name;
	private final char separator;

	public DefaultStateNobi(String name){
		this.name = name;
		this.separator = '-';
	}

	@Override
	public State getState() {
		return new State(name + separator + System.currentTimeMillis());
	}
}
