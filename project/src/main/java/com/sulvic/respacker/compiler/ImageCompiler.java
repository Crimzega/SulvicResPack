package com.sulvic.respacker.compiler;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.sulvic.color.EnumBlendMode;
import com.sulvic.engine.util.AssetLocation;
import com.sulvic.respacker.util.AssetCollector;

public class ImageCompiler{

	private AssetLocation imgAssetLocation;
	private List<LayerCompiler> layerCompilers = Lists.newArrayList();

	public ImageCompiler(AssetLocation assetLoc){ imgAssetLocation = assetLoc; }

	public ImageCompiler addLayers(LayerCompiler... compilers){
		layerCompilers.addAll(Lists.newArrayList(compilers));
		return this;
	}

	@Nullable
	public BufferedImage compileImage(){
		if(layerCompilers.isEmpty()) return null;
		BufferedImage base = layerCompilers.get(0).getLayerImage(imgAssetLocation);
		BufferedImage result = new BufferedImage(base.getWidth(), base.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int y = 0; y < result.getHeight(); y++) for(int x = 0; x < result.getWidth(); x++) result.setRGB(x, y, 0xFF000000);
		for(int i = 0; i < layerCompilers.size(); i++){
			LayerCompiler compiler = layerCompilers.get(i);
			BufferedImage img = compiler.getLayerImage(imgAssetLocation);
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

	public static class LayerCompiler{

		private final String layerName;
		private EnumBlendMode theBlendMode = EnumBlendMode.NORMAL;
		private int theOpacity = 255;

		public LayerCompiler(String name, EnumBlendMode mode){
			layerName = name;
			theBlendMode = mode;
		}

		public LayerCompiler(String name, EnumBlendMode mode, int opacity){
			this(name, mode);
			theOpacity = opacity;
		}

		public EnumBlendMode getBlendMode(){ return theBlendMode; }

		public int getOpacity(){ return theOpacity; }

		public BufferedImage getLayerImage(AssetLocation assetLoc){
			return AssetCollector.getCompilerImage(new AssetLocation(assetLoc.getDomain(), String.format("%s/%s", assetLoc.getPath(), layerName)));
		}

	}

}
