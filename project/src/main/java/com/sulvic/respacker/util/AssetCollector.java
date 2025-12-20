package com.sulvic.respacker.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sulvic.engine.util.AssetLocation;
import com.sulvic.respacker.compiler.CtmCompiler;
import com.sulvic.respacker.compiler.CtmDeserializer;
import com.sulvic.respacker.compiler.ImageCompiler;
import com.sulvic.respacker.compiler.ImageDeserializer;
import com.sulvic.respacker.lib.CtmFileInfo;

public class AssetCollector{

	@Nullable
	private static InputStream getStreamResource(String name){ return AssetCollector.class.getResourceAsStream(name); }

	@Nullable
	public static BufferedImage getCompilerImage(AssetLocation assetLoc){
		String compImgPath = String.format("/compiler/assets/%s/%s.png", assetLoc.getDomain(), assetLoc.getPath());
		BufferedImage result = null;
		try{
			System.out.println(compImgPath);
			result = ImageIO.read(getStreamResource(compImgPath));
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return result;
	}

	public static ImageCompiler getCompileData(AssetLocation assetLoc){
		String pathFmt = "/compiler/assets/%s/%s/compile.icd";
		String compileFmt = String.format(pathFmt, assetLoc.getDomain(), assetLoc.getPath());
		Class<ImageCompiler.LayerCompiler[]> compilerCls = ImageCompiler.LayerCompiler[].class;
		ImageCompiler result = new ImageCompiler(assetLoc);
		Gson gson = new GsonBuilder().registerTypeAdapter(compilerCls, new ImageDeserializer()).create();
		ImageCompiler.LayerCompiler[] compilers = gson.fromJson(new InputStreamReader(getStreamResource(compileFmt)), compilerCls);
		result.addLayers(compilers);
		return result;
	}

	public static CtmCompiler getCtmCompileData(AssetLocation assetLoc){
		String pathFmt = "/compiler/assets/%s/optifine/ctm/%s/ctm_compile.icd";
		String ctmCompilePath = String.format(pathFmt, assetLoc.getDomain(), assetLoc.getPath());
		Class<CtmFileInfo> infoCls = CtmFileInfo.class;
		Gson gson = new GsonBuilder().registerTypeAdapter(infoCls, new CtmDeserializer()).create();
		CtmFileInfo info = gson.fromJson(new InputStreamReader(getStreamResource(ctmCompilePath)), infoCls);
		info.addBaseLocation(new AssetLocation(assetLoc.getDomain(), String.format("optifine/ctm/%s", assetLoc.getPath())));
		return new CtmCompiler(assetLoc, info);
	}

}
