package utilz;

//import java.awt.Graphics;
import entities.Knight;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.net.URL;
import javax.swing.*;
import main.Game;
import objects.Chest;
import objects.Door;
import objects.Item;

import static utilz.Constants.Directions.*;
import static utilz.Constants.ObjectConstants.*;
import static utilz.Constants.EnemyConstants.KNIGHT;


public class LoadSave {
	public static final String PLAYER_ATLAS = "spritesheet.png";
	public static final String LEVEL_ATLAS = "level res/Sprite Sheet 1.png";
	public static final String LEVEL_DATA = "level_data.png";
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
        
	public static final String KNIGHT_SPRITE = "player_sprites.png";
	public static final String STATUS_BAR = "health_power_bar.png";

	public static final String DOOR_IMG = "level res/Objects/Door (5).png";
                public static final String CHEST_IMG = "chest.png";
        
                public static final String SHIELD_IMG = "level res/Objects/Shield (1).png";
                public static final String SWORD_IMG = "level res/Objects/Sword (1).png";
                
                public static final String CROWN_IMG = "level res/Objects/Crown.png";
                
                public static final String THRONE_IMG = "level res/Objects/Throne (1).png";

	public static BufferedImage getSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/res/" + fileName);
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// If an error occoured here, you probably put the file in the wrong place
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static int[][] GetLevelData() {
//		 BufferedImage img = getSpriteAtlas(TESTING_ROOM);
		BufferedImage img = getSpriteAtlas(LEVEL_DATA);
		// int[][] lvlData = new int[img.getHeight()][img.getWidth()];
		int[][] lvlData = new int[Game.GAME_HEIGHT][Game.GAME_WIDTH];

		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				// https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
				int color = img.getRGB(i, j);

				// Components will be in the range of 0..255:
				int mapValue = (color & 0xff0000) >> 16; // Red

				if (mapValue >= 130) {
					mapValue = 98;
				}
				lvlData[j][i] = mapValue;
			}
		}
		return lvlData;
	}

	public static ArrayList<Knight> GetKnights() {
		BufferedImage img = getSpriteAtlas(LEVEL_DATA);
//                                BufferedImage img = getSpriteAtlas(TESTING_ROOM);
		ArrayList<Knight> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == KNIGHT)
					list.add(new Knight(i * Game.TILES_SIZE, j * Game.TILES_SIZE-28));
			}
		return list;

	}
        
                public static ArrayList<Door> GetDoor(){
                      BufferedImage img = getSpriteAtlas(LEVEL_DATA);
//                                BufferedImage img = getSpriteAtlas(TESTING_ROOM);
                    ArrayList<Door> doorList = new ArrayList<>();
                    for (int j = 0; j < img.getHeight(); j++){
                            for (int i = 0; i < img.getWidth(); i++) {
                                    Color color = new Color(img.getRGB(i, j));
                                    int value = color.getGreen();
                                    if (value == 200){
                                        doorList.add(new Door(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                                    }
                            }
                    }
                    System.out.println("Number of doors :" + doorList.size());
                    return doorList;
                }
                
                public static ArrayList<Chest> GetChest(){
                      BufferedImage img = getSpriteAtlas(LEVEL_DATA);
//                                BufferedImage img = getSpriteAtlas(TESTING_ROOM);
                    ArrayList<Chest> chestList = new ArrayList<>();
                    for (int j = 0; j < img.getHeight(); j++){
                            for (int i = 0; i < img.getWidth(); i++) {
                                    Color color = new Color(img.getRGB(i, j));
                                    int value = color.getGreen();
                                    if (value == CHEST){
                                    chestList.add(new Chest(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                                    }
                            }
                     }
                      System.out.println("Number of chest :" + chestList.size());
                      return chestList;
                }
                
                public static ArrayList<Item> GetItem(){
                      BufferedImage img = getSpriteAtlas(LEVEL_DATA);
//                                BufferedImage img = getSpriteAtlas(TESTING_ROOM);
                    ArrayList<Item> itemList = new ArrayList<>();
                    for (int j = 0; j < img.getHeight(); j++){
                            for (int i = 0; i < img.getWidth(); i++) {
                                    Color color = new Color(img.getRGB(i, j));
                                    int value = color.getGreen();
                                    if (value == SHIELD || value == SWORD || value == PAINTING1 || value == PAINTING2 || value == SWORD ){
                                        itemList.add(new Item(i * Game.TILES_SIZE, j * Game.TILES_SIZE, value));
                                    }
                            }
                                    }
                                    System.out.println("chest size :" + itemList.size());
                                    return itemList;
                }
                  
               
//                   public static Font LoadFont() {
//                        // This font is < 35Kb.
//                        URL fontUrl = new URL("http://www.webpagepublicity.com/" +
//                            "free-fonts/a/Airacobra%20Condensed.ttf");
//                        Font font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
//                        GraphicsEnvironment ge = 
//                            GraphicsEnvironment.getLocalGraphicsEnvironment();
//                        ge.registerFont(font);
//                        JList fonts = new JList( ge.getAvailableFontFamilyNames() );
//                         JOptionPane.showMessageDialog(null, new JScrollPane(fonts));
//
//                    }

}
