package utilz;

//import java.awt.Graphics;
import entities.Crabby;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import main.Game;
import objects.Item;

import static utilz.Constants.Directions.*;
import static utilz.Constants.EnemyConstants.CRABBY;
import static utilz.Constants.ObjectConstants.*;

public class LoadSave {
	public static final String PLAYER_ATLAS = "spritesheet.png";
	public static final String LEVEL_ATLAS = "level res/Sprite Sheet 1.png";
	public static final String LEVEL_DATA = "level2_data.png";
	public static final String TESTING_ROOM = "testing_room.png";
        
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String MENU_BACKGROUND_IMG = "background_menu.png";
	public static final String OPTION_MENU = "options_background.png";
                public static final String DEATH_SCREEN = "death_screen.png";
        
	public static final String CRABBY_SPRITE = "player_sprites.png";
	public static final String STATUS_BAR = "health_power_bar.png";

	public static final String DOOR_IMG = "level res/Objects/Door (5).png";	

	public static BufferedImage getSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/res/" + fileName);
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// System.out.println("Wrong file location, probably.");
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static int[][] GetLevelData() {
		 BufferedImage img = getSpriteAtlas(TESTING_ROOM);
//		BufferedImage img = getSpriteAtlas(LEVEL_DATA);
		// int[][] lvlData = new int[img.getHeight()][img.getWidth()];
		int[][] lvlData = new int[Game.GAME_HEIGHT][Game.GAME_WIDTH];

		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				// https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
				int color = img.getRGB(i, j);

				// Components will be in the range of 0..255:
				int mapValue = (color & 0xff0000) >> 16; // Red
				// System.out.println(mapValue);

				// Color color = new Color(img.getRGB(i, j));
				// int value = (int) color.getRed();

				if (mapValue >= 130) {
					mapValue = 98;
				}
				lvlData[j][i] = mapValue;
			}
		}
		return lvlData;
	}

	public static ArrayList<Crabby> GetCrabs() {
//		BufferedImage img = getSpriteAtlas(LEVEL_DATA);
                                BufferedImage img = getSpriteAtlas(TESTING_ROOM);
		ArrayList<Crabby> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == CRABBY)
					list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE-28));
			}
		return list;

	}
        
                public static ArrayList<Item> GetDoor(){
//                      BufferedImage img = getSpriteAtlas(LEVEL_DATA);
                                BufferedImage img = getSpriteAtlas(TESTING_ROOM);
		ArrayList<Item> doorList = new ArrayList<>();
                                int time = 0;
		for (int j = 0; j < img.getHeight(); j++){
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == 200){
                                                                                time++;
                                                                                System.out.println("Number of time door was added: " + time);
                                                                                System.out.println(i + " : " + j);
					doorList.add(new Item(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                                                                }
			}
                                }
                                System.out.println("Door size :" + doorList.size());
                                return doorList;
                }
}
