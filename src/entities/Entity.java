package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

	protected float x, y;
	protected int width, height;
        protected Rectangle2D.Float hitbox;

	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
                
                
	}
        protected void drawHitbox(Graphics g, int xLvlOffset,int yLvlOffset){
            g.setColor(Color.GREEN);
            g.drawRect((int)hitbox.x-xLvlOffset, (int)hitbox.y-yLvlOffset, (int)hitbox.width, (int)hitbox.height);
        } 
        protected void initHitbox(float x, float y, float width, float height){
            hitbox = new Rectangle2D.Float(x,y,width,height);
        } 
        public void updateHitbox(){
           
            
        }
        public Rectangle2D.Float getHitbox(){
            return hitbox;
        }

        public float getX() {
            return x;
        }
        public float getY() {
            return y;
        }

        public void setX(float x) {
            this.x = x;
        }
        public void setY(float y) {
            this.y = y;
        }
        
        public void changeLoc(int x, int y){
            this.setX((float) x);
            this.setY((float) y);
        }

        
        
}