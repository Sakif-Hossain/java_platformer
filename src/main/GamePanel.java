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

    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animation;
    private int animationTick, animationIndex, animationSpeed = 15;
    private int playerAction = JUMP;
    private int playerDirection = -1;
    private boolean moving = false;

    public GamePanel() {
        MouseInputs mouseInputs = new MouseInputs(this);
        importImage();
        loadAnimation();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
    }

    private void loadAnimation() {
        animation = new BufferedImage[9][6];
        for (int row = 0; row < animation.length; row++) {
            for (int col = 0; col < animation[row].length; col++) {
                animation[row][col] = img.getSubimage(col*64, row*40, 64, 40);
            }
        }
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension screenSize = new Dimension(1280, 800);
        setMinimumSize(screenSize);
        setMaximumSize(screenSize);
        setPreferredSize(screenSize);
    }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetNumberOfSprite(playerAction)) {
                animationIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updatePosition() {
        if (moving) {
            switch (playerDirection) {
                case LEFT:
                    xDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(animation[playerAction][animationIndex], (int)xDelta, (int)yDelta, 128, 80, null);
    }

}
