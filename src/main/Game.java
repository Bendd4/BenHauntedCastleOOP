package main;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private LevelManager levelManager;

	public static final int TILES_DEFAULT_SIZE = 32;
	public static final float SCALE = 1.25f;
	public static final int TILES_IN_WIDTH = 26;
	public static final int TILES_IN_HEIGHT = 14;
	public static final int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

	public Game() {

		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		startGameLoop();

	}

	private void initClasses(){
		player = new Player(200, 200);
	}


	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();

		int frames = 0;
		long lastCheck = System.currentTimeMillis();

		while (true) {

			now = System.nanoTime();
			if (now - lastFrame >= timePerFrame) {
				gamePanel.repaint();
				lastFrame = now;
				frames++;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}

	}

}
