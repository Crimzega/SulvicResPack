package com.sulvic.respacker.util;

public enum MCVersion{

	RD_131655("rd-131655");

	private String gameVersion;

	MCVersion(String version){ gameVersion = version; }

	public String getGameVersion(){ return gameVersion; }

}
