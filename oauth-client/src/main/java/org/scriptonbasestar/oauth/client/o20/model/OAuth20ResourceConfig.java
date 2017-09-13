package org.scriptonbasestar.oauth.client.o20.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.scriptonbasestar.oauth.client.core.nobi.TokenNobi;
import org.scriptonbasestar.oauth.client.core.type.OAuthHttpVerb;
import org.scriptonbasestar.oauth.client.core.type.SignatureType;
import org.scriptonbasestar.oauth.client.o20.type.ResponseFormatType;

/**
 * @author archmagece
 * @since 2016-10-24
 */
@Getter
@Builder
public class OAuth20ResourceConfig {
	@NonNull
	private String authorizeUrl;
	private boolean oobSupport;
	private ResponseFormatType responseFormatType;
	@NonNull
	private String accessTokenUrl;
	private OAuthHttpVerb accessTokenVerb;
	private SignatureType signatureType;
	@NonNull
	private String callbackUrl;
	private String scope;
	@NonNull
	private TokenNobi tokenFormatNobi;

	public static class OAuth20ResourceConfigBuilder{
		private boolean oobSupport = false;
		private ResponseFormatType responseFormatType = ResponseFormatType.CODE;
		private OAuthHttpVerb accessTokenVerb = OAuthHttpVerb.GET;
		private SignatureType signatureType = SignatureType.Header;
	}

}
