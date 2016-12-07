package org.scriptonbasestar.oauth.core.nobi;

import lombok.extern.slf4j.Slf4j;
import org.scriptonbasestar.oauth.o20.model.Token20;

/**
 * @Author archmagece
 * @CreatedAt 2016-10-26 16
 */
@Slf4j
public class TokenStreamOutNobi implements TokenNobi {
	@Override
	public Token20 extract(String socialResponse) {
		System.out.println(socialResponse);
		return null;
	}
}
