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
    
    
    

    private ArrayList<Door> doors;
    private ArrayList<Chest> chests;
    private ArrayList<Item> items;
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
        items = LoadSave.GetItem();
//        LoadSave.Get
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

    public void checkObjectHit(Rectangle2D.Float attackbox) {
        for(Door d : doors)
            if(d.isActive()) {
                if(d.getHitbox().intersects(attackbox)) {
                    System.out.println("door");
                    d.setAnimation(true);
                     changePlayerLoc(d);
                }
            }
        for(Chest c : chests)
            if(c.isActive()) {
                if(c.getHitbox().intersects(attackbox)) {
                    System.out.println("chest");
                    c.setAnimation(true);
                    c.setActive(false);
                    openChest();
                }
            }
        for(Item i : items){
            if(i.isActive()) {
                if(i.getHitbox().intersects(attackbox)) {
                    System.out.println("chest");
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
//            System.out.println("Object interacted");
    }
    
    private void openChest() {
        int score = 10;
//            if (c == chests.get(0)){
//                score = 5;
//            }
//            
//            else{
//
//            }
            playing.addScore(score);
    }
    
    
    private void collectItem(Item i){
        int score = 0;
        if (i == items.get(0)){
                score = 5;
            }
            
        else{
                score = 10;
        }
        playing.addScore(score);
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
        for (Door p : doors)
            if(p.isActive())
                p.update();
    }

    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        drawDoors(g, xLvlOffset, yLvlOffset);
        drawChests(g, xLvlOffset, yLvlOffset);
        
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
                 (int) (CHEST_HEIGHT),
                 (int) (CHEST_WIDTH), 
                 null);
                chest.drawHitbox(g, xLvlOffset, yLvlOffset);
            }
             if(!chest.isActive()) {
               
                
                g.drawImage(chestSprite[0],
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
    
    private void drawItem(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Item item : items){
            if(item.isActive()) {
                if(item.getObjType() == SHIELD){
//                g.drawImage(doorImgs[type][p.getAniIndex()],
                    g.drawImage(doorImgs,
                    (int) ((item.getHitbox().x) - item.getxDrawOffset() - xLvlOffset), 
                    (int) ((item.getHitbox().y) - item.getyDrawOffset() - yLvlOffset), 
                    DOOR_WIDTH, 
                    DOOR_HEIGHT, 
                    null);
                }
                else if(item.getObjType() == SWORD){
//                g.drawImage(doorImgs[type][p.getAniIndex()],
                    g.drawImage(doorImgs,
                    (int) ((item.getHitbox().x) - item.getxDrawOffset() - xLvlOffset), 
                    (int) ((item.getHitbox().y) - item.getyDrawOffset() - yLvlOffset), 
                    DOOR_WIDTH, 
                    DOOR_HEIGHT, 
                    null);
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
    }


}
