package objects;

import main.Game;
import static utilz.Constants.ObjectConstants.*;

public class Item extends GameObject {

    public Item(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        initHitbox(x, y, 70, 45);
        xDrawOffset = (int) (1* Game.SCALE);
        yDrawOffset = (int) (1* Game.SCALE);
    }
    
    public void update() {
        updateAnimationTick();
    }
}
