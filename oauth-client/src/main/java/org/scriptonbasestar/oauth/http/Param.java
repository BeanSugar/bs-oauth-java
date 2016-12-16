package org.scriptonbasestar.oauth.http;

import lombok.Getter;
import org.beansugar.tools.core.check.Check;

/**
 * @author archmagece
 * @since 2016-10-25
 *
 * TODO generic.
 * TODO ParamUtil - ParamLister 안으로
 * TODO
 */
@Getter
public class Param {
	protected String key;
	protected String[] value;
	public Param(String key, String ... value){
		Check.notNullOrEmptyString(key, "Param.key will not empty");
		Check.notNullOrEmpty(value, "Param.value will not empty");
		this.key = key;
		this.value = value;
	}
}
