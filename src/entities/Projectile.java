package entities;

import display.GameWindow;
import music.C4;
import music.Music;

import java.awt.*;

public class Projectile extends Entity {
    public boolean isMovingRight;
    private String[] pongSounds = C4.NOTES;
    Music pong;


    public Projectile(double xPos, double yPos, double width, double height, boolean isMovingRight) {
        super(xPos, yPos, width, height);
        this.isMovingRight = isMovingRight;
    }

    public void renderProjectile(Graphics g) {
        super.render(g);
    }

    ;

    public void updatePosition(Player player1, Player player2) {
        xPos += xDir;
        yPos += yDir;
        System.out.println(isMovingRight);
        if (!isMovingRight) {
            checkP1X(player1);
        } else {
            checkP2X(player2);
        }
        checkWall();
    }

    ;

    private void checkWall() {
        if (yPos + height + 40 > GameWindow.HEIGHT || yPos < 10) {
            yDir *= -1;
            pongSound();
        }
    }

    public void pongSound() {
        pong = new Music(pongSounds[0]);
        pong.play();
        pong.kill();
    }


    private void checkP2X(Player p2) {
        if (xPos + width > p2.xPos - p2.width - p2.buffer) {
            // if (xPos + width > p2.xPos - p2.buffer && yPos + height > p2.yPos -
            //       p2.buffer && yPos < p2.yPos + p2.height + p2.buffer) {
            xPos -= xDir;
            yPos -= yDir;
            xDir *= -1;
            isMovingRight = false;
            pongSound();
        }
    }

    private void checkP1X(Player p1) {
        if (xPos < p1.xPos + p1.width + p1.buffer) {
            //     if (xPos < p1.xPos + p1.width + p1.buffer && yPos + height > p1.yPos -
              //         p1.buffer && yPos < p1.yPos + p1.height + p1.buffer ) {
            xPos -= xDir;
            yPos -= yDir;
            xDir *= -1;
            isMovingRight = true;
            pongSound();
        }
    }

}
