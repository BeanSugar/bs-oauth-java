package org.scriptonbasestar.oauth.client.core.nobi;

import org.scriptonbasestar.oauth.client.o20.model.Token20;

/**
 * @author archmagece
 * @since 2016-10-26 16
 */
public interface TokenNobi {
	Token20 extract(String socialResponse);
}
