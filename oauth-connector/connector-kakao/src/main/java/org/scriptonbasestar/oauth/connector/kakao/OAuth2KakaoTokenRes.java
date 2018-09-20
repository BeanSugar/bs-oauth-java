package org.scriptonbasestar.oauth.connector.kakao;

import lombok.Data;
import org.scriptonbasestar.oauth.client.TokenPack;
import org.scriptonbasestar.oauth.client.o20.type.AccessTokenType;

@Data
public class OAuth2KakaoTokenRes implements TokenPack {

	private String accessToken;
	private AccessTokenType tokenType;
	private String refreshToken;
	private Long expiresIn;

}
