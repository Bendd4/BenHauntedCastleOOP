package levels;

import main.Game;
import utilz.LoadSave;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.awt.Graphics;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level lvl;

    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
        lvl = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[130];
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 13; i++) {
                int index = j * 13 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g){
        for(int j = 0; j < Game.GAME_HEIGHT; j++){
            for (int i = 0; i < Game.GAME_WIDTH; i++) {
                int index = lvl.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], i*Game.TILES_SIZE, j*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }
}
