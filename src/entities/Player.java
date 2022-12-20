/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import gamestates.Playing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import main.Game;
import audio.AudioPlayer;

import utilz.LoadSave;

import static utilz.Constants.Directions.*;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

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
    private float xDrawOffset = 64 * Game.SCALE;
    private float yDrawOffset = 23 * Game.SCALE;
    private boolean moving = false, attacking = false;

    private BufferedImage statusBarImg;

    private int statusBarWidth = (int) (192 * Game.SCALE);
    private int statusBarHeight = (int) (58 * Game.SCALE);
    private int statusBarX = (int) (10 * Game.SCALE);
    private int statusBarY = (int) (10 * Game.SCALE);

    private int healthBarWidth = (int) (150 * Game.SCALE);
    private int healthBarHeight = (int) (5 * Game.SCALE);
    private int healthBarXStart = (int) (34 * Game.SCALE);
    private int healthBarYStart = (int) (13 * Game.SCALE);

    private int maxHealth = 30;
    private int currentHealth = maxHealth;
    private int healthWidth = healthBarWidth;

    private Rectangle2D.Float interactBox;

    private int flipX = 0;
    private int flipW = 1;

    private boolean interactChecked;
    private Playing playing;

    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        loadAnimations();
        initHitbox(x, y, 35 * Game.SCALE, 63 * Game.SCALE);
        initInteractBox();
    }

    private void initInteractBox() {

        interactBox = new Rectangle2D.Float(x, y, 65 * Game.SCALE, 63 * Game.SCALE);
    }

    public void update() {
        updateHealthBar();
        if (currentHealth <= 0) {
            if (playerAction != DEAD) {
                playerAction = DEAD;
                aniTick = 0;
                aniIndex = 0;
                playing.setPlayerDying(true);
                playing.getGame().getAudioPlayer().playEffect(AudioPlayer.DIE);
            } else if (aniIndex == GetSpriteAmount(DEAD) - 1 && aniTick >= aniSpeed - 1) {
                playing.setGameOver(true);
                playing.getGame().getAudioPlayer().stopSong();
                playing.getGame().getAudioPlayer().playEffect(AudioPlayer.GAMEOVER);
            } else {
                updateAnimationTick();
            }

            // playing.setGameOver(true);
            return;
        }

        updateInteractBox();
        if (attacking) {
            checkInteract();
        }
        updatePos();
        if (moving)
            checkItemTouched();

        updateAnimationTick();
        setAnimation();

    }

    private void checkItemTouched() {
//        playing.checkItemTouched(hitbox);
    }

    private void checkInteract() {
        if (interactChecked || aniIndex != 1) {
            return;
        }
        interactChecked = true;
        playing.checkEnemyHit(interactBox);
        playing.checkObjectHit(interactBox);
        playing.getGame().getAudioPlayer().playAttackSound();

    }

    private void updateInteractBox() {

        interactBox.x = hitbox.x - 15;
        interactBox.y = hitbox.y;

    }

    private void updateHealthBar() {
        healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
    }

    public void render(Graphics g, int xLvlOffset, int yLvlOffset) {
        g.drawImage(animations[playerAction][aniIndex],
                (int) (hitbox.x - xDrawOffset) - xLvlOffset + flipX,
                (int) (hitbox.y - yDrawOffset) - yLvlOffset-17,
                width * flipW, height, null);
        drawHitbox(g, xLvlOffset, yLvlOffset);
        drawInteractBox(g, xLvlOffset, yLvlOffset);
        drawUI(g);
    }

    private void drawInteractBox(Graphics g, int lvlOffsetX, int yLvlOffset) {
        g.setColor(Color.red);
        g.drawRect((int) interactBox.x - lvlOffsetX, (int) interactBox.y - yLvlOffset, (int) interactBox.width,
                (int) interactBox.height);

    }

    private void drawUI(Graphics g) {
        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(Color.red);
        g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
                interactChecked = false;
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

        // if (!InAir){
        if ((!left && !right) || (left && right)) {
            return;
        }
        // }

        float xSpeed = 0, ySpeed = 0;

        if (up)
            ySpeed = -playerSpeed;
        else if (down)
            ySpeed = playerSpeed;
        if (left) {
            xSpeed -= playerSpeed;
            flipX = width;
            flipW = -1;
        }
        if (right) {
            xSpeed += playerSpeed;
            flipX = 0;
            flipW = 1;
        }
        // if (CanMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
        // this.x += xSpeed;
        // this.y += ySpeed;
        // moving = true;
        // }
        //        playing.getGame().getAudioPlayer().playEffect(AudioPlayer.JUMP);
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            moving = true;
        }

    }

    public void changeHealth(int value) {
        currentHealth += value;

        if (currentHealth <= 0)
            currentHealth = 0;
        else if (currentHealth >= maxHealth)
            currentHealth = maxHealth;
    }
    

    private void loadAnimations() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[5][8];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 150, j *150, 150, 150);
            }
        }
        statusBarImg = LoadSave.getSpriteAtlas(LoadSave.STATUS_BAR);

    }

    public void loadLvlData(int[][] lvldata) {
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

    public void setInteract(boolean attacking) {
        this.attacking = attacking;
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    public void resetAll() {
        resetDirBooleans();

        attacking = false;
        moving = false;
        playerAction = IDLE;
        currentHealth = maxHealth;

        hitbox.x = x;
        hitbox.y = y;

        // if (!IsEntityOnFloor(hitbox, lvlData))
        // inAir = true;
    }

    
}
