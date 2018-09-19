package org.scriptonbasestar.oauth.connector.google;

import lombok.Data;

@Data
public class OAuth2GoogleAuthorizeReq {
	//	@JsonProperty("response_type")
	private String responseType;
	private String clientId;
	private String redirectUri;
	private String state;
	private String scope;
	private String accessType;
	private String includeGrantedScopes;
	private String loginHint;
	private String prompt;
}
