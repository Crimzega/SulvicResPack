package com.sulvic.respacker.common.world.block;

import com.sulvic.respacker.compiler.ICompiler;
import com.sulvic.respacker.compiler.PackCompiler;
import com.sulvic.respacker.util.AssetCollector;

public class BlockBedrockAsset extends PackCompiler.AssetCompiler{

	public BlockBedrockAsset(){ super("minecraft:textures/blocks/bedrock"); }

	@Override
	public ICompiler<?> getCompiler(String outDomainPath){ return AssetCollector.getImageCompiler(outDomainPath, getInputAssetLocation()); }

}
