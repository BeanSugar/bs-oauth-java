package org.scriptonbasestar.oauth.http;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.scriptonbasestar.oauth.core.util.OAuthEncodeUtil;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

/**
 * @author archmagece
 * @since 2016-10-26 13
 */
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@UtilityClass
public final class ParamUtil {
	private static final char queryQuestion = '?';
	private static final char queryAnd = '&';
	private static final char queryEqual = '=';

	public static String generateQuery(String url, Collection<Param> params) {
		return url + queryQuestion + generateQuery(params.toArray(new Param[params.size()]));
	}

	public static String generateQuery(String url, Param... params) {
		return url + queryQuestion + generateQuery(params);
	}

	public static String generateQuery(Collection<Param> params) {
		return generateQuery(params.toArray(new Param[params.size()]));
	}

	public static String generateQuery(Param... params) {
		StringBuilder sb = new StringBuilder();

		int i = 0;
		for (Param param : params) {
			for(int j=0;j<param.getValue().length;j++){
				sb.append(param.getKey()).append(queryEqual).append(param.getValue()[j]);
				sb.append(queryAnd);
			}
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	public static String generateOAuthQuery(String url, Collection<Param> params) throws UnsupportedEncodingException {
		return url + queryQuestion + generateOAuthQuery(params.toArray(new Param[params.size()]));
	}

	public static String generateOAuthQuery(String url, Param... params) throws UnsupportedEncodingException {
		return url + queryQuestion + generateOAuthQuery(params);
	}

	public static String generateOAuthQuery(Collection<Param> params) throws UnsupportedEncodingException {
		return generateOAuthQuery(params.toArray(new Param[params.size()]));
	}

	public static String generateOAuthQuery(Param... params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();

		int i = 0;
		for (Param param : params) {
			for(int j=0;j<param.getValue().length;j++){
				sb.append(param.getKey()).append(queryEqual).append(OAuthEncodeUtil.encode(param.getValue()[j]));
				sb.append(queryAnd);
			}
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
