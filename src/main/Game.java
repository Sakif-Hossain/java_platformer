package main;

import entities.Player;
import levels.LevelHandler;

import java.awt.*;

/**
 * This class binds all the component together, such as GameWindow, GamePanel
 */

public class Game implements Runnable {

    private Thread gameLoopThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private GamePanel gamePanel;
    private int frames = 0;
    private int updates = 0;
    private long lastChecked = System.currentTimeMillis();
    private Player player;
    private LevelHandler levelHandler;

    public static final int TILE_DEFAULT_SIZE = 32;
    public static final float SCALE = 1.5f;
    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;
    public static final int TILE_SIZE = (int) (TILE_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;


    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        GameWindow gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        levelHandler = new LevelHandler(this);
        player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
        player.loadLevelData(levelHandler.getCurrentLevel().getLevelData());
    }

    private void startGameLoop() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    public void update() {
        player.update();
        levelHandler.update();
    }

    public void render(Graphics g) {
        levelHandler.draw(g);
        player.render(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;

        long previousTime = System.nanoTime();
        double deltaU = 0;
        double deltaF = 0;

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
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastChecked >= 1000) {
                lastChecked = System.currentTimeMillis();
                System.out.println("FPS: " + frames + "| Updates: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBool();
    }
}
