package objects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import gamestates.*;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.ObjectConstants.*;

public class ObjectManager {

    private Playing playing;
//    private BufferedImage[][] potionImgs;
    private BufferedImage doorImgs;
    private BufferedImage chestImgs;
    private ArrayList<Item> doors;
    private ArrayList<Chest> chests;
    private BufferedImage[] chestSprite;

    public ObjectManager(Playing playing) {
        this.playing = playing;
        loadImgs();
       
//        System.out.println(doors);
        addObjects();
        
//        door = new ArrayList<>();
    }
    
    public void addObjects(){
        doors = LoadSave.GetDoor();
         chests = LoadSave.GetChest();
//        LoadSave.Get
    }
    
    public void checkObjectTouched(Rectangle2D.Float hitbox) {
        for(Item p : doors)
            if(p.isActive()) {
                if(hitbox.intersects(p.getHitbox())) {
//                    p.setActive(false);
                    applyEffectToPlayer(p);
                }
            }
    }

    public void checkObjectHit(Rectangle2D.Float attackbox) {
        for(Item p : doors)
            if(p.isActive()) {
                if(p.getHitbox().intersects(attackbox)) {
                    System.out.println("door");
                    p.setAnimation(true);
                     applyEffectToPlayer(p);
                }
            }
        for(Chest c : chests)
            if(c.isActive()) {
                if(c.getHitbox().intersects(attackbox)) {
                    System.out.println("chest");
                    c.setAnimation(true);
                    c.setActive(false);
//                     applyEffectToPlayer(c);
                }
            }
    }

    public void applyEffectToPlayer(Item i) {
        if(i.getObjType() == DOOR){
            int x = (int) i.getHitbox().x, y = (int) i.getHitbox().y + 44;
            if (i == doors.get(2)){
//                x = 200;
            }
            else if (i == doors.get(5)){
//                x = 600;
            }
            else if (i == doors.get(8)){
//                y = 510;
                y = (int) doors.get(5).hitbox.y + DOOR_HEIGHT/3 + 6;
//             x = 1000;
            }
            
            playing.getPlayer().changeLoc(x, y);
        }
            System.out.println("Object interacted");
    }

    


    private void loadImgs() {

        doorImgs = LoadSave.getSpriteAtlas(LoadSave.DOOR_IMG);
        chestImgs = LoadSave.getSpriteAtlas(LoadSave.CHEST_IMG);

       
        chestSprite = new BufferedImage[2];

//        for (int j = 0; j < potionImgs.length; j++)
//            for (int i = 0; i < potionImgs[j].length; i++)
//                potionImgs[j][i] = potionSprite.getSubimage(12 * i, 16 * j, 12, 16);
         chestSprite[0] = chestImgs.getSubimage(0 , 40 , 74, 40);
         chestSprite[1] = chestImgs.getSubimage(74 , 40 , 74, 40);
    }

    public void update() {
        for (Item p : doors)
            if(p.isActive())
                p.update();
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        drawDoors(g, xLvlOffset, yLvlOffset);
        drawChests(g, xLvlOffset, yLvlOffset);
        
    }

    private void drawDoors(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Item door : doors){
            if(door.isActive()) {
                int type = 0;
                if(door.getObjType() == DOOR)
                    type = 1;
                
//                g.drawImage(doorImgs[type][p.getAniIndex()],
                g.drawImage(doorImgs,
                 (int) ((door.getHitbox().x) - door.getxDrawOffset() - xLvlOffset), 
                 (int) ((door.getHitbox().y) - door.getyDrawOffset() - yLvlOffset), 
                 DOOR_WIDTH, 
                 DOOR_HEIGHT, 
                 null);
                door.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
            
//            System.out.println(door.getHitbox().x + " : " + door.getHitbox().y);
        }
    }
    private void drawChests(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Chest chest : chests){
            if(chest.isActive()) {
                int type = 0;
                if(chest.getObjType() == CHEST)
                    type = 1;
                
                g.drawImage(chestSprite[1],
//                g.drawImage(chestImgs,
                 (int) ((chest.getHitbox().x) - chest.getxDrawOffset() - xLvlOffset), 
                 (int) ((chest.getHitbox().y) - chest.getyDrawOffset() - yLvlOffset), 
                 (int) (49*Game.SCALE), 
                 (int) (49*Game.SCALE),
                 
                 null);
                chest.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
             if(!chest.isActive()) {
               
                
                g.drawImage(chestSprite[0],
//                g.drawImage(chestImgs,
                 (int) ((chest.getHitbox().x) - chest.getxDrawOffset() - xLvlOffset), 
                 (int) ((chest.getHitbox().y) - chest.getyDrawOffset() - yLvlOffset), 
                 (int) (49*Game.SCALE), 
                 (int) (49*Game.SCALE),
                 
                 null);
                chest.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
//            System.out.println(door.getHitbox().x + " : " + door.getHitbox().y);
        }
    }

    public void resetAllObjects() {
        for (Item p : doors)
            p.reset();
        for (Chest c : chests)
            c.reset();
    }
}
