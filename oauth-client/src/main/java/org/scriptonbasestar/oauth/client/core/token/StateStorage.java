package org.scriptonbasestar.oauth.client.core.token;

/**
 * state 추가add/확인exists만 존재
 * exists확인 후에는 storage에서 삭제.
 * memory db 이용 expire time 설정
 * jdbc 이용시는 flush schedule
 *
 */
public interface StateStorage {
	/**
	 * @param userid
	 * @param state
	 *
	 * add
	 */
	void add(String userid, String state);

	/**
	 * @param userid
	 * @param state
	 *
	 * check exists and drop
	 */
	void exists(String userid, String state);
	/**
	 * 그냥 다지우기. 시스템 영향있을 수 있음
	 */
//	void flush();
}
