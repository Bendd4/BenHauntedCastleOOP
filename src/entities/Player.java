/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import main.Game;

import utilz.LoadSave;

import static utilz.Constants.Directions.*;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.CanMoveHere;

/**
 *
 * @author Asus
 */
public class Player extends Entity {
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 35;
    private int playerAction = IDLE;
    private boolean left, up, right, down;
    private float playerSpeed = 0.75f;
    private int[][] lvlData;
    private float xDrawOffset = 64*Game.SCALE;
    private float yDrawOffset = 23*Game.SCALE;
    private boolean moving = false, attacking = false;

   public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
                initHitbox(x, y, 35 * Game.SCALE, 63 * Game.SCALE);
}   

    public void update() {
        updatePos();
        
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g, int xLvlOffset, int yLvlOffset) {
       g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset) - xLvlOffset, (int) (hitbox.y - yDrawOffset)  - yLvlOffset, width, height, null);
//       drawHitbox(g, xLvlOffset);
    }
    //

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }

        }

    }

    private void setAnimation() {
        int startAni = playerAction;
        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
        if (attacking) {
            playerAction = ATTACK_1;
        }
        if (startAni != playerAction) {
            resetAniTick();
        }
    }

  	private void updatePos() {
		moving = false;
		if (!left && !right && !up && !down)
			return;

//                                if (!InAir){
                                        if ((!left && !right) || (left && right)){
                                            return;
                                        }
//                                }
                
		float xSpeed = 0, ySpeed = 0;

		if (left && !right)
			xSpeed = -playerSpeed;
		else if (right && !left)
			xSpeed = playerSpeed;

		if (up && !down)
			ySpeed = -playerSpeed;
		else if (down && !up)
			ySpeed = playerSpeed;

//		if (CanMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
//			this.x += xSpeed;
//			this.y += ySpeed;
//			moving = true;
//		}
                if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
			hitbox.y += ySpeed;
			moving = true;
		}

		

	}

    private void loadAnimations() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[10][7];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 132, j * 86, 120, 90);
            }
        }


    }
    public void loadLvlData(int[][] lvldata){
        this.lvlData = lvldata;
    }
    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

}
