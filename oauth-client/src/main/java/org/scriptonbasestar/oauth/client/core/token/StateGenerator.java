package org.scriptonbasestar.oauth.client.core.token;

import org.scriptonbasestar.oauth.client.core.model.State;

/**
 * @author archmagece
 * @since 2016-10-27
 */
public interface StateGenerator {
	 String generate(String ... values);
}
