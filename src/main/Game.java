package main;

import entities.Player;
import java.awt.Graphics;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
        private final int UPS_SET  = 200;
        private Player player;
	public Game() {
                 initClasses(); 
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
              
		startGameLoop();
                
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
        public void update(){
            player.update();
        }
        public void render(Graphics g){
            player.render(g);
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
                        if(deltaU >= 1){
                            update();
                            updates++;
                            deltaU--;
                        }
                        if(deltaF >=1){
                             gamePanel.repaint();
				deltaF--;
				frames++; 
                        }
                        


			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + "//UPS: " + updates);
                                updates = 0;
				frames = 0;
			}
		}

	}

    private void initClasses() {
        player = new Player(200,200);
    }
    public Player getPlayer(){
        return player;
    }
    public void windowFocusLost(){
        player.resetDirBooleans();
    }
   

}
