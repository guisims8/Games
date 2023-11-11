package entities;

import java.awt.*;

public class Cube extends Entity implements Projectile {
    Color color;
    public Cube(int xPos, int yPos, double xDir, double yDir) {
        super(xPos, yPos, 25, 25);
        this.xDir = xDir;
        this.yDir = yDir;
    }
    @Override
    public void updatePosition() {
        xPos += xDir;
        yPos += yDir;
    }


    @Override
    public void renderProjectile(Graphics g) {
        render(g);
    }
}
