package game.entities;

import java.awt.*;

public class Cube extends Projectile {
    Color color;

    public Cube(double xPos, double yPos, double xDir, double yDir, boolean isMovingRight) {
        super(xPos, yPos, 25, 25, isMovingRight);
        this.xDir = xDir;
        this.yDir = yDir;
        pongSound();
    }


    @Override
    public void renderProjectile(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect((int) xPos, (int) yPos, 25, 25, 25, 25);

    }

}
