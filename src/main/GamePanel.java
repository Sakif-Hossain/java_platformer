package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

/**
 * Drawing things that we need to show in the frame.
 */

public class GamePanel extends JPanel {
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        MouseInputs mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
    }

    private void setPanelSize() {
        Dimension screenSize = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(screenSize);
        setMaximumSize(screenSize);
        setPreferredSize(screenSize);

        System.out.println(screenSize.width + "x" + screenSize.height);
    }

    public void updateGame() {
    }

    public void paint(Graphics g) {
        super.paint(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }

}
