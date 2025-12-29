package com.sulvic.respacker.common;

import java.io.File;

public class AssetManager{

	private static final File OUTPUT_DIRECTORY = new File(String.format("%s/compiled", System.getProperty("user.dir", ".")));

//	private static final CtmCompiler DIAMOND_BLOCK_CTM = AssetCollector.getCtmCompileData(new AssetLocation("minecraft:diamond_block"));
//	private static final ImageCompiler BEDROCK_BLOCK = AssetCollector.getCompileData(new AssetLocation("minecraft:textures/blocks/bedrock"));
//	private static final ImageCompiler DIAMOND_BLOCK = AssetCollector.getCompileData(new AssetLocation("minecraft:textures/blocks/diamond_block"));
//	private static final ImageCompiler DIRT_BLOCK = AssetCollector.getCompileData(new AssetLocation("minecraft:textures/blocks/dirt"));
//	private static final CopyCompiler SULVIC_UNKNOWN = new CopyCompiler(new AssetLocation("sulvic:blocks/unknown_block"));

	public static void compileAssets(){
		if(!OUTPUT_DIRECTORY.exists()) OUTPUT_DIRECTORY.mkdirs();
//		DIAMOND_BLOCK_CTM.compileCtm(OUTPUT_DIRECTORY);
//		BEDROCK_BLOCK.compileImage(OUTPUT_DIRECTORY);
//		DIAMOND_BLOCK.compileImage(OUTPUT_DIRECTORY);
//		DIRT_BLOCK.compileImage(OUTPUT_DIRECTORY);
//		SULVIC_UNKNOWN.compileCopy(OUTPUT_DIRECTORY);
	}

}
