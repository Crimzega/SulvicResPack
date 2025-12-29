package com.sulvic.respacker.compiler;

import java.awt.image.BufferedImage;
import java.util.List;

import com.google.common.collect.Lists;
import com.sulvic.color.EnumBlendMode;
import com.sulvic.engine.util.AssetLocation;
import com.sulvic.respacker.lib.ImageDeserializer;
import com.sulvic.respacker.util.AssetCollector;

public abstract class ImageCompiler implements IAssetCompiler<BufferedImage>{

	private final AssetLocation dataAssetLocation;
	private final List<BlendLayerCompiler> layerCompilers = Lists.newArrayList();
	private final BufferedImage baseLayer;

	public ImageCompiler(AssetLocation assetLoc){
		dataAssetLocation = assetLoc;
		AssetLocation assetLoc1 = new AssetLocation(assetLoc.getDomain(), String.format("%s/base", assetLoc.getPath()));
		baseLayer = AssetCollector.getCompilerImage(assetLoc1);
	}

    public AssetLocation getDataAssetLocation(){ return dataAssetLocation; }

	public ImageCompiler addLayers(ImageDeserializer.LayerData... dataArr){
		for(ImageDeserializer.LayerData data: dataArr) layerCompilers.add(new BlendLayerCompiler(this, data.getLayerName(), data.getBlendMode(), data.getLayerOpacity()));
		return this;
	}

	@Override
	public BufferedImage compile(){
		if(layerCompilers.isEmpty()) return null;
		BufferedImage result = baseLayer;
		for(int i = 0; i < layerCompilers.size(); i++){
			BlendLayerCompiler compiler = layerCompilers.get(i);
			BufferedImage img = compiler.getLayerImage(dataAssetLocation);
			EnumBlendMode blendMode = compiler.getBlendMode();
			float alpha = (float)compiler.getOpacity() / 255f;
			for(int y = 0; y < result.getHeight(); y++)for(int x = 0; x < result.getWidth(); x++){
				int rgbBase = result.getRGB(x, y), rgbLayer = img.getRGB(x, y);
				int layerAlpha = (rgbLayer >> 24) & 0xFF;
				if(layerAlpha == 0) continue;
				float effectiveAlpha = layerAlpha * (alpha / 255f);
				int rgbBlend = blendMode.blendColor(rgbBase, rgbLayer, effectiveAlpha);
				result.setRGB(x, y, rgbBlend + (0xFF << 24));
			}
		}
		return result;
	}

	public static class BlendLayerCompiler{

		private final AssetLocation layerAssetLoc;
		private EnumBlendMode theBlendMode = EnumBlendMode.NORMAL;
		private int theOpacity = 255;

		public BlendLayerCompiler(ImageCompiler parent, String name, EnumBlendMode mode){
			layerAssetLoc = new AssetLocation(parent.dataAssetLocation.getDomain(), String.format("%s/%s", parent.dataAssetLocation.getPath(), name));
			theBlendMode = mode;
		}

		public BlendLayerCompiler(ImageCompiler parent, String name, EnumBlendMode mode, int opacity){
			this(parent, name, mode);
			theOpacity = opacity;
		}

		public EnumBlendMode getBlendMode(){ return theBlendMode; }

		public int getOpacity(){ return theOpacity; }

		public BufferedImage getLayerImage(AssetLocation assetLoc){ return AssetCollector.getCompilerImage(layerAssetLoc); }

	}

}
