package com.sulvic.respacker.common.world.block;

import com.sulvic.respacker.compiler.ICompiler;
import com.sulvic.respacker.compiler.PackCompiler;
import com.sulvic.respacker.util.AssetCollector;

public class BlockDiamondAsset extends PackCompiler.AssetCompiler{

	public BlockDiamondAsset(){ super("minecraft:textures/blocks/diamond_block"); }

	@Override
	public ICompiler<?> getCompiler(String outDomainPath){ return AssetCollector.getImageCompiler(outDomainPath, getInputAssetLocation()); }

}
