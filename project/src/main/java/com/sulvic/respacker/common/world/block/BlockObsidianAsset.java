package com.sulvic.respacker.common.world.block;

import com.sulvic.respacker.compiler.ICompiler;
import com.sulvic.respacker.compiler.PackCompiler;
import com.sulvic.respacker.util.AssetCollector;

public class BlockObsidianAsset extends PackCompiler.AssetCompiler{

	public BlockObsidianAsset(){ super("minecraft:textures/blocks/obsidian"); }

    @Override
	public ICompiler<?> getCompiler(String outDomainPath){ return AssetCollector.getImageCompiler(outDomainPath, getInputAssetLocation()); }

}
