package levels;

import main.Game;
import utilz.LoadSave;

import java.awt.image.BufferedImage;
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
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[130];
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 13; i++) {
                int index = j * 13 + i;
                levelSprite[index] = img.getSubimage((i * 72)+(i*2), (j * 72)+(j*2), 36, 36);
            }
        }
    }
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (int j = 0; j < Game.GAME_HEIGHT; j++) {
            for (int i = 0; i < Game.GAME_WIDTH; i++) {
                int index = lvl.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], (i * Game.TILES_SIZE) - xLvlOffset, (j * Game.TILES_SIZE) - yLvlOffset, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }
    public void update() {

    }
    public Level getCurrentLevel(){
        return lvl;
    }
}
