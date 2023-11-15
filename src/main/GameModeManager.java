package main;

public class GameModeManager {

    private int nyanCatSpawnTimer = 900;
    private int nyanCatSpawnCounter = 0;
    private int nyanCatTimer = 600;
    private int nyanCatCounter = 0;
    private void setNyanCatDefenitions() {
        //  gamePanel.ballWidth = 115;
        // gamePanel.ballHeight = 45;

        // gamePanel.xDir += 1.8 * Math.signum(gamePanel.xDir);
        //gamePanel.yDir += 1.8 * Math.signum(gamePanel.yDir);

        //player1.yDir += 1.3;
        //player2.yDir += 1.3;
        //gamePanel.animationTimer--;
    }
    private void spawnNyanCat() {
        nyanCatSpawnCounter++;
        if (nyanCatSpawnCounter >= nyanCatSpawnTimer) {
            nyanCatSpawnCounter = 0;
        }
    }

    private void runNyanCatMode() {
        if (nyanCatCounter == 0) {
            setNyanCatDefenitions();
        }
        nyanCatCounter++;
        if (nyanCatCounter >= nyanCatTimer) {

            nyanCatCounter = 0;
            //setCubeDefenitions();
        }
    }



}
