package com.sulvic.respacker.util;

import java.util.Comparator;

import javax.annotation.Nullable;

public enum MCVersion implements Comparator<MCVersion>{

	RD_131655("rd-131655"),
	RD_132211("rd-132211"),
	RD_132328("rd-132328"),
	RD_20090515("rd-20090515"),
	RD_160052("rd-160052"),
	RD_161348("rd-161348"),
	MC_161607("mc-161607"),
	MC_161616("mc-161616"),
	MC_161625("mc-161625"),
	MC_161648("mc-161648"),
	CLASSIC_0_0_2A("classic-0.0.2a"),
	CLASSIC_0_0_3A("classic-0.0.3a"),
	CLASSIC_0_0_9A("classic-0.0.9a"),
	CLASSIC_0_0_10A("classic-0.0.10a"),
	CLASSIC_0_0_11A("classic-0.0.11a"),
	CLASSIC_0_0_12A("classic-0.0.12a"),
	CLASSIC_0_0_12A_01("classic-0.0.12a_01"),
	CLASSIC_0_0_12A_02("classic-0.0.12a_02"),
	CLASSIC_0_0_12A_03("classic-0.0.12a_03"),
	CLASSIC_0_0_13A("classic-0.0.13a"),
	CLASSIC_0_0_13A_03("classic-0.0.13a_03"),
	CLASSIC_0_0_14A("classic-0.0.14a"),
	CLASSIC_0_0_14A_01("classic-0.0.14a_01"),
	CLASSIC_0_0_14A_03("classic-0.0.14a_03"),
	CLASSIC_0_0_14A_04("classic-0.0.14a_04"),
	CLASSIC_0_0_14A_05("classic-0.0.14a_05"),
	CLASSIC_0_0_14A_06("classic-0.0.14a_06"),
	CLASSIC_0_0_14A_07("classic-0.0.14a_07"),
	CLASSIC_0_0_14A_08("classic-0.0.14a_08"),
	CLASSIC_0_0_15A("classic-0.0.15a"),
	CLASSIC_0_0_15A_MT_1("classic-0.0.15a_mt_1"),
	CLASSIC_0_0_15A_MT_2("classic-0.0.15a_mt_2"),
	CLASSIC_0_0_15A_MT_3("classic-0.0.15a_mt_3"),
	CLASSIC_0_0_15A_MT_4("classic-0.0.15a_mt_4"),
	CLASSIC_0_0_15A_MT_5("classic-0.0.15a_mt_5"),
	CLASSIC_0_0_15A_MT_6("classic-0.0.15a_mt_6"),
	CLASSIC_0_0_15A_MT_7("classic-0.0.15a_mt_7"),
	CLASSIC_0_0_15A_MT_8("classic-0.0.15a_mt_8"),
	CLASSIC_0_0_15A_01("classic-0.0.15a_01"),
	CLASSIC_0_0_15A_02("classic-0.0.15a_02"),
	CLASSIC_0_0_15A_03("classic-0.0.15a_03"),
	CLASSIC_0_0_16A("classic-0.0.16a"),
	CLASSIC_0_0_16A_01("classic-0.0.16a_01"),
	CLASSIC_0_0_16A_02("classic-0.0.16a_02"),
	CLASSIC_0_0_17A("classic-0.0.17a"),
	CLASSIC_0_0_18A("classic-0.0.18a"),
	CLASSIC_0_0_18A_01("classic-0.0.18a_01"),
	CLASSIC_0_0_18A_02("classic-0.0.18a_02"),
	CLASSIC_0_0_19A("classic-0.0.19a"),
	CLASSIC_0_0_19A_01("classic-0.0.19a_01"),
	CLASSIC_0_0_19A_02("classic-0.0.19a_02"),
	CLASSIC_0_0_19A_03("classic-0.0.19a_03"),
	CLASSIC_0_0_19A_04("classic-0.0.19a_04"),
	CLASSIC_0_0_19A_05("classic-0.0.19a_05"),
	CLASSIC_0_0_19A_06("classic-0.0.19a_06"),
	CLASSIC_0_0_20A("classic-0.0.20a"),
	CLASSIC_0_0_20A_01("classic-0.0.20a_01"),
	CLASSIC_0_0_20A_02("classic-0.0.20a_02"),
	CLASSIC_0_0_21A("classic-0.0.21a"),
	CLASSIC_0_0_21A_01("classic-0.0.21a_01"),
	CLASSIC_0_0_21A_02("classic-0.0.21a_02"),
	CLASSIC_0_0_22A("classic-0.0.22a"),
	CLASSIC_0_0_22A_01("classic-0.0.22a_01"),
	CLASSIC_0_0_22A_02("classic-0.0.22a_02"),
	CLASSIC_0_0_22A_03("classic-0.0.22a_03"),
	CLASSIC_0_0_22A_04("classic-0.0.22a_04"),
	CLASSIC_0_0_22A_05("classic-0.0.22a_05"),
	CLASSIC_0_0_23A("classic-0.0.23a"),
	CLASSIC_0_0_23A_01("classic-0.0.23a_01"),
	CLASSIC_0_24_ST("classic-0.24_st"),
	CLASSIC_0_24A_ST_01("classic-0.24a_st_01"),
	CLASSIC_0_24A_ST_02("classic-0.24a_st_02"),
	CLASSIC_0_24A_ST_03("classic-0.24a_st_03"),
	CLASSIC_0_25_ST("classic-0.25_st"),
	CLASSIC_0_25A_ST_02("classic-0.25a_st_02"),
	CLASSIC_0_25A_ST_03("classic-0.25a_st_03"),
	CLASSIC_0_25A_ST_04("classic-0.25a_st_04"),
	CLASSIC_0_25A_ST_05("classic-0.25a_st_05"),
	CLASSIC_0_26_ST("classic-0.26_st"),
	CLASSIC_0_27_ST("classic-0.27_st"),
	CLASSIC_0_28("classic-0.28"),
	CLASSIC_0_29("classic-0.29"),
	CLASSIC_0_29A_01("classic-0.29a_01"),
	CLASSIC_0_29A_02("classic-0.29a_02"),
	CLASSIC_0_30("classic-0.30"),
	INDEV_0_31_20091223_0040("indev-0.31_20091223-0040"),
	INDEV_0_31_20091229_1457("indev-0.31_20091229-1457"),
	INDEV_0_31_20091229_1459("indev-0.31_20091229-1459"),
	INDEV_0_31_20091231_1856("indev-0.31_20091231-1856"),
	INDEV_0_31_20091231_2004("indev-0.31_20091231-2004"),
	INDEV_0_31_20091231_2013("indev-0.31_20091231-2013"),
	INDEV_0_31_20091231_2033("indev-0.31_20091231-2033"),
	INDEV_0_31_20091231_2047("indev-0.31_20091231-2047"),
	INDEV_0_31_20091231_2255("indev-0.31_20091231-2255"),
	INDEV_0_31_20100104_2154("indev-0.31_20100104-2154"),
	INDEV_0_31_20100104_2258("indev-0.31_20100104-2258"),
	INDEV_0_31_20100106_1655("indev-0.31_20100106-1655"),
	INDEV_0_31_20100106_2158("indev-0.31_20100106-2158"),
	INDEV_0_31_20100106_2220("indev-0.31_20100106-2220"),
	INDEV_0_31_20100107_1851("indev-0.31_20100107-1851"),
	INDEV_0_31_20100107_1947("indev-0.31_20100107-1947"),
	INDEV_0_31_20100107_2010("indev-0.31_20100107-2010"),
	INDEV_0_31_20100109_1939("indev-0.31_20100109-1939"),
	INDEV_0_31_20100109_2000("indev-0.31_20100109-2000"),
	INDEV_0_31_20100110("indev-0.31_20100110"),
	INDEV_0_31_20100111_2207("indev-0.31_20100111-2207"),
	INDEV_0_31_20100111_2210("indev-0.31_20100111-2210"),
	INDEV_0_31_20100112_0826("indev-0.31_20100112-0826"),
	INDEV_0_31_20100112_1949("indev-0.31_20100112-1949"),
	INDEV_0_31_20100113_2015("indev-0.31_20100113-2015"),
	INDEV_0_31_20100113_2244("indev-0.31_20100113-2244"),
	INDEV_0_31_20100114("indev-0.31_20100114"),
	INDEV_0_31_20100122_2251("indev-0.31_20100122-2251"),
	INDEV_0_31_20100124_2119("indev-0.31_20100124-2119"),
	INDEV_0_31_20100124_2134("indev-0.31_20100124-2134"),
	INDEV_0_31_20100124_2310("indev-0.31_20100124-2310"),
	INDEV_0_31_20100125("indev-0.31_20100125"),
	INDEV_0_31_20100128_2200("indev-0.31_20100128-2200"),
	INDEV_0_31_20100128_2304("indev-0.31_20100128-2304"),
	INDEV_0_31_20100129_1447("indev-0.31_20100129-1447"),
	INDEV_0_31_20100129_1452("indev-0.31_20100129-1452"),
	INDEV_0_31_20100129_2029("indev-0.31_20100129-2029"),
	INDEV_0_31_20100129_2134("indev-0.31_20100129-2134"),
	INDEV_0_31_20100129_2138("indev-0.31_20100129-2138"),
	INDEV_0_31_20100129_2158("indev-0.31_20100129-2158"),
	INDEV_0_31_20100129_2209("indev-0.31_20100129-2209"),
	INDEV_0_31_20100129_2332("indev-0.31_20100129-2332"),
	INDEV_0_31_20100130("indev-0.31_20100130"),
	INDEV_0_31_20100131_2156("indev-0.31_20100131-2156"),
	INDEV_0_31_20100131_2241("indev-0.31_20100131-2241"),
	INDEV_0_31_20100131_2244("indev-0.31_20100131-2244"),
	INDEV_0_31_20100201_0025("indev-0.31_20100201-0025"),
	INDEV_0_31_20100201_2227("indev-0.31_20100201-2227"),
	INDEV_0_31_20100202("indev-0.31_20100202"),
	INDEV_0_31_20100204_1524("indev-0.31_20100204-1524"),
	INDEV_0_31_20100204_2027("indev-0.31_20100204-2027"),
	INDEV_0_31_20100204_2153("indev-0.31_20100204-2153"),
	INDEV_0_31_20100205_2241("indev-0.31_20100205-2241"),
	INDEV_0_31_20100206_1437("indev-0.31_20100206-1437"),
	INDEV_20100206_2034("indev-20100206-2034"),
	INDEV_20100206_2103("indev-20100206-2103"),
	INDEV_20100207_1057("indev-20100207-1057"),
	INDEV_20100207_1101("indev-20100207-1101"),
	INDEV_20100207_1647("indev-20100207-1647"),
	INDEV_20100207_1703("indev-20100207-1703"),
	INDEV_20100211_2327("indev-20100211-2327"),
	INDEV_20100211_2333("indev-20100211-2333"),
	INDEV_20100211_2340("indev-20100211-2340"),
	INDEV_20100212_1210("indev-20100212-1210"),
	INDEV_20100212_1622("indev-20100212-1622"),
	INDEV_20100213("indev-20100213"),
	INDEV_20100214("indev-20100214"),
	INDEV_20100218_0011("indev-20100218-0011"),
	INDEV_20100218_0016("indev-20100218-0016"),
	INDEV_20100219("indev-20100219"),
	INDEV_20100223("indev-20100223"),
	INFDEV_20100227_1414("infdev-20100227-1414"),
	INFDEV_20100227_1433("infdev-20100227-1433"),
	INFDEV_20100313("infdev-20100313"),
	INFDEV_20100316("infdev-20100316"),
	INFDEV_20100320("infdev-20100320"),
	INFDEV_20100321("infdev-20100321"),
	INFDEV_20100325_1545("infdev-20100325-1545"),
	INFDEV_20100325_1640("infdev-20100325-1640"),
	INFDEV_20100327("infdev-20100327"),
	INFDEV_20100330_1203("infdev-20100330-1203"),
	INFDEV_20100330_1511("infdev-20100330-1511"),
	INFDEV_20100413_1949("infdev-20100413-1949"),
	INFDEV_20100413_1953("infdev-20100413-1953"),
	INFDEV_20100414("infdev-20100414"),
	INFDEV_20100415("infdev-20100415"),
	INFDEV_20100420("infdev-20100420"),
	INFDEV_20100607("infdev-20100607"),
	INFDEV_20100608("infdev-20100608"),
	INFDEV_20100611("infdev-20100611"),
	INFDEV_20100615("infdev-20100615"),
	INFDEV_20100616_1808("infdev-20100616-1808"),
	INFDEV_20100616_2210("infdev-20100616-2210"),
	INFDEV_20100617_1205("infdev-20100617-1205"),
	INFDEV_20100617_1531("infdev-20100617-1531"),
	INFDEV_20100618("infdev-20100618"),
	INFDEV_20100624("infdev-20100624"),
	INFDEV_20100625_0922("infdev-20100625-0922"),
	INFDEV_20100625_1917("infdev-20100625-1917"),
	INFDEV_20100627("infdev-20100627"),
	INFDEV_20100629("infdev-20100629"),
	INFDEV_20100630_1340("infdev-20100630-1340"),
	INFDEV_20100630_1825("infdev-20100630-1825"),
	ALPHA_1_0_0("alpha-1.0.0"),
	ALPHA_1_0_1("alpha-1.0.1"),
	ALPHA_1_0_1_01("alpha-1.0.1_01"),
	ALPHA_1_0_2("alpha-1.0.2"),
	ALPHA_1_0_2_01("alpha-1.0.2_01"),
	ALPHA_1_0_2_02("alpha-1.0.2_02"),
	ALPHA_1_0_3("alpha-1.0.3"),
	ALPHA_1_0_4("alpha-1.0.4"),
	ALPHA_1_0_5("alpha-1.0.5"),
	ALPHA_1_0_5_01("alpha-1.0.5_01"),
	ALPHA_1_0_6("alpha-1.0.6"),
	ALPHA_1_0_6_01("alpha-1.0.6_01"),
	ALPHA_1_0_6_02("alpha-1.0.6_02"),
	ALPHA_1_0_6_03("alpha-1.0.6_03"),
	ALPHA_1_0_7("alpha-1.0.7"),
	ALPHA_1_0_8("alpha-1.0.8"),
	ALPHA_1_0_8_01("alpha-1.0.8_01"),
	ALPHA_1_0_9("alpha-1.0.9"),
	ALPHA_1_0_10("alpha-1.0.10"),
	ALPHA_1_0_11("alpha-1.0.11"),
	ALPHA_1_0_12("alpha-1.0.12"),
	ALPHA_1_0_13("alpha-1.0.13"),
	ALPHA_1_0_13_01("alpha-1.0.13_01"),
	ALPHA_1_0_14("alpha-1.0.14"),
	ALPHA_1_0_15("alpha-1.0.15"),
	ALPHA_1_0_16("alpha-1.0.16"),
	ALPHA_1_0_16_01("alpha-1.0.16_01"),
	ALPHA_1_0_16_02("alpha-1.0.16_02"),
	ALPHA_1_0_17("alpha-1.0.17"),
	ALPHA_1_0_17_01("alpha-1.0.17_01"),
	ALPHA_1_0_17_02("alpha-1.0.17_02"),
	ALPHA_1_0_17_04("alpha-1.0.17_04"),
	ALPHA_1_1_0("alpha-1.1.0"),
	ALPHA_1_1_1("alpha-1.1.1"),
	ALPHA_1_1_2("alpha-1.1.2"),
	ALPHA_1_1_2_01("alpha-1.1.2_01"),
	ALPHA_1_2_0("alpha-1.2.0"),
	ALPHA_1_2_0_01("alpha-1.2.0_01"),
	ALPHA_1_2_0_02("alpha-1.2.0_02"),
	ALPHA_1_2_1("alpha-1.2.1"),
	ALPHA_1_2_1_01("alpha-1.2.1_01"),
	ALPHA_1_2_2("alpha-1.2.2"),
	ALPHA_1_2_3("alpha-1.2.3"),
	ALPHA_1_2_3_01("alpha-1.2.3_01"),
	ALPHA_1_2_3_02("alpha-1.2.3_02"),
	ALPHA_1_2_3_04("alpha-1.2.3_04"),
	ALPHA_1_2_3_05("alpha-1.2.3_05"),
	ALPHA_1_2_4_01("alpha-1.2.4_01"),
	ALPHA_1_2_5("alpha-1.2.5"),
	ALPHA_1_2_6("alpha-1.2.6"),
	BETA_1_0("beta-1.0"),
	BETA_1_0_01("beta-1.0_01"),
	BETA_1_0_2("beta-1.0.2"),
	BETA_1_1("beta-1.1"),
	BETA_1_1_01("beta-1.1_01"),
	BETA_1_1_02("beta-1.1_02"),
	BETA_1_2("beta-1.2"),
	BETA_1_2_01("beta-1.2_01"),
	BETA_1_2_02("beta-1.2_02"),
	BETA_1_2_02_20110517("beta-1.2_02-20110517"),
	BETA_1_3("beta-1.3"),
	BETA_1_3_01("beta-1.3_01"),
	BETA_1_3_01_PCGAMER("beta-1.3_01_pcgamer"),
	BETA_1_4("beta-1.4"),
	BETA_1_4_01("beta-1.4_01"),
	BETA_1_5("beta-1.5"),
	BETA_1_5_01("beta-1.5_01"),
	BETA_1_5_02("beta-1.5_02"),
	BETA_1_6("beta-1.6"),
	BETA_1_6_1("beta-1.6.1"),
	BETA_1_6_2("beta-1.6.2"),
	BETA_1_6_3("beta-1.6.3"),
	BETA_1_6_4("beta-1.6.4"),
	BETA_1_6_5("beta-1.6.5"),
	BETA_1_6_6("beta-1.6.6"),
	BETA_1_7("beta-1.7"),
	BETA_1_7_01("beta-1.7_01"),
	BETA_1_7_2("beta-1.7.2"),
	BETA_1_8("beta-1.8"),
	BETA_1_8_1("beta-1.8.1"),
	RELEASE_1_0_0("release-1.0.0"),
	RELEASE_1_0_1("release-1.0.1"),
	RELEASE_1_1("release-1.1"),
	RELEASE_1_2_1("release-1.2.1"),
	RELEASE_1_2_2("release-1.2.2"),
	RELEASE_1_2_3("release-1.2.3"),
	RELEASE_1_2_4("release-1.2.4"),
	RELEASE_1_2_5("release-1.2.5"),
	RELEASE_1_3_1("release-1.3.1"),
	RELEASE_1_3_2("release-1.3.2"),
	RELEASE_1_4_2("release-1.4.2"),
	RELEASE_1_4_4("release-1.4.4"),
	RELEASE_1_4_5("release-1.4.5"),
	RELEASE_1_4_6("release-1.4.6"),
	RELEASE_1_4_7("release-1.4.7"),
	RELEASE_1_5("release-1.5"),
	RELEASE_1_5_1("release-1.5.1"),
	RELEASE_1_5_2("release-1.5.2"),
	RELEASE_1_6_1("release-1.6.1"),
	RELEASE_1_6_2("release-1.6.2"),
	RELEASE_1_6_4("release-1.6.4"),
	RELEASE_1_7_2("release-1.7.2"),
	RELEASE_1_7_4("release-1.7.4"),
	RELEASE_1_7_5("release-1.7.5"),
	RELEASE_1_7_6("release-1.7.6"),
	RELEASE_1_7_7("release-1.7.7"),
	RELEASE_1_7_8("release-1.7.8"),
	RELEASE_1_7_9("release-1.7.9"),
	RELEASE_1_7_10("release-1.7.10"),
	RELEASE_1_8("release-1.8"),
	RELEASE_1_8_1("release-1.8.1"),
	RELEASE_1_8_2("release-1.8.2"),
	RELEASE_1_8_3("release-1.8.3"),
	RELEASE_1_8_4("release-1.8.4"),
	RELEASE_1_8_5("release-1.8.5"),
	RELEASE_1_8_6("release-1.8.6"),
	RELEASE_1_8_7("release-1.8.7"),
	RELEASE_1_8_8("release-1.8.8"),
	RELEASE_1_8_9("release-1.8.9"),
	RELEASE_1_9("release-1.9"),
	RELEASE_1_9_1("release-1.9.1"),
	RELEASE_1_9_2("release-1.9.2"),
	RELEASE_1_9_3("release-1.9.3"),
	RELEASE_1_9_4("release-1.9.4"),
	RELEASE_1_10("release-1.10"),
	RELEASE_1_10_1("release-1.10.1"),
	RELEASE_1_10_2("release-1.10.2"),
	RELEASE_1_11("release-1.11"),
	RELEASE_1_11_1("release-1.11.1"),
	RELEASE_1_11_2("release-1.11.2"),
	RELEASE_1_12("release-1.12"),
	RELEASE_1_12_1("release-1.12.1"),
	RELEASE_1_12_2("release-1.12.2"),
	RELEASE_1_13("release-1.13"),
	RELEASE_1_13_1("release-1.13.1"),
	RELEASE_1_14("release-1.14"),
	RELEASE_1_14_1("release-1.14.1"),
	RELEASE_1_14_2("release-1.14.2"),
	RELEASE_1_14_3("release-1.14.3"),
	RELEASE_1_14_4("release-1.14.4"),
	RELEASE_1_15("release-1.15"),
	RELEASE_1_15_1("release-1.15.1"),
	RELEASE_1_15_2("release-1.15.2"),
	RELEASE_1_16("release-1.16"),
	RELEASE_1_16_1("release-1.16.1"),
	RELEASE_1_16_2("release-1.16.2"),
	RELEASE_1_16_3("release-1.16.3"),
	RELEASE_1_16_4("release-1.16.4"),
	RELEASE_1_16_5("release-1.16.5"),
	RELEASE_1_17("release-1.17"),
	RELEASE_1_17_1("release-1.17.1"),
	RELEASE_1_18("release-1.18"),
	RELEASE_1_18_1("release-1.18.1"),
	RELEASE_1_18_2("release-1.18.2"),
	RELEASE_1_19("release-1.19"),
	RELEASE_1_19_1("release-1.19.1"),
	RELEASE_1_19_2("release-1.19.2"),
	RELEASE_1_19_3("release-1.19.3"),
	RELEASE_1_19_4("release-1.19.4"),
	RELEASE_1_20("release-1.20"),
	RELEASE_1_20_1("release-1.20.1"),
	RELEASE_1_20_2("release-1.20.2"),
	RELEASE_1_20_3("release-1.20.3"),
	RELEASE_1_20_4("release-1.20.4"),
	RELEASE_1_20_5("release-1.20.5"),
	RELEASE_1_20_6("release-1.20.6"),
	RELEASE_1_21("release-1.21"),
	RELEASE_1_21_1("release-1.21.1"),
	RELEASE_1_21_2("release-1.21.2"),
	RELEASE_1_21_3("release-1.21.3"),
	RELEASE_1_21_4("release-1.21.4"),
	RELEASE_1_21_5("release-1.21.5"),
	RELEASE_1_21_6("release-1.21.6"),
	RELEASE_1_21_7("release-1.21.7"),
	RELEASE_1_21_8("release-1.21.8"),
	RELEASE_1_21_9("release-1.21.9"),
	RELEASE_1_21_10("release-1.21.10"),
	RELEASE_1_21_11("release-1.21.11");

	private final String versionString;

	MCVersion(String str){ versionString = str; }

	@Override
	public int compare(MCVersion a, MCVersion b){ return Integer.compare(a.ordinal(), b.ordinal()); }

	@Nullable
	public static MCVersion byString(String str){
		for(MCVersion version: values()) if(version.getVersionString().equals(str)) return version;
		return null;
	}

	public String getVersionString(){ return versionString; }

	@Nullable
	public int getPackFormat(){
		switch(this){
			case RELEASE_1_6_1:
			case RELEASE_1_6_2:
			case RELEASE_1_6_4:
			case RELEASE_1_7_2:
			case RELEASE_1_7_4:
			case RELEASE_1_7_5:
			case RELEASE_1_7_6:
			case RELEASE_1_7_7:
			case RELEASE_1_7_8:
			case RELEASE_1_7_9:
			case RELEASE_1_7_10:
			case RELEASE_1_8:
			case RELEASE_1_8_1:
			case RELEASE_1_8_2:
			case RELEASE_1_8_3:
			case RELEASE_1_8_4:
			case RELEASE_1_8_5:
			case RELEASE_1_8_6:
			case RELEASE_1_8_7:
			case RELEASE_1_8_8:
			case RELEASE_1_8_9:
				return 1;
			case RELEASE_1_9:
			case RELEASE_1_9_1:
			case RELEASE_1_9_2:
			case RELEASE_1_9_3:
			case RELEASE_1_9_4:
			case RELEASE_1_10:
			case RELEASE_1_10_1:
			case RELEASE_1_10_2:
				return 2;
			case RELEASE_1_11:
			case RELEASE_1_11_1:
			case RELEASE_1_11_2:
			case RELEASE_1_12:
			case RELEASE_1_12_1:
			case RELEASE_1_12_2:
				return 3;
			case RELEASE_1_13:
			case RELEASE_1_13_1:
			case RELEASE_1_14:
			case RELEASE_1_14_1:
			case RELEASE_1_14_2:
			case RELEASE_1_14_3:
			case RELEASE_1_14_4:
				return 4;
			case RELEASE_1_15:
			case RELEASE_1_15_1:
			case RELEASE_1_15_2:
			case RELEASE_1_16:
			case RELEASE_1_16_1:
				return 5;
			case RELEASE_1_16_2:
			case RELEASE_1_16_3:
			case RELEASE_1_16_4:
			case RELEASE_1_16_5:
				return 6;
			case RELEASE_1_17:
			case RELEASE_1_17_1:
				return 7;
			case RELEASE_1_18:
			case RELEASE_1_18_1:
			case RELEASE_1_18_2:
				return 8;
			case RELEASE_1_19:
			case RELEASE_1_19_1:
			case RELEASE_1_19_2:
				return 9;
			case RELEASE_1_19_3:
				return 12;
			case RELEASE_1_19_4:
				return 13;
			case RELEASE_1_20:
			case RELEASE_1_20_1:
				return 15;
			case RELEASE_1_20_2:
				return 18;
			case RELEASE_1_20_3:
			case RELEASE_1_20_4:
				return 22;
			case RELEASE_1_20_5:
			case RELEASE_1_20_6:
				return 32;
			case RELEASE_1_21:
			case RELEASE_1_21_1:
				return 34;
			case RELEASE_1_21_2:
			case RELEASE_1_21_3:
				return 42;
			case RELEASE_1_21_4:
				return 46;
			case RELEASE_1_21_5:
				return 55;
			case RELEASE_1_21_6:
				return 63;
			case RELEASE_1_21_7:
			case RELEASE_1_21_8:
				return 64;
			case RELEASE_1_21_9:
			case RELEASE_1_21_10:
				return 69;
			case RELEASE_1_21_11:
				return 75;
			default:
				return 0;
		}
	}

}
