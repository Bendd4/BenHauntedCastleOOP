package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import static utilz.Constants.EnemyConstants.*;

import main.Game;
import static utilz.Constants.Directions.*;

public class Knight extends Enemy {

         private Rectangle2D.Float attackBox;
         private int attackBoxOffsetX;
    
	public Knight(float x, float y) {
		super(x, y, KNIGHT_WIDTH, KNIGHT_HEIGHT, KNIGHT);
		initHitbox(x, y, (int) (35 * Game.SCALE), (int) (63 * Game.SCALE));
                initAttackBox();

	}
        
        private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x, y, (int) (65 * Game.SCALE), (int) (63 * Game.SCALE));
		attackBoxOffsetX = (int) (Game.SCALE);
	}

	public void update(int[][] lvlData, Player player) {
		updateBehavior(lvlData, player);
		updateAnimationTick();
                updateAttackBox();

	}
        
        private void updateAttackBox() {
		attackBox.x = hitbox.x - attackBoxOffsetX-12;
		attackBox.y = hitbox.y;

	}
        
        

	private void updateBehavior(int[][] lvlData, Player player) {
		if (firstUpdate)
			firstUpdateCheck(lvlData);

		if (inAir)
			updateInAir(lvlData);
		else {
			 switch(enemyState){
                    case IDLE:
                           if(isPlayerInRange(player)){
                            turnTowardsPlayer(player);
                              newState(RUNNING);
                            move(lvlData);
                        }
                       if(isPlayerCloseForAttack(player)){
                           
                           newState(ATTACK);
                           
                       } 
                      
//                        newState(RUNNING);
                        break;
                    case RUNNING:
                        if(isPlayerInRange(player)){
                            turnTowardsPlayer(player);
                        }
                       if(isPlayerCloseForAttack(player)){
                           
                           newState(ATTACK);
                       } 
                       move(lvlData);
                        break;
                    case ATTACK:
                        if(aniIndex == 0){
                            attackChecked = false;
                        }
                        if(aniIndex == 3 && !attackChecked){
                            checkPlayerHit(attackBox,player);
                        }
                        break;
                    case HIT:
                        break;
                }
            
		}

	}
        
        public void drawAttackBox(Graphics g, int xLvlOffset, int yLvlOffset) {
		g.setColor(Color.red);
		g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y -  yLvlOffset, (int) attackBox.width, (int) attackBox.height);
	}

        public int flipX() {
		if (walkDir == LEFT)
			return width;
		else
			return 0;
	}
        public int flipW() {
		if (walkDir == LEFT)
			return -1;
		else
			return 1;

	}

}