package main;

import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.io.InputStream;

//import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

//import static utilz.Constants.PlayerConstants.*;
//import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private Game game;
	// private float xDelta = 100, yDelta = 100;
	// private BufferedImage img;
	// private BufferedImage[][] animations;
	// private int aniTick, aniIndex, aniSpeed = 15;
	// private int playerAction = IDLE;
	// private int playerDir = -1;
	// private boolean moving = false;

	public GamePanel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;

		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

	// private void loadAnimations() {
	// animations = new BufferedImage[10][7];
	// for (int j = 0; j < animations.length; j++)
	// for (int i = 0; i < animations[j].length; i++)
	// animations[j][i] = img.getSubimage(i * 132, j * 86, 120, 90);
	// }

	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
	}

	// private void setAnimation() {
	// if (moving)
	// playerAction = RUNNING;
	// else
	// playerAction = IDLE;
	// }

	// private void updatePos() {
	// if (moving) {
	// switch (playerDir) {
	// case LEFT:
	// xDelta -= 5;
	// break;
	// case UP:
	// yDelta -= 5;
	// break;
	// case RIGHT:
	// xDelta += 5;
	// break;
	// case DOWN:
	// yDelta += 5;
	// break;
	// }
	// }
	// }

	public void updateGame() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);

	}

	public Game getGame() {
		return game;
	}

}
