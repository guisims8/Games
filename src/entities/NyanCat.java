package entities;

import java.awt.*;

public class NyanCat extends Entity implements Projectile {


    public NyanCat(int xPos, int yPos, int heigh, int width) {
        super(xPos, yPos, heigh, width);
    }

    @Override
    public void updatePosition() {

    }

    @Override
    public void renderProjectile(Graphics g) {
        render(g);
    }
}
