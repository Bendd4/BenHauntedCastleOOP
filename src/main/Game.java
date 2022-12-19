package main;

import entities.Player;
import levels.LevelManager;
import ui.AudioOptions;

import java.awt.Graphics;

import gamestates.GameOptions;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 60;
	private final int UPS_SET = 225;
	private Player player;
	private LevelManager levelManager;

	private Playing playing;
	private Menu menu;
	private GameOptions gameOptions;
	private AudioOptions audioOptions;

	public final static int TILES_DEFAULT_SIZE = 36;
	public final static float SCALE = 1f;
	public final static int TILES_IN_WIDTH = 20;
	public final static int TILES_IN_HEIGHT = 11;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

	public Game() {
		initClasses();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
                gamePanel.setFocusable(true);
		gamePanel.requestFocus();

		startGameLoop();

	}

	private void initClasses() {
		audioOptions = new AudioOptions();
		menu = new Menu(this);
		levelManager = new LevelManager(this);
		playing = new Playing(this);
		gameOptions = new GameOptions(this);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		switch (Gamestate.state) {
			case MENU:
				menu.update();
				break;
			case PLAYING:
				levelManager.update();
				playing.update();
				break;
			case OPTION:
				gameOptions.update();
				break;
			case QUIT:
			default:
				System.exit(0);
				break;
		}
	}

	public void render(Graphics g) {
		switch (Gamestate.state) {
			case MENU:
				menu.draw(g);
				break;
			case PLAYING:
				playing.draw(g);
				break;
			case OPTION:
				gameOptions.draw(g);
				break;
			default:
				break;
		}
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		double deltaF = 0;
		double deltaU = 0;
		long lastCheck = System.currentTimeMillis();

		while (true) {
			long currentTime = System.nanoTime();
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			if (deltaF >= 1) {
				gamePanel.repaint();
				deltaF--;
				frames++;
			}
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
//				System.out.println("FPS: " + frames + "//UPS: " + updates);
				updates = 0;
				frames = 0;
			}
		}

	}

	public Player getPlayer() {
		return player;
	}

	public void windowFocusLost() {
		if (Gamestate.state == Gamestate.PLAYING) {
			playing.getPlayer().resetDirBooleans();
		}
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public GameOptions getGameOptions() {
		return gameOptions;
	}

	public AudioOptions getAudioOption() {
		return audioOptions;
	}
}
