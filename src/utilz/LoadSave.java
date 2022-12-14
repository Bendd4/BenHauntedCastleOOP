package utilz;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import static utilz.Constants.Directions.*;

public class LoadSave {
    public static final String PLAYER_ATLAS = "player_sprites2.png";
    public static final String LEVEL_ATLAS = "Sprite Sheet 1.png";


    public static BufferedImage getSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);
        }catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return img;
    }

}
