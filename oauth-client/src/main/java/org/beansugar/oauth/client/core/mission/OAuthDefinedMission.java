package org.beansugar.oauth.client.core.mission;

import java.util.Set;

/**
 * @author archmagece
 * @since 2016-11-01
 */
public class OAuthDefinedMission {
	//필수 파라미터
	Set<String> keyEssential;
	//한 사이트에서 한가지만 허용되는 파라미터(필수)
	Set<String> keyFixed;
	//기본값이 존재해서 필수가 아닌 파라미터
	Set<String> keyOptional;
}
