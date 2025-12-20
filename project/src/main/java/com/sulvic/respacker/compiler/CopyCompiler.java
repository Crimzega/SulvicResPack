package com.sulvic.respacker.compiler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sulvic.engine.util.AssetLocation;
import com.sulvic.respacker.util.AssetCollector;

public class CopyCompiler{

	private final AssetLocation baseCopyAsset;

	public CopyCompiler(AssetLocation assetLoc){ baseCopyAsset = assetLoc; }

	public BufferedImage compileCopy(File outputDir){
		String pathFmt = "assets/%s/%s.png";
		String pathDir = String.format(pathFmt, baseCopyAsset.getDomain(), baseCopyAsset.getPath());
		BufferedImage result = AssetCollector.getCompilerImage(baseCopyAsset);
		try{
			File file = new File(outputDir, pathDir);
			if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
			ImageIO.write(result, "png", file);
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return result;
	}

}
