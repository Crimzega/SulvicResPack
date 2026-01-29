package com.sulvic.respacker.common.world.block;

import com.sulvic.respacker.compiler.ICompiler;
import com.sulvic.respacker.compiler.PackCompiler;
import com.sulvic.respacker.util.AssetCollector;

public class BlockUnmappedAsset extends PackCompiler.AssetCompiler{

	public BlockUnmappedAsset(){ super("sulvic:textures/blocks/unmapped"); }

	@Override
	public ICompiler<?> getCompiler(String outDomainPath){ return AssetCollector.getImageCopier(outDomainPath, getInputAssetLocation()); }

}
