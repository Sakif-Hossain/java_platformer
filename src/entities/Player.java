package entities;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.*;
import static utils.HelperMethods.CanMove;

public class Player extends Entity {
    private BufferedImage[][] animation;
    private int animationTick, animationIndex, animationSpeed = 15;
    private int playerAction = JUMP;
    private boolean moving = false, attacking = false;
    private boolean up, down, left, right;
    private float playerSpeed = 2.0f;
    private int[][] levelData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimation();
        initHitBox(x, y, 20*Game.SCALE, 28*Game.SCALE);
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animation[playerAction][animationIndex], (int)(hitBox.x - xDrawOffset), (int)(hitBox.y - yDrawOffset), width, height, null);
        drawHitBox(g);
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetNumberOfSprite(playerAction)) {
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        int animationStart = playerAction;

        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }

        if (attacking) {
            playerAction = ATTACK;
        }

        if (animationStart != playerAction) {
            resetAnimationTick();
        }
    }

    private void resetAnimationTick() {
        animationIndex = 0;
        animationTick = 0;
    }

    private void updatePosition() {
        moving = false;
        float xSpeed = 0, ySpeed = 0;

        if (!left && !right && !up && !down) {
            return;
        }

        if (left && !right) {
            xSpeed = -playerSpeed;
        } else if (right && !left) {
            xSpeed = playerSpeed;
        }

        if (up && !down) {
            ySpeed = -playerSpeed;
        } else if (down && !up) {
            ySpeed = playerSpeed;
        }

        if (CanMove(hitBox.x+xSpeed, hitBox.y +ySpeed, hitBox.width, hitBox.height, levelData)) {
            hitBox.x += xSpeed;
            hitBox.y += ySpeed;
            moving = true;
        }
    }

    private void loadAnimation() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animation = new BufferedImage[9][6];
        for (int row = 0; row < animation.length; row++) {
            for (int col = 0; col < animation[row].length; col++) {
                animation[row][col] = img.getSubimage(col*64, row*40, 64, 40);
            }
        }
    }

    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;
    }

    public void resetDirBool() {
        left = right = down = up = false;
    }

    public void setAttacking(Boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
