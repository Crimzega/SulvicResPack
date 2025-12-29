package com.sulvic.respacker.common.world.block;

import com.sulvic.respacker.compiler.ICompiler;
import com.sulvic.respacker.compiler.PackCompiler;
import com.sulvic.respacker.util.AssetCollector;

public class BlockDirtAsset extends PackCompiler.AssetCompiler{

	public BlockDirtAsset(){ super("minecraft:textures/blocks/dirt"); }

	@Override
	public ICompiler<?> getCompiler(String outDomainPath){ return AssetCollector.getImageCompiler(outDomainPath, getInputAssetLocation()); }

}
