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
import com.sulvic.respacker.compiler.CopyCompiler;
// import com.sulvic.respacker.compiler.CtmCompiler;
import com.sulvic.respacker.compiler.ImageCompiler;
import com.sulvic.respacker.lib.CtmFileInfo;
// import com.sulvic.respacker.lib.CtmDeserializer;
// import com.sulvic.respacker.lib.CtmFileInfo;
import com.sulvic.respacker.lib.ImageDeserializer;

@SuppressWarnings("CallToPrintStackTrace")
public class AssetCollector{

	@Nullable
	private static InputStream getStreamResource(String name){ return AssetCollector.class.getResourceAsStream(name); }

	@Nullable
	public static BufferedImage getProjectImage(AssetLocation assetLoc){
		String compImgPath = String.format("/project/assets/%s/%s.png", assetLoc.getDomain(), assetLoc.getPath());
		if(!StoredContentSet.hasCollectedImage(compImgPath)){
			BufferedImage result = null;
			try{
				result = ImageIO.read(getStreamResource(compImgPath));
				StoredContentSet.setCollectedImage(compImgPath, result);
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
			return result;
		}
		else return StoredContentSet.getCollectedImage(compImgPath);
	}

	@Nullable
	public static BufferedImage getCompilerImage(AssetLocation assetLoc){
		String compImgPath = String.format("/compiler/assets/%s/%s.png", assetLoc.getDomain(), assetLoc.getPath());
		if(!StoredContentSet.hasCollectedImage(compImgPath)){
			BufferedImage result = null;
			try{
				result = ImageIO.read(getStreamResource(compImgPath));
				StoredContentSet.setCollectedImage(compImgPath, result);
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
			return result;
		}
		else return StoredContentSet.getCollectedImage(compImgPath);
	}

	public static CopyCompiler getImageCopier(String outDomainPath, AssetLocation assetLoc){
		if(!StoredContentSet.hasCollectedCopier(outDomainPath)){
			CopyCompiler result = new CopyCompiler(assetLoc){
	
				@Override
				public AssetLocation getAssetOutput(){ return new AssetLocation(outDomainPath); }
	
			};
			StoredContentSet.setCollectedCopier(outDomainPath, result);
			return result;
		}
		else return StoredContentSet.getCollectedCopier(outDomainPath);
	}

	public static ImageCompiler getImageCompiler(String outDomainPath, AssetLocation assetLoc){
		String compilerPath = String.format("/compiler/assets/%s/%s/compile.icd", assetLoc.getDomain(), assetLoc.getPath());
		if(!StoredContentSet.hasCollectedCompiler(outDomainPath)){
			Class<ImageDeserializer.LayerData[]> compilerCls = ImageDeserializer.LayerData[].class;
			ImageCompiler result = new ImageCompiler(assetLoc){
	
				@Override
				public AssetLocation getAssetOutput(){ return new AssetLocation(outDomainPath); }
	
			};
			Gson gson = new GsonBuilder().registerTypeAdapter(compilerCls, new ImageDeserializer()).create();
			ImageDeserializer.LayerData[] dataArr = gson.fromJson(new InputStreamReader(getStreamResource(compilerPath)), compilerCls);
			result.addLayers(dataArr);
			return result;
		}
		else return StoredContentSet.getCollectedCompiler(outDomainPath);
	}

	public static CtmCompiler getCtmCompiler(String outDomainPath, AssetLocation assetLoc){
		String ctmCompilePath = String.format("/compiler/assets/%s/optifine/ctm/%s/ctm_compile.icd", assetLoc.getDomain(), assetLoc.getPath());
		if(!StoredContentSet.hasCtmCompiler(outDomainPath)){
			Class<CtmFileInfo> infoCls = CtmFileInfo.class;
		}
		else return StoredContentSet.getCtmCompiler(outDomainPath);
	}

//	public static CtmCompiler getCtmCompileData(AssetLocation assetLoc){
//		String pathFmt = "/compiler/assets/%s/optifine/ctm/%s/ctm_compile.icd";
//		String ctmCompilePath = String.format(pathFmt, assetLoc.getDomain(), assetLoc.getPath());
//		Class<CtmFileInfo> infoCls = CtmFileInfo.class;
//		Gson gson = new GsonBuilder().registerTypeAdapter(infoCls, new CtmDeserializer()).create();
//		CtmFileInfo info = gson.fromJson(new InputStreamReader(getStreamResource(ctmCompilePath)), infoCls);
//		info.addBaseLocation(new AssetLocation(assetLoc.getDomain(), String.format("optifine/ctm/%s", assetLoc.getPath())));
//		return new CtmCompiler(assetLoc, info);
//	}

}
