package entities;

public abstract class Entity {
    protected int xPos,yPos, height,width;

    public Entity(int xPos, int yPos, int height, int width) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
    }
}
