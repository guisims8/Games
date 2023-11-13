package entities;

import main.GameWindow;
import music.C4;
import music.Music;

import java.awt.*;

public class Cube extends Projectile {
    Color color;

    public Cube(int xPos, int yPos, double xDir, double yDir) {
        super(xPos, yPos, 25, 25);
        this.xDir = xDir;
        this.yDir = yDir;
        pongSound();
    }



    @Override
    public void renderProjectile(Graphics g) {
        render(g);
    }

}
