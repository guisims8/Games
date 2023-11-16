package game.entities;

import java.awt.*;

public abstract class Entity {
    public double xPos, yPos, height, width;
    public double xDir,yDir;

    public Entity(double xPos, double yPos, double width, double height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int)xPos, (int)yPos, (int)width, (int)height);
    }

    public double getYDir() {
        return yDir;
    }

    public void setYDir(double yDir) {
        this.yDir = yDir;
    }

}
