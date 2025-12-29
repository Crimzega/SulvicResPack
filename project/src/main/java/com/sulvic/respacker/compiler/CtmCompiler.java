package com.sulvic.respacker.compiler;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Properties;

import com.google.common.collect.Maps;
import com.sulvic.engine.util.AssetLocation;
import com.sulvic.respacker.lib.CtmFileInfo;

@SuppressWarnings({"unused"})
public abstract class CtmCompiler implements IAssetCompiler<Map<AssetLocation, BufferedImage>>{

	private final AssetLocation ctmAssetLocation;
	private final CtmFileInfo ctmFileInfo;

	public CtmCompiler(AssetLocation assetLoc, CtmFileInfo info){
		ctmAssetLocation = assetLoc;
		ctmFileInfo = info;
	}

	@Override
	public Map<AssetLocation, BufferedImage> compile(){
		Map<AssetLocation, BufferedImage> result = Maps.newHashMap();
		for(String entry: ctmFileInfo){
			AssetLocation assetLoc = new AssetLocation(ctmAssetLocation.getDomain(), String.format("optifine/ctm/%s/%s", ctmAssetLocation.getPath(), entry));
//			result.put(assetLoc, AssetCollector.getCompileData(assetLoc).compileImage());
		}
		return result;
	}

	public Properties getProperties(){ return ctmFileInfo.getProperties(); }

	public String getPropertesFilename(){ return ctmFileInfo.getFilename(); }

}
