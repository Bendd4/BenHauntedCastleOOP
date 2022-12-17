package objects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;

import gamestates.*;
import utilz.LoadSave;
import static utilz.Constants.ObjectConstants.*;

public class ObjectManager {

    private Playing playing;
    private BufferedImage[][] potionImgs;
    private ArrayList<Potion> potions;

    public ObjectManager(Playing playing) {
        this.playing = playing;
        loadImgs();
        
        potions = new ArrayList<>();
        potions.add(new Potion(300,300,0));
        potions.add(new Potion(400,300,1));
    }
    
    private void loadImgs() {
        BufferedImage potionSprite = LoadSave.getSpriteAtlas(LoadSave.POTION_ATLAS);
        potionImgs = new BufferedImage[2][7];

        for (int j = 0; j < potionImgs.length; j++)
            for (int i = 0; i < potionImgs[j].length; i++)
                potionImgs[j][i] = potionSprite.getSubimage(12 * i, 16 * j, 12, 16);

    }

    public void update() {
        for (Potion p : potions)
            if(p.isActive())
                p.update();
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawPotions(g, xLvlOffset);
    }

    private void drawPotions(Graphics g, int xLvlOffset) {
        for (Potion p : potions)
            if(p.isActive()) {
                int type = 0;
                if(p.getObjType() == RED_POTION)
                    type = 1;
                g.drawImage(potionImgs[type][p.getAniIndex()],
                 (int) (p.getHitbox().x - p.getxDrawOffset() - xLvlOffset), 
                 (int) (p.getHitbox().y - p.getyDrawOffset()), 
                 POTION_WIDTH, 
                 POTION_HEIGHT, 
                 null);
            }
    }
}
