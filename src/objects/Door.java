package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class Door extends GameObject {

    public Door(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        initHitbox(x, y - DOOR_WIDTH, DOOR_HEIGHT, DOOR_WIDTH);
        xDrawOffset = (int) (1* Game.SCALE);
        yDrawOffset = (int) (1* Game.SCALE);
    }
    
    public void update() {
        updateAnimationTick();
    }
}
