package org.scripton.oauth.connector.kakao;

import lombok.Data;

@Data
public class OAuth2KakaoCallbackRes {
	private String code;
	private String state;
	private String error;
	private String errorDescription;
}
