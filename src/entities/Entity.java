package entities;

// you cannot create objects of abstract class instead only extend them.
public abstract class Entity {

    protected float x, y;
    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
