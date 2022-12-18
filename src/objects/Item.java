package objects;

import main.Game;

public class Item extends GameObject {

    public Item(int x, int y, int objType) {
        super(x, y, objType);
        doAnimation = true;
        initHitbox(7, 14, 7, 14);
        xDrawOffset = (int) (1* Game.SCALE);
        yDrawOffset = (int) (1* Game.SCALE);
    }
    
    public void update() {
        updateAnimationTick();
    }
}