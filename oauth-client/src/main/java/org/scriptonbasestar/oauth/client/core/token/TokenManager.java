package org.scriptonbasestar.oauth.client.core.token;

/**
 * token action list
 * issueToken
 * refreshToken
 * deleteToken
 * checkToken
 * <p>
 * TokenStorage.load
 * TokenStorage.drop
 * TokenStorage.save
 * <p>
 * Resource.use
 */
public interface TokenManager {
	/**
	 * issueToken, TokenStorage.load, checkToken, refreshToken, TokenStorage.store
	 * TokenStorage.load -> (return)
	 * TokenStorage.load -> (null) -> issueToken -> TokenStorage.store -> (return)
	 * TokenStorage.load -> (expired) -> refreshToken -> TokenStorage.store -> (return)
	 * TokenStorage.load -> (expired) -> refreshToken -> (fail) -> issueToken -> TokenStorage.store -> (return)
	 *
	 * @param id
	 * @return
	 */
	String find(String id);

	/**
	 * TokenStorage.dropToken
	 *
	 * @param id
	 * @return
	 */
	String delete(String id);
}
