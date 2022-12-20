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
	private BufferedImage[][] crabbyArr;
	private ArrayList<Knight> crabbies = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}

	private void addEnemies() {
		crabbies = LoadSave.GetCrabs();
		System.out.println("size of crabs: " + crabbies.size());
	}

	public void update(int[][] lvlData,Player player) {
		for (Knight c : crabbies)
                    if(c.isActive())
			c.update(lvlData, player);
	}

	public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
		drawCrabs(g, xLvlOffset, yLvlOffset);
	}

	private void drawCrabs(Graphics g, int xLvlOffset, int yLvlOffset) {
		for (Knight c : crabbies){
                    if(c.isActive()){
			g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], 
                                (int) c.getHitbox().x - xLvlOffset-48 +c.flipX(), 
                                (int) (c.getHitbox().y - yLvlOffset-20), 
                                CRABBY_WIDTH *c.flipW(), CRABBY_HEIGHT, null);
                        c.drawAttackBox(g, xLvlOffset, yLvlOffset);
                        c.drawHitbox(g, xLvlOffset, yLvlOffset);
                    }
                }
	}
        
        public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for (Knight c : crabbies)
			if (c.isActive())
				if (attackBox.intersects(c.getHitbox())) {
					c.hurt(0);
					return;
				}
	}

	private void loadEnemyImgs() {
		crabbyArr = new BufferedImage[10][7];
		BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.CRABBY_SPRITE);
		for (int j = 0; j < crabbyArr.length; j++)
			for (int i = 0; i < crabbyArr[j].length; i++)
                            
				crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
	}
        public void resetAllEnemies() {
		for (Knight c : crabbies)
			c.resetEnemy();
	}
}