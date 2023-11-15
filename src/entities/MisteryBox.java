package entities;

import display.GameWindow;
import main.HelperMethods;

import java.awt.*;

public class MisteryBox extends Entity {
    Color color;
    private int counter=0;
    private boolean barSize,controls;

    public MisteryBox() {
        super(HelperMethods.randomNumber(0, GameWindow.WIDTH), HelperMethods.randomNumber(0, GameWindow.WIDTH), 25, 25);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect((int) xPos, (int) yPos, (int) width, (int) height);
    }


    public void changeBarSize(Player p1, Player p2, Projectile projectile) {
        if (projectile.isMovingRight) {
            p1.height += 20;
            p2.height -= 20;
        } else {
            p1.height -= 20;
            p2.height += 20;
        }
    }

    public void invertControls(Player p1, Player p2, Projectile projectile) {
        if (projectile.isMovingRight) {
            p2.invertedControls = true;
        } else {
            p1.invertedControls = false;
        }
    }

    private void buffDebuffTimer(int timer) {
        counter++;
        if (counter >= timer) {

        }
    }}
