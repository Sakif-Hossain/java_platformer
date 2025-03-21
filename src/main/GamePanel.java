package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

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
        Dimension screenSize = new Dimension(1280, 800);
        setMinimumSize(screenSize);
        setMaximumSize(screenSize);
        setPreferredSize(screenSize);
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
