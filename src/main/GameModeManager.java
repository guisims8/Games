package main;

import entities.Cube;
import entities.NyanCat;
import entities.Projectile;
import music.Music;

public class GameModeManager {
    Game game;
    Music nyanMusic = new Music("resources/sounds/Nyan cat theme song (sped up).wav");
    private int nyanCatSpawnTimer = 200;
    private int nyanCatSpawnCounter = 0;
    private int nyanCatTimer = 100;
    private int nyanCatCounter = 0;

    public GameModeManager(Game game) {
        this.game = game;
    }

    protected void spawnNyanCat(Projectile projectile) {
            nyanCatSpawnCounter++;
            if (nyanCatSpawnCounter >= nyanCatSpawnTimer) {
                runNyanCatMode(projectile);
            }
    }

    private void runNyanCatMode(Projectile projectile) {
        if (nyanCatCounter == 0) {
            setNyanCatDefenitions(projectile);
        }
        nyanCatCounter++;
        if (nyanCatCounter >= nyanCatTimer) {
            nyanCatCounter = 0;
            nyanCatSpawnCounter = 0;
            setCubeDefenitions(projectile);
        }
    }

    private void setCubeDefenitions(Projectile projectile) {
        double x = projectile.xPos;
        double y = projectile.yPos;
        double xDir = HelperMethods.increaseSpeedBy(projectile.xDir,-1);
        double yDir = HelperMethods.increaseSpeedBy(projectile.yDir,-1);
        boolean isMovingRight = projectile.isMovingRight;

        game.projectile = new Cube(x, y, xDir, yDir, isMovingRight);
        nyanMusic.stop();
    }

    private void setNyanCatDefenitions(Projectile projectile) {
        double x = projectile.xPos;
        double y = projectile.yPos;
        double xDir = HelperMethods.increaseSpeedBy(projectile.xDir,1.5);
        double yDir = HelperMethods.increaseSpeedBy(projectile.yDir,1.5);
        boolean isMovingRight = projectile.isMovingRight;

        game.projectile = new NyanCat(x, y, xDir, yDir, isMovingRight);
        nyanMusic.play();
    }

}
