package objects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import gamestates.*;
import utilz.LoadSave;
import static utilz.Constants.ObjectConstants.*;

public class ObjectManager {

    private Playing playing;
//    private BufferedImage[][] potionImgs;
    private BufferedImage doorImgs;
    private ArrayList<Item> door;

    public ObjectManager(Playing playing) {
        this.playing = playing;
        loadImgs();
        
        door = new ArrayList<>();
        door.add(new Item(1100,1500,0));
        door.add(new Item(1000,1500,1));
    }
    
    public void checkObjectTouched(Rectangle2D.Float hitbox) {
        for(Item p : door)
            if(p.isActive()) {
                if(hitbox.intersects(p.getHitbox())) {
                    p.x = 1500;
                    p.y = 2000;
                    p.setActive(false);
                    applyEffectToPlayer(p);
                }
            }
    }

    public void checkObjectHit(Rectangle2D.Float attackbox) {
        for(Item p : door)
            if(p.isActive()) {
                if(p.getHitbox().intersects(attackbox)) {
                    p.setAnimation(true);
                    // applyEffectToPlayer(p);
                }
            }
    }

    public void applyEffectToPlayer(Item p) {
        if(p.getObjType() == DOOR)
            playing.getPlayer().changeHealth(DOOR_VALUE);
    }

    private void loadImgs() {
//        BufferedImage potionSprite = LoadSave.getSpriteAtlas(LoadSave.DOOR_IMG);
        doorImgs = LoadSave.getSpriteAtlas(LoadSave.DOOR_IMG);

//        potionImgs = new BufferedImage[2][7];

//        for (int j = 0; j < potionImgs.length; j++)
//            for (int i = 0; i < potionImgs[j].length; i++)
//                potionImgs[j][i] = potionSprite.getSubimage(12 * i, 16 * j, 12, 16);

    }

    public void update() {
        for (Item p : door)
            if(p.isActive())
                p.update();
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        drawDoors(g, xLvlOffset, yLvlOffset);
    }

    private void drawDoors(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Item p : door)
            if(p.isActive()) {
                int type = 0;
                if(p.getObjType() == DOOR)
                    type = 1;
//                g.drawImage(doorImgs[type][p.getAniIndex()],
                g.drawImage(doorImgs,
                 (int) (p.getHitbox().x - p.getxDrawOffset() - xLvlOffset), 
                 (int) (p.getHitbox().y - p.getyDrawOffset() - yLvlOffset), 
                 DOOR_WIDTH, 
                 DOOR_HEIGHT, 
                 null);
            }
    }

    public void resetAllObjects() {
        for (Item p : door)
            p.reset();
    }
}
