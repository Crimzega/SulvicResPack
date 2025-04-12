package com.sulvic.respacker.translocator;

import static com.sulvic.color.EnumBlendMode.*;
import static java.awt.image.BufferedImage.*;

import com.sulvic.engine.util.AssetLocation;
import com.sulvic.io.SulvicIO;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class TranslocatorStitcher{

	private static final AssetLocation MODEL_ASSET = new AssetLocation("translocator", "tex");
	private static BufferedImage fastTransferImg, filterImg, inverterOffImg, inverterOnImg, signalOffImg, signalOnImg, unknownImg;

	private static InputStream getSegmentedResourceStream(String path){
		if(path != null && !path.equals("")){
			String fullPath = String.format("/assets/translocator/segmented/%s.png", path);
			return TranslocatorStitcher.class.getResourceAsStream(fullPath);
		}
		else return null;
	}

	private static BufferedImage getTexture(String state) throws IOException{
		InputStream stream = getSegmentedResourceStream(state);
		if(stream != null){
			BufferedImage result = ImageIO.read(stream);
			stream.close();
			return result;
		}
		else return null;
	}

	private static BufferedImage getFastTransferTexture(FastTransfer state) throws IOException{
		switch(state){
			case USED:
				if(fastTransferImg == null) fastTransferImg = getTexture(state.getState());
				return fastTransferImg;
			default:
				return null;
		}
	}

	private static BufferedImage getFilterTexture(Filter state) throws IOException{
		switch(state){
			case USED:
				if(filterImg == null) filterImg = getTexture(state.getState());
				return filterImg;
			default:
				return null;
		}
	}

	private static BufferedImage getInverterTexture(Inverter state) throws IOException{
		String stateStr = state.getState();
		switch(state){
			case OFF:
				if(inverterOffImg == null) inverterOffImg = getTexture(stateStr);
				return inverterOffImg;
			case ON:
				if(inverterOnImg == null) inverterOnImg = getTexture(stateStr);
				return inverterOnImg;
			case UNKNOWN:
				if(unknownImg == null) unknownImg = getTexture(stateStr);
				return inverterOffImg;
			default:
				return null;
		}
	}

	private static BufferedImage getSignalTexture(Signal state) throws IOException{
		String stateStr = state.getState();
		switch(state){
			case OFF:
				if(signalOffImg == null) signalOffImg = getTexture(stateStr);
				return signalOffImg;
			case ON:
				if(signalOnImg == null) signalOnImg = getTexture(stateStr);
				return signalOnImg;
			default:
				return null;
		}
	}

	private static File getModelPath(File folder){ return new File(folder, String.format("%s/textures/model/%s.png", MODEL_ASSET.getDomain(), MODEL_ASSET.getPath())); }

	public static void compile(File assetsTopPath) throws IOException{
		InputStream itemStream = getSegmentedResourceStream("item"), liquidStream = getSegmentedResourceStream("liquid");
		BufferedImage itemImg = ImageIO.read(itemStream), liquidImg = ImageIO.read(liquidStream);
		SulvicIO.closeQuietly(itemStream, liquidStream);
		File output = getModelPath(assetsTopPath);
		if(!SulvicIO.pathExists(output)) SulvicIO.createFolder(output.getParentFile());
		BufferedImage finalImg = new BufferedImage(itemImg.getWidth() * 64, itemImg.getHeight() * 2, TYPE_INT_ARGB);
		int offsetX = 0;
		for(Signal signal: Signal.values()) for(Filter filter: Filter.values()) for(FastTransfer transfer: FastTransfer.values()) for(Inverter inverter: Inverter.values()){
			BufferedImage finalItemImg = buildFinalImage(itemImg, transfer, filter, inverter, signal),
				finalLiquidImg = buildFinalImage(liquidImg, transfer, filter, inverter, signal);
			for(int x = 0; x < finalItemImg.getWidth(); x++) for(int y = 0; y < finalItemImg.getHeight(); y++){
				finalImg.setRGB(x + offsetX, y, finalItemImg.getRGB(x, y));
				finalImg.setRGB(x+  offsetX, y+64, finalLiquidImg.getRGB(x, y));
			}
			offsetX += 64;
		}
		FileOutputStream streamOut = new FileOutputStream(output);
		ImageIO.write(finalImg, "png", streamOut);
		SulvicIO.closeQuietly(streamOut);
	}

	private static BufferedImage buildFinalImage(BufferedImage imgBase, FastTransfer transfer, Filter filter, Inverter inverter, Signal signal) throws IOException{
		BufferedImage transferImg = getFastTransferTexture(transfer), filterImg = getFilterTexture(filter), inverterImg = getInverterTexture(inverter), 
			signalImg = getSignalTexture(signal);
		BufferedImage result = new BufferedImage(imgBase.getWidth(), imgBase.getHeight(), TYPE_INT_ARGB);
		for(int x = 0; x < imgBase.getWidth(); x++) for(int y = 0; y < imgBase.getHeight(); y++){
			int baseRGB = imgBase.getRGB(x, y), transferRGB = 0x00000000, filterRGB = 0x00000000, inverterRGB = 0x00000000, signalRGB = 0x00000000;
			int resultRGB = baseRGB;
			if(transferImg != null) transferRGB = transferImg.getRGB(x, y);
			if(filterImg != null) filterRGB = filterImg.getRGB(x, y);
			if(inverterImg != null) inverterRGB = inverterImg.getRGB(x, y);
			if(signalImg != null) signalRGB = signalImg.getRGB(x, y);
			float transferAlpha = (float)((transferRGB >> 24) & 0xFF) / 255f, filterAlpha = (float)((filterRGB >> 24) & 0xFF) / 255f,
				inverterAlpha = (float)((inverterRGB >> 24) & 0xFF) / 255f, signalAlpha = (float)((signalRGB >> 24) & 0xFF) / 255f;
			if(transferAlpha > 0f){
				switch(transfer){
					case USED:
						resultRGB = NORMAL.getModeColor(resultRGB, transferRGB, transferAlpha) | (0xFF << 24);
					break;
					default:
					break;
				}
			}
			if(filterAlpha > 0f){
				switch(filter){
					case USED:
						resultRGB = NORMAL.getModeColor(resultRGB, transferRGB, transferAlpha) | (0xFF << 24);
					break;
					default:
					break;
				}
			}
			if(inverterAlpha > 0f){
				switch(inverter){
					case OFF:
					case ON:
						resultRGB = NORMAL.getModeColor(resultRGB, transferRGB, transferAlpha) | (0xFF << 24);
					break;
					case UNKNOWN:
						resultRGB = NORMAL.getModeColor(resultRGB, transferRGB) | (0xFF << 24);
					break;
					default:
					break;
				}
			}
			if(signalAlpha > 0f){
				switch(signal){
					case OFF:
					case ON:
						resultRGB = NORMAL.getModeColor(resultRGB, transferRGB, transferAlpha) | (0xFF << 24);
					break;
					default:
					break;
				}
			}
			result.setRGB(x, y, resultRGB);
		}
		return result;
	}

	public static enum FastTransfer{

		NONE,
		USED;

		public String getState(){
			switch(this){
				case USED:
					return "fast_transfer";
				default:
					return "";
			}
		}

	}

	public static enum Filter{

		NONE,
		USED;

		public String getState(){
			switch(this){
				case USED:
					return "filter";
				default:
					return "";
			}
		}

	}

	public static enum Inverter{

		NONE,
		OFF,
		ON,
		UNKNOWN;

		public String getState(){
			switch(this){
				case OFF:
					return "inverter_off";
				case ON:
					return "inverter_on";
				case UNKNOWN:
					return "unknown";
				default:
					return "";
			}
		}

	}

	public static enum Signal{

		NONE,
		OFF,
		ON;

		public String getState(){
			switch(this){
				case OFF:
					return "signal_off";
				case ON:
					return "signal_on";
				default:
					return "";
			}
		}

	}

}
