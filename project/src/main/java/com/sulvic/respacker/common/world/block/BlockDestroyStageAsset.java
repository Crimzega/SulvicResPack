package com.sulvic.respacker.common.world.block;

import com.sulvic.respacker.compiler.ICompiler;
import com.sulvic.respacker.compiler.PackCompiler;
import com.sulvic.respacker.util.AssetCollector;

public class BlockDestroyStageAsset extends PackCompiler.AssetCompiler{

	public BlockDestroyStageAsset(Level lvl){ super(lvl.getStageName()); }

	@Override
	public ICompiler<?> getCompiler(String outDomainPath){ return AssetCollector.getImageCompiler(outDomainPath, getInputAssetLocation()); }

	public static enum Level{
		LEVEL_0("destroy_stage_0"),
		LEVEL_1("destroy_stage_1"),
		LEVEL_2("destroy_stage_2"),
		LEVEL_3("destroy_stage_3"),
		LEVEL_4("destroy_stage_4"),
		LEVEL_5("destroy_stage_5"),
		LEVEL_6("destroy_stage_6"),
		LEVEL_7("destroy_stage_7"),
		LEVEL_8("destroy_stage_8"),
		LEVEL_9("destroy_stage_9");

		private final String stageName;

		Level(String name){ stageName = name; }

		public String getStageName(){ return String.format("minecraft:textures/blocks/%s", stageName); }

	}

}
