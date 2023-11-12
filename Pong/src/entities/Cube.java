package entities;

import java.awt.*;

public class Cube extends Entity implements Projectile{
Color color;


    public Cube(int xPos, int height, int width) {
        super(70,70,20,20);
    }

    @Override
    public void updatePosition() {

    }
}
