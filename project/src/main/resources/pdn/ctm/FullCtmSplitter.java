import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class FullCtmSplitter{

	private static final String FULL_CTM_FILE = "full_ctm_enre.png";
	private static List<ImageSegment> imageSegments = new ArrayList<>();

	private static boolean hasFolders(File folder){ return folder.isDirectory(); }

	public static void main(String[] args) throws IOException{
		File currDirectory = new File(".").getCanonicalFile();
		File projectFolder = currDirectory.getParentFile();
		File resultFolder = new File(projectFolder.getParentFile(), "current");
		System.out.println(currDirectory);
		for(File folder: currDirectory.listFiles(FullCtmSplitter::hasFolders)){
			for(File folder1: folder.listFiles(FullCtmSplitter::hasFolders)){
				File file = new File(folder1, FULL_CTM_FILE);
				if(file.exists()) imageSegments.add(new ImageSegment(folder, folder1));
			}
		}
		for(ImageSegment segment: imageSegments){
			String common = segment.getProjectFolderPath();
			File folder = new File(resultFolder, segment.getFinalFolderPath());
			if(!folder.exists()) folder.mkdirs();
			BufferedImage img = ImageIO.read(new File(currDirectory, String.format("%s/%s", common, FULL_CTM_FILE)));
			String blockName = segment.getBlockName();
			int width = img.getWidth() / 12, height = img.getHeight() / 4;
			for(int i = 0; i < 47; i++){
				int x = i % 12, y = (int)Math.floor((double)i / 12f);
				FileOutputStream stream = new FileOutputStream(new File(folder, String.format("%d.png", i)));
				ImageIO.write(img.getSubimage(x * width, y * height, width, height), "png", stream);
				stream.flush();
				stream.close();
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(folder, String.format("%s.properties", blockName)))));
			writer.append("method=ctm\nconnect=block\ntiles=0-46\n");
			writer.flush();
			writer.close();
		}
	}

	protected static class ImageSegment{

		private final String assetName, blockName;

		public ImageSegment(File asset, File block){
			assetName = asset.getName().toLowerCase();
			blockName = block.getName().toLowerCase();
		}

		public String getBlockName(){ return blockName; }

		public String getFinalFolderPath(){ return String.format("assets/%s/mcpatcher/ctm/%s", assetName, blockName); }

		public String getProjectFolderPath(){ return String.format("%s/%s", assetName, blockName); }

		public String toString(){ return String.format("%s:%s", assetName, blockName); }

	}

}
