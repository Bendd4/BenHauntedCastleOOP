    package gamestates;

import audio.AudioPlayer;
import entities.EnemyManager;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import java.awt.geom.Rectangle2D;
import levels.LevelManager;

import main.Game;
import objects.ObjectManager;
import ui.GameCompleteOverlay;
import ui.GameOverOverlay;
import ui.PauseOverlay;
import utilz.LoadSave;
    
public class Playing extends State implements Statemethods {
    private Player player;
    private int score = 0;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private ObjectManager objectManager;
    private PauseOverlay pauseOverlay;
    private GameOverOverlay gameOverOverlay;
    private GameCompleteOverlay gameCompleteOverlay;
    private boolean paused = false;

    private int xLvlOffset;
    private int leftBorder = (int) (0.3 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.7 * Game.GAME_WIDTH);
    private int lvlWidth = LoadSave.GetLevelData()[0].length;
    private int maxXOffset = (lvlWidth - Game.TILES_IN_WIDTH) * Game.TILES_SIZE;

    private int yLvlOffset;
    private int topBorder = (int) (0.5 * Game.GAME_HEIGHT);
    private int bottBorder = (int) (0.5 * Game.GAME_HEIGHT);
    private int lvlHeight = LoadSave.GetLevelData().length;
    private int maxYOffset = (lvlHeight - Game.TILES_IN_HEIGHT) * Game.TILES_SIZE;

    private boolean gameOver;
    private boolean gameComplete;
    private boolean playerDying;
    private AudioPlayer audioPlayer;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        objectManager = new ObjectManager(this);
        enemyManager = new EnemyManager(this);
        player = new Player(200, 800, (int) (150 * game.SCALE), (int) (150 * game.SCALE), this);
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        pauseOverlay = new PauseOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);
        gameCompleteOverlay = new GameCompleteOverlay(this);
    }

    @Override
    public void update() {
        if (paused) {
            pauseOverlay.update();
        } else if (gameOver) {
            gameOverOverlay.update();
        } else if (gameComplete) {
            gameCompleteOverlay.update();
        } else if (playerDying) {
            player.update();
        } else {
            levelManager.update();
            objectManager.update();
            player.update();
            enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
            checkCamMove();
        }
    }

    private void checkCamMove() {
        int playerX = (int) player.getHitbox().x;
        int diffX = playerX - xLvlOffset;
        if (diffX > rightBorder) {
            xLvlOffset += diffX - rightBorder;
        } else if (diffX < leftBorder) {
            xLvlOffset += diffX - leftBorder;
        }

        if (xLvlOffset > maxXOffset) {
            xLvlOffset = maxXOffset;
        } else if (xLvlOffset < 0) {
            xLvlOffset = 0;
        }

        int playerY = (int) player.getHitbox().y;
        int diffY = playerY - yLvlOffset;
        if (diffY > bottBorder) {
            yLvlOffset += diffY - bottBorder;
        } else if (diffY < topBorder) {
            yLvlOffset += diffY - topBorder;
        }

        if (yLvlOffset > maxYOffset) {
            yLvlOffset = maxYOffset;
        } else if (yLvlOffset < 0) {
            yLvlOffset = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, xLvlOffset, yLvlOffset);
        objectManager.draw(g, xLvlOffset, yLvlOffset);
        enemyManager.draw(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);

        if (paused) {
            pauseOverlay.draw(g);
        } else if (gameOver) {
            gameOverOverlay.draw(g);
        } else if (gameComplete) {
            gameCompleteOverlay.draw(g);
        }
    }

    public void resetAll() {
        gameOver = false;
        paused = false;
        playerDying = false;
        gameComplete = false;
        score = 0;
        player.resetAll();
        objectManager.resetAllObjects();
        enemyManager.resetAllEnemies();
//        audioPlayer = new AudioPlayer();

    }
    
    public void setGameComplete(boolean gameComplete){
        this.gameComplete = gameComplete;
    }
    public boolean getGameComplete(){
        return this.gameComplete;
    }
    
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void checkObjectHit(Rectangle2D.Float attackBox) {
        objectManager.checkObjectHit(attackBox);
    }

    public void checkItemTouched(Rectangle2D.Float hitbox) {
        objectManager.checkObjectTouched(hitbox);
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        enemyManager.checkEnemyHit(attackBox);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!gameOver) {
            if ((e.getButton() == MouseEvent.BUTTON1)) {
                player.setInteract(true);
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (!gameOver)
            if (paused) {
                pauseOverlay.mouseDragged(e);
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gameOver) {
            if (gameComplete){
                gameCompleteOverlay.mousePressed(e);
            } else if (paused) {
                pauseOverlay.mousePressed(e);
            }
        } else {
            gameOverOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!gameOver) {
            if (gameComplete){
                gameCompleteOverlay.mouseReleased(e);
            } else if (paused) {
                pauseOverlay.mouseReleased(e);
            }
        } else {
            gameOverOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!gameOver) {
            if (gameComplete){
                gameCompleteOverlay.mouseMoved(e);
            } else if (paused) {
                pauseOverlay.mouseMoved(e);
            }
        } else {
            gameOverOverlay.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver) {
            gameOverOverlay.keyPressed(e);
        } else if (gameComplete){
            gameCompleteOverlay.keyPressed(e);
        } else
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
//                    player.setUp(true);
                    break;
                case KeyEvent.VK_A:
                    player.setLeft(true);
//                    System.out.println("A");
                    break;
                case KeyEvent.VK_S:
//                    player.setDown(true);
                    break;
                case KeyEvent.VK_D:
                    player.setRight(true);
//                    System.out.println("A");
                    break;
                case KeyEvent.VK_SPACE:
                    player.setInteract(true);
                    break;
                case KeyEvent.VK_ESCAPE:
                    paused = !paused;
                    break;
            }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!gameOver && !gameComplete)
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
//        paused = true;
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayerDying(boolean playerDying) {
        this.playerDying = playerDying;
    }

    public ObjectManager getObjectManager(){
        return objectManager;
    }
    
    public void addScore(int score){
        this.score += score;
        System.out.println("Score: " + this.score);
    }
    public int getScore(){
        return this.score;
    }

}
