package game.entities;

import game.controller.Game;
import game.display.GameWindow;
import game.utility.GameConstants;
import game.utility.HelperMethods;
import resources.music.C4;
import resources.music.Music;

import java.awt.*;

public class Projectile extends Entity {
    public boolean isMovingRight;
    private String[] pongSounds = C4.NOTES;
    Music pong;
    private double centerPosition;


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
        // if (xPos + width > p2.xPos - p2.buffer) {
        if (xPos + width > p2.xPos - p2.buffer && yPos + height > p2.yPos -
                p2.buffer && yPos < p2.yPos + p2.height + p2.buffer) {

            setNewDirection(p2);
            isMovingRight = false;
            pongSound();
            Game.increaseScore(1);

        }

        if (xPos + width > p2.xPos + p2.width) {
            Game.isGameOver = true;
        }
    }

    private void setNewDirection(Player player) {


        centerPosition = this.yPos + this.height / 2;
        double firstSectionEnd = player.yPos + player.height / 4;
        double secondSectionEnd = player.yPos + (player.height / 4) * 3;
        double absolutSpeed = HelperMethods.getAbsolutSpeed(xDir, yDir);
        double setXDir = xDir / Math.abs(xDir) * -1;


        if (centerPosition < firstSectionEnd) {
            System.out.println("upper section");
            if (yDir < 0) {
                xDir = absolutSpeed * GameConstants.COS_70 * setXDir;
                yDir = absolutSpeed * GameConstants.SIN_70 * -1;
            } else {
                xDir = absolutSpeed * GameConstants.SIN_45 * setXDir;
                yDir = absolutSpeed * GameConstants.SIN_45 * -1;
            }
        } else if (centerPosition > secondSectionEnd) {
            System.out.println("down section");

            if (yDir < 0) {
                xDir = absolutSpeed * GameConstants.SIN_45 * setXDir;
                yDir = absolutSpeed * GameConstants.SIN_45;
            } else {
                xDir = absolutSpeed * GameConstants.COS_70 * setXDir;
                yDir = absolutSpeed * GameConstants.SIN_70;
            }


        } else {
            System.out.println("middle section");
            xDir = absolutSpeed * GameConstants.COS_20 * setXDir;
            yDir = absolutSpeed * GameConstants.SIN_20 * (yDir / Math.abs(yDir));
        }
    }


    private void checkP1X(Player p1) {
        //   if (xPos < p1.xPos + p1.width + p1.buffer) {
        if (xPos < p1.xPos + p1.width + p1.buffer && yPos + height > p1.yPos -
                p1.buffer && yPos < p1.yPos + p1.height + p1.buffer) {
            setNewDirection(p1);
            isMovingRight = true;
            pongSound();
            Game.increaseScore(1);
        }
        if (xPos < p1.xPos) {
            Game.isGameOver = true;
        }
    }
}
