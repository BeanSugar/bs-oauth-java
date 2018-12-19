package org.scriptonbasestar.oauth.client.nobi.state;

import org.scriptonbasestar.oauth.client.model.State;

public class JsonStateGenerator
		implements StateGenerator {

	private final String[] keys;

	public JsonStateGenerator(String... keys) {
		this.keys = keys;
	}

	@Override
	public State generate(String... values) {
		if (keys.length != values.length) {
			throw new IllegalArgumentException("values must same length of keys");
		}
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		for (int i = 0; i < keys.length; i++) {
			sb.append(keys[i]).append('=').append(values[i]);
		}
		sb.append('}');
		return new State(sb.toString());
	}
}
