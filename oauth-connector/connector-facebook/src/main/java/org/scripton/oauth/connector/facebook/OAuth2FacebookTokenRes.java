package org.scripton.oauth.connector.facebook;

import lombok.Data;
import org.scriptonbasestar.oauth.client.TokenPack;
import org.scriptonbasestar.oauth.client.o20.type.AccessTokenType;

@Data
public class OAuth2FacebookTokenRes implements TokenPack {
	private String accessToken;
	private AccessTokenType tokenType;
	//null or not
	private String refreshToken;
	private Long expiresIn;

	// 삭제시만 응답. 삭제 성공시 success
	private String result;
	private String error;
	private String errorDescription;
}
