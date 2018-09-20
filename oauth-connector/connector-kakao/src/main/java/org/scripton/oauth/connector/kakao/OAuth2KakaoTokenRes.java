package org.scripton.oauth.connector.kakao;

import lombok.Data;
import org.scriptonbasestar.oauth.client.TokenPack;
import org.scriptonbasestar.oauth.client.o20.type.AccessTokenType;

@Data
public class OAuth2KakaoTokenRes implements TokenPack {

	private String accessToken;
	private Long expiresIn;
	private AccessTokenType tokenType;
	private String refreshToken;
	//까까오만의특유의옵션
	private Long refreshTokenExpiresIn;
	private String scope;

}
