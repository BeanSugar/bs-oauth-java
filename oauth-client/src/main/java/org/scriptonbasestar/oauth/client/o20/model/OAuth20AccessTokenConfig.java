package org.scriptonbasestar.oauth.client.o20.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.scriptonbasestar.oauth.client.nobi.token.TokenExtractor;
import org.scriptonbasestar.oauth.client.type.GrantType;
import org.scriptonbasestar.oauth.client.type.OAuthHttpVerb;
import org.scriptonbasestar.oauth.client.type.SignatureType;

/**
 * @author archmagece
 * @since 2016-10-24
 */
@Getter
@Builder
public class OAuth20AccessTokenConfig {
	@NonNull
	private String accessTokenEndpoint;
	@NonNull
	//authorized token 요청할 때 썼던 주소 그대로
	private String redirectUri;
	//	private String scope;
	@NonNull
	private TokenExtractor tokenFormatNobi;
	private OAuthHttpVerb accessTokenVerb;
	private SignatureType signatureType;
	private GrantType grantType;

//	private String refreshToken;

	public static class OAuth20AccessTokenConfigBuilder {
		private OAuthHttpVerb accessTokenVerb = OAuthHttpVerb.GET;
		private SignatureType signatureType = SignatureType.Header;
		private GrantType grantType = GrantType.AUTHORIZATION_CODE;
	}

}
