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
    private BufferedImage chestSprites;
    private BufferedImage shieldImgs;
    private BufferedImage swordImgs;
    private BufferedImage painting1Imgs;
    private BufferedImage painting2Imgs;
    private BufferedImage crownImgs;
    
    private ArrayList<Door> doors;
    private ArrayList<Chest> chests;
    private ArrayList<Item> items;
    private BufferedImage[] chestImgs;
    private BufferedImage escapeDoorImgs;

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
        items = LoadSave.GetItem();
    }
    
    public void checkObjectTouched(Rectangle2D.Float hitbox) {
        for(Door p : doors)
            if(p.isActive()) {
                if(hitbox.intersects(p.getHitbox())) {
//                    p.setActive(false);
                    changePlayerLoc(p);
                }
            }
    }

    public void checkObjectHit(Rectangle2D.Float interactbox) {
        for(Door d : doors)
            if(d.isActive()) {
                if(d.getHitbox().intersects(interactbox)) {
//                    System.out.println("used a door");
                    d.setAnimation(true);
                     changePlayerLoc(d);
                }
            }
        for(Chest c : chests)
            if(c.isActive()) {
                if(c.getHitbox().intersects(interactbox)) {
//                    System.out.println("opened a chest");
                    c.setAnimation(true);
                    c.setActive(false);
                    openChest();
                }
            }
        for(Item i : items){
            if(i.isActive()) {
                if(i.getHitbox().intersects(interactbox)) {
//                    System.out.println("Picked up an item");
                    i.setAnimation(true);
                    i.setActive(false);
                    collectItem(i);
                }
            }
        }
    }

    public void changePlayerLoc(Door i) {
        if(i.getObjType() == DOOR){
//            int x = (int) i.getHitbox().x, y = (int) i.getHitbox().y + 33;
            int x, y;
            if (i == doors.get(0)){
                x = (int) doors.get(9).hitbox.x + 15;
                y = (int) doors.get(9).hitbox.y + 33;
            }
            else if (i == doors.get(1)){
                x = (int) doors.get(19).hitbox.x + 15;
                y = (int) doors.get(19).hitbox.y + 33;
            }
            else if (i == doors.get(2)){
                x = (int) doors.get(12).hitbox.x + 15;
                y = (int) doors.get(12).hitbox.y + 33;
            }
            
            else if (i == doors.get(3)){
                x = (int) doors.get(13).hitbox.x + 15;
                y = (int) doors.get(13).hitbox.y + 33;
            }
            else if (i == doors.get(4)){
                x = (int) doors.get(15).hitbox.x + 15;
                y = (int) doors.get(15).hitbox.y + 33;
            }
            else if (i == doors.get(5)){
                x = (int) doors.get(8).hitbox.x + 15;
                y = (int) doors.get(8).hitbox.y + 33;
            }
            
            else if (i == doors.get(6)){
                x = (int) doors.get(17).hitbox.x + 15;
                y = (int) doors.get(17).hitbox.y + 33;
            }
            else if (i == doors.get(7)){
                x = (int) doors.get(18).hitbox.x + 15;
                y = (int) doors.get(18).hitbox.y + 33;
            }
            else if (i == doors.get(8)){
                x = (int) doors.get(5).hitbox.x + 15;
                y = (int) doors.get(5).hitbox.y + 33;
            }
            
            else if (i == doors.get(9)){
                x = (int) doors.get(0).hitbox.x + 15;
                y = (int) doors.get(0).hitbox.y + 33;
            }
            else if (i == doors.get(10)){
                x = (int) doors.get(14).hitbox.x + 15;
                y = (int) doors.get(14).hitbox.y + 33;
            }
            
            else if (i == doors.get(11)){
                x = (int) doors.get(16).hitbox.x + 15;
                y = (int) doors.get(16).hitbox.y + 33;
            }
            else if (i == doors.get(12)){
                x = (int) doors.get(2).hitbox.x + 15;
                y = (int) doors.get(2).hitbox.y + 33;
            }
            
            else if (i == doors.get(13)){
                x = (int) doors.get(3).hitbox.x + 15;
                y = (int) doors.get(3).hitbox.y + 33;
            }
            
            else if (i == doors.get(14)){
                x = (int) doors.get(10).hitbox.x + 15;
                y = (int) doors.get(10).hitbox.y + 33;
            }
            else if (i == doors.get(15)){
                x = (int) doors.get(4).hitbox.x + 15;
                y = (int) doors.get(4).hitbox.y + 33;
            }
            else if (i == doors.get(16)){
                x = (int) doors.get(11).hitbox.x + 15;
                y = (int) doors.get(11).hitbox.y + 33;
            }
            
            else if (i == doors.get(17)){
                x = (int) doors.get(6).hitbox.x + 15;
                y = (int) doors.get(6).hitbox.y + 33;
            }
            else if (i == doors.get(18)){
                x = (int) doors.get(7).hitbox.x + 15;
                y = (int) doors.get(7).hitbox.y + 33;
            }
            
            else{
                x = (int) doors.get(1).hitbox.x + 15;
                y = (int) doors.get(1).hitbox.y + 33;
            }
            playing.getPlayer().changeLoc(x, y);
        }
    }
    
    private void openChest() {
        int score = 10;
        playing.addScore(score);
    }
    
    
    private void collectItem(Item i){
        int score = 0;
        switch (i.getObjType()) {
                    case SHIELD:
                        score = 50;
                        break;
                    case SWORD:
                        score = 25;
                        break;
                    case PAINTING1:
                        score = 100;
                        break;
                    case PAINTING2:
                        score = 100;
                        break;
                    case CROWN:
                        score = 500;
                        break;
                    case ESCAPE:
                        i.setActive(true);
                        playing.setGameComplete(true);
                        break;
                    default:
                        break;
                }
        playing.addScore(score);
    }


    private void loadImgs() {

        doorImgs = LoadSave.getSpriteAtlas(LoadSave.DOOR_IMG);
        chestSprites = LoadSave.getSpriteAtlas(LoadSave.CHEST_IMG);
        shieldImgs = LoadSave.getSpriteAtlas(LoadSave.SHIELD_IMG);
        swordImgs = LoadSave.getSpriteAtlas(LoadSave.SWORD_IMG);
        painting1Imgs = LoadSave.getSpriteAtlas(LoadSave.PICTURE1_IMG);
        painting2Imgs = LoadSave.getSpriteAtlas(LoadSave.PICTURE2_IMG);
        crownImgs = LoadSave.getSpriteAtlas(LoadSave.CROWN_IMG);
        escapeDoorImgs = LoadSave.getSpriteAtlas(LoadSave.ESCAPE_DOOR_IMG);
        
        
       
        chestImgs = new BufferedImage[2];

         chestImgs[0] = chestSprites.getSubimage(0 , 40 , 74, 40);
         chestImgs[1] = chestSprites.getSubimage(74 , 40 , 74, 40);
    }

    public void update() {
        for (Door p : doors)
            if(p.isActive())
                p.update();
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        drawDoors(g, xLvlOffset, yLvlOffset);
        drawChests(g, xLvlOffset, yLvlOffset);
        drawItems(g, xLvlOffset, yLvlOffset);

    }

    private void drawDoors(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Door door : doors){
            if(door.isActive()) {
                if(door.getObjType() == DOOR)
//                g.drawImage(doorImgs[type][p.getAniIndex()],
                    g.drawImage(doorImgs,
                    (int) ((door.getHitbox().x) - door.getxDrawOffset() - xLvlOffset), 
                    (int) ((door.getHitbox().y) - door.getyDrawOffset() - yLvlOffset), 
                     DOOR_WIDTH, 
                     DOOR_HEIGHT, 
                     null);
                        door.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
        }
    }

    private void drawChests(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Chest chest : chests){
            if(chest.isActive()) {
                int type = 0;
                if(chest.getObjType() == CHEST)
                    type = 1;
                
                g.drawImage(chestImgs[1],
//                g.drawImage(chestImgs,
                 (int) ((chest.getHitbox().x) - chest.getxDrawOffset() - xLvlOffset), 
                 (int) ((chest.getHitbox().y) - chest.getyDrawOffset() - yLvlOffset), 
                 (int) (CHEST_HEIGHT),
                 (int) (CHEST_WIDTH), 
                 null);
                chest.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
             if(!chest.isActive()) {
                g.drawImage(chestImgs[0],
//                g.drawImage(chestImgs,
                 (int) ((chest.getHitbox().x) - chest.getxDrawOffset() - xLvlOffset), 
                 (int) ((chest.getHitbox().y) - chest.getyDrawOffset() - yLvlOffset), 
                 (int) (CHEST_HEIGHT),
                 (int) (CHEST_WIDTH), 
                 null);
                chest.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
//            System.out.println(door.getHitbox().x + " : " + door.getHitbox().y);
        }
    }
    
    private void drawItems(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Item item : items){
            if(item.isActive()) {
                switch (item.getObjType()) {
                    case SHIELD:
                        g.drawImage(shieldImgs,
                                (int) ((item.getHitbox().x) - item.getxDrawOffset() - xLvlOffset),
                                (int) ((item.getHitbox().y) - item.getyDrawOffset() - yLvlOffset),
                                ITEM_WIDTH,
                                ITEM_HEIGHT,
                                null);
                        break;
                    case SWORD:
                        g.drawImage(swordImgs,
                                (int) ((item.getHitbox().x) - item.getxDrawOffset() - xLvlOffset),
                                (int) ((item.getHitbox().y) - item.getyDrawOffset() - yLvlOffset),
                                ITEM_WIDTH,
                                ITEM_HEIGHT,
                                null);
                        break;
                    case PAINTING1:
                        g.drawImage(painting1Imgs,
                                (int) ((item.getHitbox().x) - item.getxDrawOffset() - xLvlOffset),
                                (int) ((item.getHitbox().y) - item.getyDrawOffset() - yLvlOffset),
                                ITEM_WIDTH,
                                ITEM_HEIGHT,
                                null);
                        break;
                    case PAINTING2:
                        g.drawImage(painting2Imgs,
                                (int) ((item.getHitbox().x) - item.getxDrawOffset() - xLvlOffset),
                                (int) ((item.getHitbox().y) - item.getyDrawOffset() - yLvlOffset),
                                ITEM_WIDTH,
                                ITEM_HEIGHT,
                                null);
                        break;
                    case CROWN:
                        g.drawImage(crownImgs,
                                (int) ((item.getHitbox().x) - item.getxDrawOffset() - xLvlOffset),
                                (int) ((item.getHitbox().y) - item.getyDrawOffset() - yLvlOffset),
                                CROWN_WIDTH,
                                CROWN_HEIGHT,
                                null);
                        break;
                    case ESCAPE:
                        g.drawImage(escapeDoorImgs,
                                (int) ((item.getHitbox().x) - item.getxDrawOffset() - xLvlOffset) - 9,
                                (int) ((item.getHitbox().y) - item.getyDrawOffset() - yLvlOffset) - 58,
                                DOOR_WIDTH/2,
                                DOOR_HEIGHT,
                                null);
                        break;
                    default:
                        break;
                }
                
                item.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
            
//            System.out.println(door.getHitbox().x + " : " + door.getHitbox().y);
        }
    }

    public void resetAllObjects() {
        for (Door p : doors)
            p.reset();
        for (Chest c : chests)
            c.reset();
        for (Item i : items)
            i.reset();
    }


}
