package com.sulvic.respacker.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.sulvic.engine.util.AssetLocation;

public class AssetCollector{

	private static final LegacyDeserializer DESERIALIZER = new LegacyDeserializer();

	@Nullable
	private static InputStream getStreamResource(String name){ return AssetCollector.class.getResourceAsStream(name); }

	private static LegacyData getLegacyData(String path){
		Gson gson = new GsonBuilder().registerTypeAdapter(LegacyData.class, DESERIALIZER).create();
		JsonReader reader = new JsonReader(new InputStreamReader(getStreamResource(path)));
		LegacyData result = gson.fromJson(reader, LegacyData.class);
		try{
			reader.close();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return result;
	}

	public static LegacyData getLegacyTerrainData(MCVersion version){ return getLegacyData(String.format("/legacy_structure/terrain/%s.json", version.getGameVersion())); }

	public static LegacyData getLegacyItemData(MCVersion version){ return getLegacyData(String.format("/legacy_structure/items/%s.json", version.getGameVersion())); }

	@Nullable
	public static BufferedImage getProjectImage(AssetLocation assetLoc){
		String pathFmt = "/project/assets/%s/textures/%s.png";
		String projectImgPath = String.format(pathFmt, assetLoc.getDomain(), assetLoc.getPath());
		BufferedImage result = null;
		try{
			result = ImageIO.read(getStreamResource(projectImgPath));
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return result;
	}

	@Nullable
	public static BufferedImage getCtmImage(AssetLocation assetLoc){
		String pathFmt = "/project/assets/%s/optifine/ctm/%s.png";
		String ctmImgPath = String.format(pathFmt, assetLoc.getDomain(), assetLoc.getPath());
		BufferedImage result = null;
		try{
			result = ImageIO.read(getStreamResource(ctmImgPath));
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return result;
	}

	public static Properties getCtmProperties(AssetLocation assetLoc){
		String pathFmt = "/project/assets/%s/optifine/ctm/%s.properties";
		String propertiesPath = String.format(pathFmt, assetLoc.getDomain(), assetLoc.getPath());
		Properties result = new Properties();
		try{
			InputStream stream = getStreamResource(propertiesPath);
			result.load(stream);
			stream.close();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return result;
	}
	
}
