package gamestates;

import entities.EnemyManager;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;

import main.Game;
import ui.PauseOverlay;
import utilz.LoadSave;

public class Playing extends State implements Statemethods {
    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private PauseOverlay pauseOverlay;
    private boolean paused = false;
    
    private int xLvlOffset;
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private int lvlWidth = LoadSave.GetLevelData()[0].length;
    private int maxXOffset = (lvlWidth - Game.TILES_IN_WIDTH) * Game.TILES_SIZE;
    
    private int yLvlOffset;
    private int topBorder = (int) (0.3 * Game.GAME_HEIGHT);
    private int bottBorder = (int) (0.7 * Game.GAME_HEIGHT);
    private int lvlHeight = LoadSave.GetLevelData().length;
    private int maxYOffset = (lvlHeight - Game.TILES_IN_HEIGHT) * Game.TILES_SIZE;

    
    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        player = new Player(200, 692, (int) (150 * game.SCALE), (int) (90 * game.SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        pauseOverlay = new PauseOverlay(this);
    }

    @Override
    public void update() {
        if (!paused) {
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
            checkCamMove();
        } else {
            pauseOverlay.update();
        }
    }
    
    private void checkCamMove() {
        int playerX = (int) player.getHitbox().x;
        int diffX = playerX -xLvlOffset;
        if (diffX > rightBorder){
            xLvlOffset += diffX - rightBorder;
        }
        else if (diffX < leftBorder){
            xLvlOffset += diffX - leftBorder;
        }
        
        if (xLvlOffset > maxXOffset){
            xLvlOffset = maxXOffset;
        }
        else if (xLvlOffset < 0){
            xLvlOffset = 0;
        }
        
        int playerY = (int) player.getHitbox().y;
        int diffY = playerY - yLvlOffset;
        if (diffY > bottBorder){
            yLvlOffset += diffY - bottBorder;
        }
        else if (diffY < topBorder){
            yLvlOffset += diffY - topBorder;
        }
        
        if (yLvlOffset > maxYOffset){
            yLvlOffset = maxYOffset;
        }
        else if (yLvlOffset < 0){
            yLvlOffset = 0;
        }
        
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);
        enemyManager.draw(g, xLvlOffset, yLvlOffset);

        if (paused) {
            pauseOverlay.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((e.getButton() == MouseEvent.BUTTON1)) {
            player.setAttacking(true);
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseDragged(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused) {
            pauseOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(true);
                break;
            case KeyEvent.VK_A:
                player.setLeft(true);
                System.out.println("A");
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                System.out.println("A");
                break;
            case KeyEvent.VK_SPACE:
                player.setAttacking(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
        }
    }

    public void unpauseGame() {
        paused = false;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }

    
}
