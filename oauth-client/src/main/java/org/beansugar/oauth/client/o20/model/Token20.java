package org.beansugar.oauth.client.o20.model;

import lombok.Getter;
import lombok.ToString;
import org.beansugar.oauth.client.o20.type.AccessTokenType;

import java.io.Serializable;

/**
 * @author archmagece
 * @since 2016-10-24
 */
@Getter
@ToString
public class Token20 implements Serializable {

	//REQUIRED
	private String accessToken;
	//REQUIRED
	private AccessTokenType tokenType;
	//RECOMMENDED
	private double expireIn;
	//OPTIONAL,
//	OPTIONAL, if identical to the scope requested by the client;
//	otherwise, REQUIRED.  The scope of the access token as
//	described by Section 3.3.
	//	private String scope;
//	//STATE
//	private String state;
	private String idToken;
	private String refreshToken;

	public Token20(String accessToken, double expireIn){
		this.accessToken = accessToken;
		this.expireIn = expireIn;
		this.tokenType = AccessTokenType.BEARER;
	}

	public Token20(String accessToken, double expireIn, AccessTokenType tokenType){
		this.accessToken = accessToken;
		this.expireIn = expireIn;
		this.tokenType = tokenType;
	}

	public Token20(String accessToken, double expireIn, AccessTokenType tokenType, String refreshToken){
		this.accessToken = accessToken;
		this.expireIn = expireIn;
		this.tokenType = tokenType;
		this.refreshToken = refreshToken;
	}

	public Token20(String accessToken, double expireIn, AccessTokenType tokenType, String idToken, String refreshToken){
		this.accessToken = accessToken;
		this.expireIn = expireIn;
		this.tokenType = tokenType;
		this.idToken = idToken;
		this.refreshToken = refreshToken;
	}

}
