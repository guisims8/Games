package game.controller;

import game.entities.Cube;
import game.entities.NyanCat;
import game.utility.HelperMethods;
import resources.music.Music;
import game.utility.Timer;

public class CubeNyanController {
    Game game;
    Music nyanMusic = new Music("resources/sounds/Nyan cat theme song (sped up).wav");
    Timer nyanCattimer, nyanCatDuration;

    public CubeNyanController(Game game) {
        this.game = game;
    }

    protected void spawnNyanCat() {
        if (nyanCattimer == null) {
            nyanCattimer = new Timer(900);
        } else {
            if (nyanCattimer.isTimerOver()) {
                runNyanCatMode();
            }
        }
    }

    private void runNyanCatMode() {
        if (nyanCatDuration == null) {
            setNyanCatDefenitions();
            nyanCatDuration = new Timer(600);
        } else {
            if (nyanCatDuration.isTimerOver()) {
                setCubeDefenitions();
                nyanCatDuration = null;
                nyanCattimer = null;
            }
        }
    }

    private void setCubeDefenitions() {
        double x = game.getProjectile().xPos;
        double y = game.getProjectile().yPos;
        double xDir = HelperMethods.changeSpeedBy(game.getProjectile().xDir, -0.5);
        double yDir = HelperMethods.changeSpeedBy(game.getProjectile().yDir, -0.5);
        boolean isMovingRight = game.getProjectile().isMovingRight;

        game.player1.yDir -= 0.3;
        game.player2.yDir -= 0.3;

        game.projectile = new Cube(x, y, xDir, yDir, isMovingRight);
        nyanMusic.stop();
    }

    private void setNyanCatDefenitions() {
        double x = game.getProjectile().xPos;
        double y = game.getProjectile().yPos;
        double xDir = HelperMethods.changeSpeedBy(game.getProjectile().xDir, 0.5);
        double yDir = HelperMethods.changeSpeedBy(game.getProjectile().yDir, 0.5);
        boolean isMovingRight = game.getProjectile().isMovingRight;

        game.player1.yDir += 0.8;
        game.player2.yDir += 0.8;

        game.projectile = new NyanCat(x, y, xDir, yDir, isMovingRight);
        nyanMusic.play();
    }

}
