package org.scripton.test;

import java.io.InputStream;
import java.util.Properties;

public class ReadSetting {
	private final Properties configFile;

	private ReadSetting(InputStream is) {
		configFile = new java.util.Properties();
		try {
			configFile.load(is);
		} catch (Exception e) {
			throw new IllegalArgumentException("파일이 없거나 읽는 중 오류 발생.", e);
		}
	}

	public static ReadSetting readProjectResource(String filename){
		return new ReadSetting(ClassLoader.getSystemClassLoader().getResourceAsStream(filename));
	}


	public String getProperty(String key) {
		return this.configFile.getProperty(key);
	}
}
