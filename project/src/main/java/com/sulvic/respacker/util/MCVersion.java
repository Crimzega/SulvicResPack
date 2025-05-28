package com.sulvic.respacker.util;

import java.util.Comparator;

import com.sulvic.util.SulvicMath;

public enum MCVersion implements Comparator<MCVersion>{

	RD_131655("rd-131655"),
	RD_132211("rd-132211", RD_131655),
	RD_132328("rd-132328", RD_131655),
	RD_20090515("rd-20090515"),
	RD_160052("rd-160052", RD_20090515),
	RD_161348("rd-161348"),
	MC_161607("mc-161607", RD_161348),
	MC_161616("mc-161616", RD_161348),
	MC_161625("mc-161625", RD_161348),
	MC_161648("mc-161648", RD_161348),
	CLASSIC_0_0_2A("0.0.2a", RD_161348),
	CLASSIC_0_0_3A("0.0.3a", RD_161348),
	CLASSIC_0_0_9A("0.0.9a", RD_161348),
	CLASSIC_0_0_10A("0.0.10a", RD_161348),
	CLASSIC_0_0_11A("0.0.11a", RD_161348),
	CLASSIC_0_0_12A("0.0.12a", "0.0.12a_01", "0.0.12a_02", "0.0.12a_03"),
	CLASSIC_0_0_13A("0.0.13a", "0.0.13a_03"),
	CLASSIC_0_0_14A("0.0.14a", "0.0.14a_01", "0.0.14a_03", "0.0.14a_04", "0.0.14a_05", "0.0.14a_06", "0.0.14a_07", "0.0.14a_08"),
	CLASSIC_0_0_15A("0.0.15a", "0.0.15a_mt_1", "0.0.15a_mt_2", "0.0.15a_mt_3", "0.0.15a_mt_4", "0.0.15a_mt_5", "0.0.15a_mt_6", "0.0.15a_mt_7",
		"0.0.15a_mt_8", "0.0.15a_01", "0.0.15a_02", "0.0.15a_03"),
	CLASSIC_0_0_16A("0.0.16a", CLASSIC_0_0_15A, "0.0.16a_01", "0.0.16a_02"),
	CLASSIC_0_0_17A("0.0.17a", CLASSIC_0_0_15A),
	CLASSIC_0_0_18A("0.0.18a", CLASSIC_0_0_15A, "0.0.18a_01", "0.0.18a_02"),
	CLASSIC_0_0_19A("0.0.19a", "0.0.19a_01", "0.0.19a_02", "0.0.19a_03", "0.0.19a_04", "0.0.19a_05", "0.0.19a_06"),
	CLASSIC_0_0_20A("0.0.20a", "0.0.20a_01", "0.0.20a_02")

	;

	private String[] gameSubVersions;
	private int targetVersion;
	private MCVersion textureFallback;
	private String gameVersion;

	MCVersion(String version, String... subVersions){
		gameVersion = version;
		gameSubVersions = subVersions;
	}

	MCVersion(String version, MCVersion fallback, String... subVersions){
		this(version, subVersions);
		textureFallback = fallback;
	}

	@Override
	public int compare(MCVersion o1, MCVersion o2){
		int result = Integer.compare(o1.ordinal(), o2.ordinal());
		return result == 0? Integer.compare(o1.targetVersion, o2.targetVersion): result;
	}

	public MCVersion getTextureFallback(){ return textureFallback != null? textureFallback: this; }

	public String getGameVersion(){ return gameVersion; }

	public String getTargetVersion(){ return targetVersion == 0? gameVersion: gameSubVersions[targetVersion - 1]; }

	public String getTextureVersion(){ return getTextureFallback().gameVersion; }

	public void setTargetVersion(int index){ targetVersion = SulvicMath.clampInt(index, 0, gameSubVersions != null?gameSubVersions.length + 1: 0); }

}
