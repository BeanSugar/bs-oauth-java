package org.scriptonbasestar.oauth.client.http;

import java.util.*;

/**
 * @author archmagece
 * @since 2016-10-26 16
 */
public class ParamList {
//public class ParamList<PARAM extends Param>{

	private final Set<Param> paramSet = new LinkedHashSet<>();

	public ParamList(Param ... params){
		for(Param param : params){
			paramSet.add(param);
		}
	}
	public ParamList(Collection<Param> params){
		paramSet.addAll(params);
	}

	public static ParamList create(Param ... params){
		return new ParamList(params);
	}

	public ParamList add(String key, String ... value){
		paramSet.add(new Param(key, value));
		return this;
	}

	public ParamList add(Collection<Param> params){
		paramSet.addAll(params);
		return this;
	}

	public ParamList add(Param ... params){
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
