package org.scriptonbasestar.oauth.core.nobi;

import org.scriptonbasestar.oauth.o20.model.Token20;

/**
 * @Author archmagece
 * @CreatedAt 2016-10-26 16
 */
public interface TokenNobi {
	Token20 extract(String socialResponse);
}
