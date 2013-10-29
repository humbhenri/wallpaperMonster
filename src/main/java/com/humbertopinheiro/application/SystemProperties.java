package com.humbertopinheiro.application;

import static java.lang.System.getProperty;

import java.io.File;

public enum SystemProperties {
	INSTANCE;
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
	
}
