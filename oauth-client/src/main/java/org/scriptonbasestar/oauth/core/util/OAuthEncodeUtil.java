package org.scriptonbasestar.oauth.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author archmagece
 * @since 2016-10-26 14
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OAuthEncodeUtil {

	private static String CHARSET = "UTF-8";

	public static String encode(String value) throws UnsupportedEncodingException {
		return URLEncoder.encode(value, CHARSET);
	}

	public static String[] encodeArray(String[] values) throws UnsupportedEncodingException {
		for (int i = 0; i < values.length; i++) {
			values[i] = URLEncoder.encode(values[i], CHARSET);
		}
		return values;
	}
}
