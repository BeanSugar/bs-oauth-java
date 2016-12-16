package org.scriptonbasestar.oauth.http;

import java.util.*;

/**
 * @author archmagece
 * @since 2016-10-26 16
 */
public class ParamLister {
//public class ParamLister<PARAM extends Param>{

	private final Set<Param> paramSet = new LinkedHashSet<>();

	public ParamLister(Param ... params){
		for(Param param : params){
			paramSet.add(param);
		}
	}
	public ParamLister(Collection<Param> params){
		paramSet.addAll(params);
	}

	public static ParamLister create(Param ... params){
		return new ParamLister(params);
	}

	public ParamLister add(String key, String ... value){
		paramSet.add(new Param(key, value));
		return this;
	}

	public ParamLister add(Collection<Param> params){
		paramSet.addAll(params);
		return this;
	}

	public ParamLister add(Param ... params){
		for(Param param : params){
			paramSet.add(param);
		}
		return this;
	}

	public List<Param> paramList(){
		return new ArrayList<>(paramSet);
	}

	public Set<Param> paramSet(){
		return paramSet;
	}


}
