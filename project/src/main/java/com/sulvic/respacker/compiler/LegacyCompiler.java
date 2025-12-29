package com.sulvic.respacker.compiler;

import java.awt.image.BufferedImage;

import javax.annotation.Nullable;

import com.sulvic.engine.util.AssetLocation;
import com.sulvic.respacker.util.AssetCollector;
import com.sulvic.respacker.util.LegacyData;

public class LegacyCompiler{

	private final AssetLocation baseLegacyAsset;
	private final Type legacyType;
	private LegacyData compilerData;

	public LegacyCompiler(AssetLocation assetLoc, Type type){
		baseLegacyAsset = assetLoc;
		legacyType = type;
	}

	public AssetLocation getLegacyAssetLocation(){ return baseLegacyAsset; }

	@Nullable
	public BufferedImage compileLegacy(){
		BufferedImage base = new StitchCompiler(compilerData.getData(" ")).compileStitch(legacyType);
		BufferedImage result = new BufferedImage(base.getWidth() * 16, base.getHeight() * 16, BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < compilerData.size(); i++){
			String entry = compilerData.getEntry(i);
			StitchCompiler compiler = new StitchCompiler(compilerData.getData(entry));
			BufferedImage img = compiler.compileStitch(legacyType);
			int baseX = i % 16, baseY = i / 16;
			for(int x = 0; x < img.getWidth(); x++) for(int y = 0; y < img.getHeight(); y++) result.setRGB(baseX + x, baseY + y, img.getRGB(x, y));
		}
		return result;
	}

	public Type getLegacyType(){ return legacyType; }

	public static class StitchCompiler{

		private final LegacyData.StitchData stitchData;

		public StitchCompiler(LegacyData.StitchData data){ stitchData = data; }

		public BufferedImage compileStitch(Type type){
			AssetLocation assetLoc = stitchData.getAssetLocation();
			String subPath = type == Type.TERRAIN? "blocks": "items";
			AssetLocation finalAssetLoc = new AssetLocation(assetLoc.getDomain(), String.format("%s/%s", subPath, assetLoc.getPath()));
			BufferedImage result = null;
			switch(stitchData.getCompileType()){
				case IMAGE:
//					result = AssetCollector.getCompileData(finalAssetLoc).compileImage();
				break;
				default:
					result = AssetCollector.getCompilerImage(finalAssetLoc);
				break;
			}
			if(result != null){
				boolean asOpaque = stitchData.asOpaque(), useOverrideAlpha = stitchData.usesAlphaOverride(), useIndexOffset = stitchData.usesIndexOffset();
				int posX = useIndexOffset? stitchData.getIndexOffsetX(): 0, posY = useIndexOffset? stitchData.getIndexOffsetY(): 0;
				int maxScanY = useIndexOffset? result.getWidth(): result.getHeight();
				for(int x = 0; x < result.getWidth(); x++) for(int y = 0; y < maxScanY; x++){
					int rgbImg = result.getRGB(posX + x, posY + y);
					if(asOpaque) rgbImg = (rgbImg & 0xFFFFFF) + (0xFF << 24);
					else if(useOverrideAlpha) rgbImg = (rgbImg & 0xFFFFFF) + (stitchData.getAlphaOverride() << 24);
					result.setRGB(x, y, rgbImg);
				}
			}
			return result;
		}

	}

	public static enum Type{

		TERRAIN,
		ITEMS;

		public String getOutputPath(){
			switch(this){
				case ITEMS:
					return "assets/gui/items.png";
				default:
					return "assets/terrain.png";
			}
		}

	}

	public static enum CompileType{

		COPY,
		IMAGE;

		@Nullable
		public static CompileType byMethod(String method){
			for(CompileType type: values()) if(type.getMethod().equals(method)) return type;
			return null;
		}

		public String getMethod(){ return name().toLowerCase(); }

	}

}
