package org.scripton.oauth.connector.naver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.scriptonbasestar.oauth.client.util.SBSingleInstances;

import java.io.IOException;

public class ParsingTest {

	static String TOKEN_STRING = "{\n" + "\"access_token\":\"AAAANl4ZCye2mRbVUCBwdLntfRLI4VhuFoxvyuXyBjPD5c6ldhaA36yia5vNdFZjIpDiCTvESWi+RWrpZodonKI1Ga8=\",\n" + "\"refresh_token\":\"ZLpMIqGNo9rhNfU87wuii59Z1PfWTsd9sKcradwXpZipr2bxJdipdMxqWtbB5BVEwoxkBZUeF5QpHtKBHis6NmBVFhJpJYI7iiAzRzoNgxanP7lQV3P5J24AisqHCpipaLC0zsH\",\n" + "\"token_type\":\"bearer\",\n" + "\"expires_in\":\"3600\"\n" + "}";

	@Test
	public void object_mapper_test() throws IOException {
		ObjectMapper mapper = SBSingleInstances.getObjectMapper();
//		convert value는 다른 object로 값 복사
//		OAuth2NaverTokenRes token0 = mapper.convertValue(TOKEN_STRING, new TypeReference<OAuth2NaverTokenRes>(){});
//		OAuth2NaverTokenRes token1 = mapper.convertValue(TOKEN_STRING, OAuth2NaverTokenRes.class);
		OAuth2NaverTokenRes token2 = mapper.readValue(TOKEN_STRING, OAuth2NaverTokenRes.class);
	}
}
