import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageSplitter{

	public static void main(String[] var0) throws IOException{
		BufferedImage img = ImageIO.read(new File("full_ctm_enre.png"));
		for(int i = 0; i < 47; i++){
			int x = i % 12;
			int y = (int)Math.floor((double)((float)i / 12.0F));
			System.out.println(String.format("Pos: %d, %d", x, y));
			FileOutputStream stream = new FileOutputStream(String.format("%d.png", i));
			ImageIO.write(img.getSubimage(x * 32, y * 32, 32, 32), "png", stream);
			stream.flush();
			stream.close();
		}
	}

}
