package org.scriptonbasestar.oauth.o20.model;

import lombok.Getter;
import lombok.ToString;
import org.scriptonbasestar.oauth.o20.type.AccessTokenType;

import java.io.Serializable;

/**
 * @author archmagece
 * @date 2016-10-24
 */
@Getter
@ToString
public class Token20 implements Serializable {

	//REQUIRED
	private String accessToken;
	//REQUIRED
	private AccessTokenType tokenType;
	//RECOMMENDED
	private int expireIn;
	//OPTIONAL,
//	OPTIONAL, if identical to the scope requested by the client;
//	otherwise, REQUIRED.  The scope of the access token as
//	described by Section 3.3.
	//	private String scope;
//	//STATE
//	private String state;
	private String idToken;
	private String refreshToken;

	public Token20(String accessToken, int expireIn){
		this.accessToken = accessToken;
		this.expireIn = expireIn;
		this.tokenType = AccessTokenType.BEARER;
	}

	public Token20(String accessToken, int expireIn, AccessTokenType tokenType){
		this.accessToken = accessToken;
		this.expireIn = expireIn;
		this.tokenType = tokenType;
	}

	public Token20(String accessToken, int expireIn, AccessTokenType tokenType, String idToken, String refreshToken){
		this.accessToken = accessToken;
		this.expireIn = expireIn;
		this.tokenType = tokenType;
		this.idToken = idToken;
		this.refreshToken = refreshToken;
	}

}
