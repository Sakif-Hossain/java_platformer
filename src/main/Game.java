package main;

import entities.Player;

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

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        GameWindow gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        player = new Player(200, 200);
    }

    private void startGameLoop() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    public void update() {
        player.update();
    }

    public void render(Graphics g) {player.render(g);}

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
}
