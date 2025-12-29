package com.sulvic.respacker.common;

import java.io.File;

import com.sulvic.respacker.common.world.block.*;
import com.sulvic.respacker.compiler.PackCompiler;
import com.sulvic.respacker.util.MCVersion;

public class PackManager{

	private static final File OUTPUT_DIRECTORY = new File(String.format("%s/compiled", System.getProperty("user.dir", ".")));
	private static final BlockObsidianAsset OBSIDIAN_ASSET = new BlockObsidianAsset();
	private static final BlockDiamondAsset DIAMOND_BLOCK_ASSET = new BlockDiamondAsset();
	private static final BlockDirtAsset DIRT_ASSET = new BlockDirtAsset();
	private static final BlockUnknownAsset UNKNOWN_BLOCK_ASSET = new BlockUnknownAsset();
	private static PackCompiler basePackCompiler;

	public static void init(MCVersion version){
		basePackCompiler = new PackCompiler("SulvicResPack", version, OUTPUT_DIRECTORY);
		basePackCompiler.addCompiler("minecraft:textures/blocks/obsidian", OBSIDIAN_ASSET);
		basePackCompiler.addCompiler("minecraft:textures/blocks/diamond_block", DIAMOND_BLOCK_ASSET);
		basePackCompiler.addCompiler("minecraft:textures/blocks/dirt", DIRT_ASSET);
		basePackCompiler.addCompiler("sulvic:textures/blocks/unknown", UNKNOWN_BLOCK_ASSET);
	}

	public static void compilePacks(){
		basePackCompiler.compile();
	}

}
