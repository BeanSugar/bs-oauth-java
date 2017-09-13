package org.scriptonbasestar.oauth.client.core.nobi;

import lombok.extern.slf4j.Slf4j;
import org.scriptonbasestar.oauth.client.core.model.State;

/**
 * @author archmagece
 * @since 2016-10-26 16
 */
@Slf4j
public class DefaultStateNobi implements StateNobi {

	private final char separator;

	public DefaultStateNobi(){
		this.separator = '-';
	}

	@Override
	public State getState(String serviceName) {
		return new State(serviceName + separator + System.currentTimeMillis());
	}

}
