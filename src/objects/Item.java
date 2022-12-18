package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class Item extends GameObject {

    public Item(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        initHitbox(DOOR_WIDTH, DOOR_HEIGHT, DOOR_HEIGHT, DOOR_HEIGHT);
        xDrawOffset = (int) (1* Game.SCALE);
        yDrawOffset = (int) (1* Game.SCALE);
    }
    
    public void update() {
        updateAnimationTick();
    }
}
