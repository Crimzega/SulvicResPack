package com.sulvic.respacker;

import com.sulvic.respacker.common.PackManager;
import com.sulvic.respacker.util.MCVersion;

public class ResPacker{

	public static void main(String[] args) throws Exception{
//		AssetManager.compileAssets();
		PackManager.init(MCVersion.RELEASE_1_12_2);
		PackManager.compilePacks();
	}

}
