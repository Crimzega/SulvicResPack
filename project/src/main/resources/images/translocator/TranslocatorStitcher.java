import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class TranslocatorStitcher{

	private static final String TRANSLOCATOR_ITEM = "item";
	private static final String TRANSLOCATOR_LIQUID = "liquid";
	private static List<BuilderHandler> builderHandlers = new ArrayList<>();

	public static void main(String[] args) throws IOException{
		File currDirectory = new File(".").getCanonicalFile();
		File segmentsFolder = new File(currDirectory, "segmented");
		File exportPath = new File(currDirectory.getParentFile().getParentFile(), "current");
		BufferedImage itemImg = ImageIO.read(new File(segmentsFolder, String.format("%s.png", TRANSLOCATOR_ITEM)));
		BufferedImage liquidImg = ImageIO.read(new File(segmentsFolder, String.format("%s.png", TRANSLOCATOR_LIQUID)));
		BufferedImage finalImg = new BufferedImage(itemImg.getWidth() * 64, itemImg.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
		for(int x = 0; x < itemImg.getWidth(); x++) for(int y = 0; y < itemImg.getHeight(); y++) finalImg.setRGB(x, y, 0x00000000);
		for(Signal signal: Signal.values()) signal.readImage(segmentsFolder);
		for(Filter filter: Filter.values()) filter.readImage(segmentsFolder);
		for(FastTransfer transfer: FastTransfer.values()) transfer.readImage(segmentsFolder);
		for(InverterState state: InverterState.values()) state.readImage(segmentsFolder);
		int xOffset = 0;
		for(Signal signal: Signal.values()) for(Filter filter: Filter.values()) for(FastTransfer transfer: FastTransfer.values()) for(InverterState state: InverterState.values()){
			builderHandlers.add(new BuilderHandler(xOffset, signal, filter, transfer, state));
			xOffset += itemImg.getWidth();
		}
		for(BuilderHandler handler: builderHandlers) handler.apply(finalImg, itemImg, liquidImg);
		File translocatorFolder = new File(exportPath, "assets/translocator/textures");
		if(!exportPath.exists()) exportPath.mkdirs();
		FileOutputStream stream = new FileOutputStream(new File(translocatorFolder, "tex.png"));
		ImageIO.write(finalImg, "png", stream);
		stream.flush();
		stream.close();
	}

	protected static class BuilderHandler{

		private static final BlenderMode MODE = BlenderMode.NORMAL;
		private FastTransfer fastTransfer;
		private InverterState inverterState;
		private Filter filterUsed;
		private Signal signalState;
		private int offsetX;

		public BuilderHandler(int offset, Signal signal, Filter filter, FastTransfer transfer, InverterState state){
			offsetX = offset;
			signalState = signal;
			filterUsed = filter;
			fastTransfer = transfer;
			inverterState = state;
		}

		public void apply(BufferedImage finalImg, BufferedImage itemImg, BufferedImage liquidImg){
			BufferedImage signalImg = signalState.getImage(),
				filterImg = filterUsed.getImage(),
				transferImg = fastTransfer.getImage(),
				stateImg = inverterState.getImage();
			for(int x = 0; x < itemImg.getWidth(); x++) for(int y = 0; y < itemImg.getHeight(); y++){
				int itemRGB = itemImg.getRGB(x, y),
					liquidRGB = liquidImg.getRGB(x, y),
					signalRGB = 0x00000000,
					filterRGB = 0x00000000,
					transferRGB = 0x00000000,
					stateRGB = 0x00000000;
				if(signalImg != null) signalRGB = signalImg.getRGB(x, y);
				if(filterImg != null) filterRGB = filterImg.getRGB(x, y);
				if(transferImg != null) transferRGB = transferImg.getRGB(x, y);
				if(stateImg != null) stateRGB = stateImg.getRGB(x, y);
				float signalAlpha = (float)((signalRGB >> 24) & 0xFF) / 255f,
					filterAlpha = (float)((filterRGB >> 24) & 0xFF) / 255f,
					transferAlpha = (float)((transferRGB >> 24) & 0xFF) / 255f,
					stateAlpha = (float)((stateRGB >> 24) & 0xFF) / 255f;
				if(signalAlpha > 0f){
					switch(signalState){
						case OFF:
						case ON:
							itemRGB = MODE.getModeColor(itemRGB, signalRGB, signalAlpha) | (0xFF << 24);
							liquidRGB = MODE.getModeColor(liquidRGB, signalRGB, signalAlpha) | (0xFF << 24);
						break;
					}
				}
				if(filterAlpha > 0f){
					switch(filterUsed){
						case USED:
							itemRGB = MODE.getModeColor(itemRGB, filterRGB, filterAlpha) | (0xFF << 24);
							liquidRGB = MODE.getModeColor(liquidRGB, filterRGB, filterAlpha) | (0xFF << 24);
						break;
					}
				}
				if(transferAlpha > 0f){
					switch(fastTransfer){
						case USED:
							itemRGB = MODE.getModeColor(itemRGB, transferRGB, transferAlpha) | (0xFF << 24);
							liquidRGB = MODE.getModeColor(liquidRGB, transferRGB, transferAlpha) | (0xFF << 24);
						break;
					}
				}
				if(stateAlpha > 0f){
					switch(inverterState){
						case UNKNOWN:
							itemRGB = MODE.getModeColor(itemRGB, stateRGB) | (0xFF << 24);
							liquidRGB = MODE.getModeColor(liquidRGB, stateRGB) | (0xFF << 24);
						break;
						case OFF:
						case ON:
							itemRGB = MODE.getModeColor(itemRGB, stateRGB, stateAlpha) | (0xFF << 24);
							liquidRGB = MODE.getModeColor(liquidRGB, stateRGB, stateAlpha) | (0xFF << 24);
						break;
					}
				}
				finalImg.setRGB(x + offsetX, y, itemRGB);
				finalImg.setRGB(x + offsetX, y + itemImg.getHeight(), liquidRGB);
			}
		}
		
	}

	protected static enum BlenderMode{

		NORMAL;

		private float getModeValue(float base, float blend){ return blend; }

		private float getModeValue(float base, float blend, float opacity){ return getModeValue(base, blend) * opacity + base * (1f - opacity); }

		private float toRange(int value){ return value / 255f; }

		public int getModeColor(int baseRGB, int blendRGB){
			float baseR = toRange((baseRGB >> 16) & 0xFF);
			float baseG = toRange((baseRGB >> 8) & 0xFF);
			float baseB = toRange(baseRGB & 0xFF);
			float blendR = toRange((blendRGB >> 16) & 0xFF);
			float blendG = toRange((blendRGB >> 8) & 0xFF);
			float blendB = toRange(blendRGB & 0xFF);
			float newR = getModeValue(baseR, blendR);
			float newG = getModeValue(baseG, blendG);
			float newB = getModeValue(baseB, blendB);
			return ((int)(newR * 0xFF) << 16) + ((int)(newG * 0xFF) << 8) + (int)(newB * 0xFF);}

		public int getModeColor(int baseRGB, int blendRGB, float opacity){
			float baseR = toRange((baseRGB >> 16) & 0xFF);
			float baseG = toRange((baseRGB >> 8) & 0xFF);
			float baseB = toRange(baseRGB & 0xFF);
			float blendR = toRange((blendRGB >> 16) & 0xFF);
			float blendG = toRange((blendRGB >> 8) & 0xFF);
			float blendB = toRange(blendRGB & 0xFF);
			float newR = getModeValue(baseR, blendR, opacity);
			float newG = getModeValue(baseG, blendG, opacity);
			float newB = getModeValue(baseB, blendB, opacity);
			return ((int)(newR * 0xFF) << 16) + ((int)(newG * 0xFF) << 8) + (int)(newB * 0xFF);
		}
	}

	protected static enum FastTransfer{

		NONE,
		USED;

		BufferedImage transferImg;

		public BufferedImage getImage(){ return transferImg; }

		public String getState(){
			switch(this){
				case USED: return "fast_transfer";
				default: return "";
			}
		}

		public void readImage(File folder) throws IOException{
			switch(this){
				case USED:
					transferImg = ImageIO.read(new File(folder, String.format("%s.png", getState())));
				break;
				default:
					transferImg = null;
				break;
			}
		}

	}

	protected static enum Filter{

		NONE,
		USED;

		BufferedImage filterImg;

		public BufferedImage getImage(){ return filterImg; }

		public String getState(){
			switch(this){
				case USED: return "filter";
				default: return "";
			}
		}

		public void readImage(File folder) throws IOException{
			switch(this){
				case USED:
					filterImg = ImageIO.read(new File(folder, String.format("%s.png", getState())));
				break;
				default:
					filterImg = null;
				break;
			}
		}

	}

	protected static enum Signal{

		NONE,
		OFF,
		ON;

		BufferedImage signalImg;

		public BufferedImage getImage(){ return signalImg; }

		public String getState(){
			switch(this){
				case OFF: return "signal_off";
				case ON: return "signal_on";
				default: return "";
			}
		}

		public void readImage(File folder) throws IOException{
			switch(this){
				case OFF:
				case ON:
					signalImg = ImageIO.read(new File(folder, String.format("%s.png", getState())));
				break;
				default:
					signalImg = null;
				break;
			}
		}

	}

	protected static enum InverterState{

		NONE,
		OFF,
		ON,
		UNKNOWN;

		BufferedImage inverterImg;

		public BufferedImage getImage(){ return inverterImg; }

		public String getState(){
			switch(this){
				case OFF: return "inverter_off";
				case ON: return "inverter_on";
				case UNKNOWN: return "unknown";
				default: return "";
			}
		}

		public void readImage(File folder) throws IOException{
			switch(this){
				case OFF:
				case ON:
				case UNKNOWN:
					if(inverterImg == null) inverterImg = ImageIO.read(new File(folder, String.format("%s.png", getState())));
				break;
				default:
					inverterImg = null;
				break;
			}
		}

	}

}
