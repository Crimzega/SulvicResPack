package com.sulvic.respacker.common.world.block;

import com.sulvic.respacker.compiler.ICompiler;
import com.sulvic.respacker.compiler.PackCompiler;
import com.sulvic.respacker.util.AssetCollector;

public class BlockUnknownAsset extends PackCompiler.AssetCompiler{

	public BlockUnknownAsset(){ super("sulvic:textures/blocks/unknown"); }

	@Override
	public ICompiler<?> getCompiler(String outDomainPath){ return AssetCollector.getImageCopier(outDomainPath, getInputAssetLocation()); }

}
