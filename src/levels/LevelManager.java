package levels;

import main.Game;
import utilz.LoadSave;

import java.awt.image.BufferedImage;
//import java.nio.Buffer;
import java.awt.Graphics;;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;

    public LevelManager(Game game) {
        this.game = game;
        // levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();
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

    public void draw(Graphics g) {
        g.drawImage(levelSprite[0], 0, 0, null);
    }

    public void update() {

    }
}
