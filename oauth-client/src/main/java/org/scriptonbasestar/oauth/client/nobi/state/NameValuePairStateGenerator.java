package org.scriptonbasestar.oauth.client.nobi.state;

import org.scriptonbasestar.oauth.client.model.State;

public class NameValuePairStateGenerator implements StateGenerator {

	private final String[] keys;

	public NameValuePairStateGenerator(String... keys) {
		this.keys = keys;
	}

	@Override
	public State generate(String... values) {
		if (keys.length != values.length) {
			throw new IllegalArgumentException("values must same length of keys");
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.length; i++) {
			sb.append(keys[i]).append('=').append(values[i]);
		}
		return new State(sb.toString());
	}
}
