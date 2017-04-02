package org.beansugar.oauth.client.core.util;

/**
 * @author archmagece
 * @since 2017-04-02
 */
public class OutputUtil {

	public static String nos(Object value){
		if(value==null){
			return null;
		}
		return value.toString();
	}

}
