package com.sulvic.respacker.compiler;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.sulvic.engine.util.AssetLocation;
import com.sulvic.io.SulvicIO;
import com.sulvic.respacker.util.MCVersion;
import com.sulvic.util.*;

@SuppressWarnings({"CallToPrintStackTrace"})
public class PackCompiler{

	private final Map<String, PackCompiler.AssetCompiler> compilers = ContentBuilder.newHashMap();
	private final File packDirOutput;
	private final MCVersion gameVersion;
	private final String packName;
	private String packDescrption;

	public PackCompiler(String name, MCVersion version, File dirOut){
		packName = name;
		gameVersion = version;
		packDirOutput = dirOut;
	}

	public boolean hasDescription(){ return !StringHelper.isNullOrEmpty(packDescrption); }

	public File getOutputDirectory(){ return packDirOutput; }

	public MCVersion getGameVersion(){ return gameVersion; }

	public PackCompiler addCompiler(String domainPath, PackCompiler.AssetCompiler compiler){
		compilers.put(domainPath, compiler);
		return this;
	}

	public PackCompiler setDescription(String desc){
		packDescrption = desc;
		return this;
	}

	public String getName(){ return packName; }

	public String getDescription(){ return hasDescription()? packDescrption: ""; }

	public void compile(){
		Gson gson = new GsonBuilder().registerTypeAdapter(getClass(), new PackSerializer()).create();
		File folder = new File(packDirOutput, packName);
		if(!folder.exists()) folder.mkdirs();
		try{
			JsonWriter jsonWriter = new JsonWriter(new FileWriter(new File(folder, "pack.mcmeta")));
			gson.toJson(this, getClass(), jsonWriter);
			SulvicIO.closeQuietly(jsonWriter);
			for(String domainPath: compilers.keySet()){
				PackCompiler.AssetCompiler assetCompiler = compilers.get(domainPath);
				AssetLocation outAssetLoc = new AssetLocation(domainPath);
				ICompiler<?> compiler = assetCompiler.getCompiler(domainPath);
				if(compiler != null){
					Object obj = compiler.compile();
					if(obj instanceof BufferedImage){
						File imgFile = new File(folder, String.format("assets/%s/%s.png", outAssetLoc.getDomain(), outAssetLoc.getPath()));
						if(!imgFile.getParentFile().exists()) imgFile.getParentFile().mkdirs();
						ImageIO.write((BufferedImage)obj, "png", imgFile);
					}
					else if(obj instanceof Map){
	
					}
				}
			}
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

	public abstract static class AssetCompiler{

		private final AssetLocation inputAssetLocation;

		public AssetCompiler(String domainPath){ inputAssetLocation = new AssetLocation(domainPath); }

		public abstract ICompiler<?> getCompiler(String outDomainPath);

		public AssetLocation getInputAssetLocation(){ return inputAssetLocation; }

	}

	private static class PackSerializer implements JsonSerializer<PackCompiler>{

        @Override
        public JsonElement serialize(PackCompiler compiler, Type t, JsonSerializationContext ctx) {
			JsonObject result = new JsonObject();
			JsonObject packJson = new JsonObject();
			packJson.addProperty("description", compiler.getDescription());
			packJson.addProperty("pack_format", compiler.getGameVersion().getPackFormat());
			result.add("pack", packJson);
            return result;
        }

	}

}
