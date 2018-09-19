package org.scriptonbasestar.oauth.client.core.nobi;

import lombok.extern.slf4j.Slf4j;
import org.scriptonbasestar.oauth.client.core.token.TokenExtractor;
import org.scriptonbasestar.oauth.client.o20.model.Token20;

/**
 * @author archmagece
 * @since 2016-10-26 16
 */
@Slf4j
public class TokenStreamOutExtractor implements TokenExtractor {
	@Override
	public Token20 extract(String socialResponse) {
		System.out.println(socialResponse);
		return null;
	}
}
