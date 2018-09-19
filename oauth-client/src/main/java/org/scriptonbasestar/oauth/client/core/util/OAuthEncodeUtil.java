package org.scriptonbasestar.oauth.client.core.util;

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

	public static String encode(String value) {
		try {
			return URLEncoder.encode(value, CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String[] encodeArray(String[] values) {
		for (int i = 0; i < values.length; i++) {
			values[i] = OAuthEncodeUtil.encode(values[i]);
		}
		return values;
	}
}
