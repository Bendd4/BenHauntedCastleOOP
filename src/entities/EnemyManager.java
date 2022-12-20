package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import java.awt.geom.Rectangle2D;
import main.Game;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] knightArr;
	private ArrayList<Knight> knights = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}

	private void addEnemies() {
		knights = LoadSave.GetKnights();
		System.out.println("Number of Knights: " + knights.size());
	}

	public void update(int[][] lvlData,Player player) {
		for (Knight c : knights)
                    if(c.isActive())
			c.update(lvlData, player);
	}

	public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
		drawCrabs(g, xLvlOffset, yLvlOffset);
	}

	private void drawCrabs(Graphics g, int xLvlOffset, int yLvlOffset) {
		for (Knight c : knights){
                    if(c.isActive()){
			g.drawImage(knightArr[c.getEnemyState()][c.getAniIndex()], 
                                (int) c.getHitbox().x - xLvlOffset-48 +c.flipX(), 
                                (int) (c.getHitbox().y - yLvlOffset-20), 
                                KNIGHT_WIDTH *c.flipW(), KNIGHT_HEIGHT, null);
                        c.drawAttackBox(g, xLvlOffset, yLvlOffset);
                        c.drawHitbox(g, xLvlOffset, yLvlOffset);
                    }
                }
	}
        
        public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Knight c : knights)
			if (c.isActive())
				if (attackBox.intersects(c.getHitbox())) {
					c.hurt(0);
					return;
				}
	}

	private void loadEnemyImgs() {
		knightArr = new BufferedImage[10][7];
		BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.KNIGHT_SPRITE);
		for (int j = 0; j < knightArr.length; j++)
			for (int i = 0; i < knightArr[j].length; i++)
                            
				knightArr[j][i] = temp.getSubimage(i * KNIGHT_WIDTH_DEFAULT, j * KNIGHT_HEIGHT_DEFAULT, KNIGHT_WIDTH_DEFAULT, KNIGHT_HEIGHT_DEFAULT);
	}
        public void resetAllEnemies() {
		for (Knight c : knights)
			c.resetEnemy();
	}
}