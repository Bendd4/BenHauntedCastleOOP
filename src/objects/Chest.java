package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class Chest extends GameObject{
    
    public Chest(int x, int y, int objType) {
      super(x, y, objType);
        doAnimation = true;
        initHitbox(x, y+9, CHEST_WIDTH*Game.SCALE, CHEST_HEIGHT*Game.SCALE);
        xDrawOffset = (int) (1* Game.SCALE);
        yDrawOffset = (int) (1* Game.SCALE);
    }
    
    public void update() {
        updateAnimationTick();
    }
    
}
