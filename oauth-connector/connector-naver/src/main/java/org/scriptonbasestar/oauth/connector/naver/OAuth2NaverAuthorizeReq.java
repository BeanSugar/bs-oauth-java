package org.scriptonbasestar.oauth.connector.naver;

import lombok.Data;
import org.scriptonbasestar.oauth.client.o20.type.ResponseFormatType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OAuth2NaverAuthorizeReq {
	@NotNull
	private final ResponseFormatType responseType = ResponseFormatType.CODE;

	@NotNull
	@Size(min = 1)
	private String clientId;

	@NotNull
	private String redirectUri;

	@NotNull
	private String state;
}
