package com.sulvic.respacker.common;

import java.io.File;

import com.sulvic.respacker.common.world.block.*;
import com.sulvic.respacker.compiler.PackCompiler;
import com.sulvic.respacker.util.MCVersion;

public class PackManager{

	private static final File OUTPUT_DIRECTORY = new File(String.format("%s/compiled", System.getProperty("user.dir", ".")));
	private static final BlockBedrockAsset ASSET_BEDROCK = new BlockBedrockAsset();
	private static final BlockObsidianAsset ASSET_OBSIDIAN = new BlockObsidianAsset();
	private static final BlockDiamondAsset ASSET_DIAMOND_BLOCK = new BlockDiamondAsset();
	private static final BlockDestroyStageAsset ASSET_DESTROY_STAGE_0 = new BlockDestroyStageAsset(BlockDestroyStageAsset.Level.LEVEL_0);
	private static final BlockDestroyStageAsset ASSET_DESTROY_STAGE_1 = new BlockDestroyStageAsset(BlockDestroyStageAsset.Level.LEVEL_1);
	private static final BlockDestroyStageAsset ASSET_DESTROY_STAGE_2 = new BlockDestroyStageAsset(BlockDestroyStageAsset.Level.LEVEL_2);
	private static final BlockDestroyStageAsset ASSET_DESTROY_STAGE_3 = new BlockDestroyStageAsset(BlockDestroyStageAsset.Level.LEVEL_3);
	private static final BlockDestroyStageAsset ASSET_DESTROY_STAGE_4 = new BlockDestroyStageAsset(BlockDestroyStageAsset.Level.LEVEL_4);
	private static final BlockDestroyStageAsset ASSET_DESTROY_STAGE_5 = new BlockDestroyStageAsset(BlockDestroyStageAsset.Level.LEVEL_5);
	private static final BlockDestroyStageAsset ASSET_DESTROY_STAGE_6 = new BlockDestroyStageAsset(BlockDestroyStageAsset.Level.LEVEL_6);
	private static final BlockDestroyStageAsset ASSET_DESTROY_STAGE_7 = new BlockDestroyStageAsset(BlockDestroyStageAsset.Level.LEVEL_7);
	private static final BlockDestroyStageAsset ASSET_DESTROY_STAGE_8 = new BlockDestroyStageAsset(BlockDestroyStageAsset.Level.LEVEL_8);
	private static final BlockDestroyStageAsset ASSET_DESTROY_STAGE_9 = new BlockDestroyStageAsset(BlockDestroyStageAsset.Level.LEVEL_9);
	private static final BlockDirtAsset ASSET_DIRT = new BlockDirtAsset();
	private static final BlockUnmappedAsset ASSET_UNMAPPED_BLOCK = new BlockUnmappedAsset();
	private static PackCompiler basePackCompiler;

	public static void init(){
		basePackCompiler = new PackCompiler("SulvicResPack_1-12-2", MCVersion.RELEASE_1_12_2, OUTPUT_DIRECTORY).setDescription("The base resource pack for Minecraft 1.12.2");
		basePackCompiler.addCompiler("minecraft:textures/blocks/bedrock", ASSET_BEDROCK);
		basePackCompiler.addCompiler("minecraft:textures/blocks/obsidian", ASSET_OBSIDIAN);
		basePackCompiler.addCompiler("minecraft:textures/blocks/diamond_block", ASSET_DIAMOND_BLOCK);
		basePackCompiler.addCompiler("minecraft:textures/blocks/dirt", ASSET_DIRT);
		basePackCompiler.addCompiler("minecraft:textures/blocks/destroy_stage_0", ASSET_DESTROY_STAGE_0);
		basePackCompiler.addCompiler("minecraft:textures/blocks/destroy_stage_1", ASSET_DESTROY_STAGE_1);
		basePackCompiler.addCompiler("minecraft:textures/blocks/destroy_stage_2", ASSET_DESTROY_STAGE_2);
		basePackCompiler.addCompiler("minecraft:textures/blocks/destroy_stage_3", ASSET_DESTROY_STAGE_3);
		basePackCompiler.addCompiler("minecraft:textures/blocks/destroy_stage_4", ASSET_DESTROY_STAGE_4);
		basePackCompiler.addCompiler("minecraft:textures/blocks/destroy_stage_5", ASSET_DESTROY_STAGE_5);
		basePackCompiler.addCompiler("minecraft:textures/blocks/destroy_stage_6", ASSET_DESTROY_STAGE_6);
		basePackCompiler.addCompiler("minecraft:textures/blocks/destroy_stage_7", ASSET_DESTROY_STAGE_7);
		basePackCompiler.addCompiler("minecraft:textures/blocks/destroy_stage_8", ASSET_DESTROY_STAGE_8);
		basePackCompiler.addCompiler("minecraft:textures/blocks/destroy_stage_9", ASSET_DESTROY_STAGE_9);
		basePackCompiler.addCompiler("sulvic:textures/blocks/unmapped", ASSET_UNMAPPED_BLOCK);
	}

	public static void compilePacks(){ basePackCompiler.compile(); }

}
