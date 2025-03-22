package entities;

import java.awt.*;

// you cannot create objects of abstract class instead only extend them.
public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Rectangle hitBox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitBox();
    }

    protected void drawHitBox(Graphics g) {
        // Debugging purposes
        g.setColor(Color.PINK);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    private void initHitBox() {
        hitBox = new Rectangle((int) x, (int) y, width, height);
    }

    protected void updateHitBox() {
        hitBox.x = (int) x;
        hitBox.y = (int) y;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
