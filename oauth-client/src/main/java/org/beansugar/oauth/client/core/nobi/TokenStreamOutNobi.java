package org.beansugar.oauth.client.core.nobi;

import lombok.extern.slf4j.Slf4j;
import org.beansugar.oauth.client.o20.model.Token20;

/**
 * @author archmagece
 * @since 2016-10-26 16
 */
@Slf4j
public class TokenStreamOutNobi implements TokenNobi {
	@Override
	public Token20 extract(String socialResponse) {
		System.out.println(socialResponse);
		return null;
	}
}
