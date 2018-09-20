package org.scripton.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.scriptonbasestar.oauth.client.util.SBSingleInstances;

import java.io.File;

/**
 * new File("").getAbsolutePath()
 *   junit으로 돌릴 때 == 프로젝트 모듈 root
 *   main으로 돌릴 때 == 프로젝트 root
 *
 * getClass().getClassLoader().getResource("").path == resources dir
 */
public class ReadSettingTest {

	@Test
	public void touch_file_test(){
		File file = new File(getClass().getClassLoader().getResource("sample.cfg").getFile());
		System.out.println(file.getAbsolutePath());
	}

}
