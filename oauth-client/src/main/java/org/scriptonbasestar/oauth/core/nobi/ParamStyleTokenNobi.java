package org.scriptonbasestar.oauth.core.nobi;

import lombok.extern.slf4j.Slf4j;
import org.scriptonbasestar.oauth.o20.model.Token20;
import org.scriptonbasestar.oauth.o20.type.AccessTokenType;

/**
 * @author archmagece
 * @since 2016-10-26 19
 *
 * facebook
 */
@Slf4j
public class ParamStyleTokenNobi implements TokenNobi {
	@Override
	public Token20 extract(String socialResponse) {
//		Token20 result = new Token20();

		String accessToken = null;
		AccessTokenType tokenType = AccessTokenType.MAC;
		Integer expireIn = null;


		String[] socialResponseKVArr = socialResponse.split("&");
		for(String socialResponseKV : socialResponseKVArr){
			String[] socialResponseKVsp = socialResponseKV.split("=");

			if(socialResponseKVsp[0].equals("access_token")){
				accessToken = socialResponseKVsp[1];
			} else if(socialResponseKVsp[0].equals("expires")){
				expireIn = Integer.parseInt(socialResponseKVsp[1]);
			}
		}
		return new Token20(accessToken, expireIn, tokenType);
	}
}
