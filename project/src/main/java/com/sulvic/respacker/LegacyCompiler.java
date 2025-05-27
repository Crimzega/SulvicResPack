package com.sulvic.respacker;

import java.awt.image.BufferedImage;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.sulvic.respacker.util.AssetCollector;
import com.sulvic.respacker.util.LegacyData;
import com.sulvic.respacker.util.MCVersion;

public class LegacyCompiler{

	private static final String UNKNOWN_BLOCK = "sulvic:unknown_block", UNKNOWN_ITEM = "sulvic:unknown_item";
	private TextureStitcher terrainStitcher, itemsStitcher;
	private MCVersion mcVersion;

	public LegacyCompiler(MCVersion version){
		LegacyData terrainData = AssetCollector.getLegacyTerrainData(version), itemsData = AssetCollector.getLegacyItemData(version);
		terrainStitcher = new TextureStitcher(UNKNOWN_BLOCK, terrainData);
		itemsStitcher = new TextureStitcher(UNKNOWN_ITEM, itemsData);
		mcVersion = version;
	}

	public void compile(){
		terrainStitcher.stitch();
		itemsStitcher.stitch();
	}

	private static class TextureStitcher{

		private LegacyData.AssetData mainTextureData;
		private LegacyData textureData;

		public TextureStitcher(String defString, LegacyData data){
			mainTextureData = new LegacyData.AssetData(defString);
			textureData = data;
		}

		@Nullable
		private BufferedImage compileImage(LegacyData.AssetData data){
			BufferedImage result = null;
			if(data != null){
				BufferedImage img = AssetCollector.getProjectImage(data.getAssetLocation());
				if(data.usesIndexOffset()){
					int[] offset = data.getIndexOffset();
					img = img.getSubimage(0, 0, img.getWidth() * offset[0], img.getWidth() * offset[1]);
				}
				if(data.usesAlphaOverride() || data.asOpqaue()) for(int y = 0; y < img.getHeight(); y++) for(int x = 0; x < img.getWidth(); x++){
					int argb = img.getRGB(x, y);
					int alpha = (argb >> 24) & 0xFF;
					alpha = data.asOpqaue()? 255: data.usesAlphaOverride() && alpha > 0? data.getAlphaOverride(): (argb >> 24) & 0xFF;
					argb = (alpha << 24) | (argb & 0xFFFFFF);
					img.setRGB(x, y, alpha == 0? 0x00000000: argb);
				}
				result = img;
			}
			return result;
		}

		public void stitch(){
			BufferedImage mainTex = compileImage(mainTextureData);
			BufferedImage finalImg = new BufferedImage(mainTex.getWidth() * 16, mainTex.getHeight() * 16, BufferedImage.TYPE_INT_ARGB);
			Map<String, BufferedImage> collectedImage = Maps.newHashMap();
			for(String entry: textureData){
				BufferedImage img = compileImage(textureData.getData(entry));
				if(img != null) collectedImage.put(entry, img);
			}
			int index = 0;
			for(String entry: textureData){
				index++;
				int offX = (index % 16) * mainTex.getWidth(), offY = (index / 16) * mainTex.getHeight();
				BufferedImage img = collectedImage.containsKey(entry)? collectedImage.get(entry): mainTex;
				for(int y = 0; y < mainTex.getHeight(); y++) for(int x = 0; x < mainTex.getWidth(); x++) finalImg.setRGB(x+offX, y+offY, img.getRGB(x, y));
			}
		}

	}

}
