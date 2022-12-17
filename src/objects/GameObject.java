package objects;

import static utilz.Constants.ObjectConstants.*;

import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics;

import main.Game;

public class GameObject {

    protected int x, y, objType;
    protected Rectangle2D.Float hitbox;
    protected boolean doAnimation, active = true;
    protected int aniTick, aniIndex;
    protected int xDrawOffset, yDrawOffset;
    protected int aniSpeed = 25;

    public GameObject(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }

    protected void updateAnimationTick() {
            
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(objType)) {
				aniIndex = 0;
			}
		}
	}

    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        active = true;

        doAnimation = true;
    }

    protected void initHitbox(float x, float y, float width, float height){
        hitbox = new Rectangle2D.Float(x,y,width,height);
    } 

    public void drawHitbox(Graphics g, int xLvlOffset,int yLvlOffset){
        g.setColor(Color.GREEN);
        g.drawRect((int)hitbox.x - xLvlOffset, (int)hitbox.y - yLvlOffset, (int)hitbox.width, (int)hitbox.height);
    }

    public int getObjType() {
        return objType;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getxDrawOffset() {
        return xDrawOffset;
    }   

    public int getyDrawOffset() {
        return yDrawOffset;
    }
   
    public int getAniIndex() {
        return aniIndex;
    }

}