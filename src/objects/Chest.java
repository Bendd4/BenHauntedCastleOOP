/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.DOOR_HEIGHT;
import static utilz.Constants.ObjectConstants.DOOR_WIDTH;

/**
 *
 * @author Asus
 */
public class Chest extends GameObject{
    
    public Chest(int x, int y, int objType) {
      super(x, y, objType);
        doAnimation = true;
        initHitbox(x, y-9, 50, 50);
        xDrawOffset = (int) (1* Game.SCALE);
        yDrawOffset = (int) (1* Game.SCALE);
    }
    
    public void update() {
        updateAnimationTick();
    }
    
}
