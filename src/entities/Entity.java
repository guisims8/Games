package entities;

import java.awt.*;

public abstract class Entity {
    public int xPos, yPos, height, width;
    public double xDir,yDir;

    public Entity(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(xPos, yPos, width, height);
    }

    public double getYDir() {
        return yDir;
    }

    public void setYDir(double yDir) {
        this.yDir = yDir;
    }

}
