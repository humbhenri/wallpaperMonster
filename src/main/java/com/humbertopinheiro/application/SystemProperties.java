package com.humbertopinheiro.application;

import static java.lang.System.getProperty;

import java.io.File;

public class SystemProperties {

	private static SystemProperties instance;

	public String getTempDir() {
		return getProperty("java.io.tmpdir");
	}

	public String getOS() {
		return getProperty("os.name");
	}

	public String getUserHomePictures() {
		return new File(getProperty("user.home"), "Pictures").getAbsolutePath();
	}

	public String getUserHome() {
		return getProperty("user.home");
	}

	public static SystemProperties instance() {
		if (instance == null) {
			instance = new SystemProperties();
		}
		return instance;
	}

}
