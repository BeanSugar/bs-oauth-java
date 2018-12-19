package org.scripton.oauth.connector.google;

import lombok.Data;
import org.scriptonbasestar.oauth.client.TokenPack;
import org.scriptonbasestar.oauth.client.o20.type.AccessTokenType;

@Data
public class OAuth2GoogleTokenRes implements TokenPack {
	private String accessToken;
	private AccessTokenType tokenType;
	//null or not
	private String refreshToken;
	private Long expiresIn;

	/**
	 * 사용자별 unique 값. 서비스마다 다름
	 */
	private String idToken;
	/**
	 * scope 왜 주는지 모르겠지만
	 */
	private String scope;

	/**
	 * 삭제시만 응답. 삭제 성공시 success
	 */
	private String result;
	private String error;
	private String errorDescription;
}
