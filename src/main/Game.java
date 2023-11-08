package main;

import music.Music;

import java.awt.*;

public class Game implements Runnable {
    private final GameWindow gameWindow;
    public static int score = 0;
    private final GamePanel gamePanel;
    private Thread gameLoop;
    private int FPS_SET = 120;
    protected static double p1speed = 2.5;
    protected static double p2speed = 2.5;
    private int nyanCatSpawnTimer = 800;
    private int nyanCatSpawnCounter = 0;
    private int nyanCatTimer = 400;
    private int nyanCatCounter = 0;
    private int levelTimer = 0;
    private int levelCounter = 0;
    public static boolean leftPlayerKeyUp = false;
    public static boolean rightPlayerKeyUp = false;
    public static boolean leftPlayerKeyDown = false;
    public static boolean rightPlayerKeyDown = false;


    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGame();
    }

    public void startGame() {
        gameLoop = new Thread(this);
        gameLoop.start();
        gamePanel.startSpeedTimer();

    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now;


        while (true) {
            while (!gamePanel.enterKeyPressed) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                lastFrame = now;
                if (!gamePanel.isGameOver) {
                    movePlayers();
                    gamePanel.udpateProjectilePosition();
                    spawnNyanCat();
                    if (gamePanel.isNyanCat) {
                        runNyanCatMode();
                    }
                    if (gamePanel.isGameOver) {
                        gamePanel.nyanMusic.kill();
                        gamePanel.nyanMusic = new Music("resources/sounds/Nyan cat theme song (sped up).wav");
                        gamePanel.nyanMusic.play();
                    }
                }
                gamePanel.repaint();
            }


        }
    }

    private void render(Graphics g) {

    }

    private void spawnNyanCat() {
        if (!gamePanel.isNyanCat) {
            nyanCatSpawnCounter++;
            if (nyanCatSpawnCounter >= nyanCatSpawnTimer) {
                gamePanel.isNyanCat = true;
                nyanCatSpawnCounter = 0;
            }
        }
    }

    private void runNyanCatMode() {
        if (nyanCatCounter == 0) {
            gamePanel.nyanMusic.play();
            setNyanCatDefenitions();
            System.out.println("--- NYAN ON ---");
            System.out.println();

        }
        nyanCatCounter++;
        if (nyanCatCounter >= nyanCatTimer) {
            gamePanel.isNyanCat = false;
            gamePanel.nyanMusic.stop();
            nyanCatCounter = 0;
            setCubeDefenitions();
            System.out.println("--- NYAN OFF ------");
            System.out.println();

        }
    }

    private void setNyanCatDefenitions() {
        gamePanel.ballWidth = 115;
        gamePanel.ballHeight = 45;
        if (gamePanel.xDir < 0) {
            gamePanel.xDir -= 1.8;
        } else {
            gamePanel.xDir += 1.8;
        }
        if (gamePanel.yDir < 0) {
            gamePanel.yDir -= 1.8;
        } else {
            gamePanel.yDir += 1.8;
        }
        p1speed += 1.3;
        p2speed += 1.3;
        gamePanel.animationTimer--;
    }

    private void setCubeDefenitions() {
        gamePanel.ballWidth = 20;
        gamePanel.ballHeight = 20;
        if (gamePanel.xDir < 0) {
            gamePanel.xDir += 1.8;
        } else {
            gamePanel.xDir -= 1.8;
        }
        if (gamePanel.yDir < 0) {
            gamePanel.yDir += 1.8;
        } else {
            gamePanel.yDir -= 1.8;
        }
        p1speed -= 1.3;
        p2speed -= 1.3;
    }

    private void nyanCatTimer() {


    }

    public void movePlayers() {
        if (leftPlayerKeyDown && gamePanel.getPlayer1Y() + gamePanel.getPlayer1Height() < GameWindow.HEIGHT-50) {
            gamePanel.moveP1(p1speed);
        }
        if (leftPlayerKeyUp && gamePanel.getPlayer1Y() > 10) {
            gamePanel.moveP1(-p1speed);
        }
        if (rightPlayerKeyDown && gamePanel.getPlayer2Y() + gamePanel.getPlayer2Height() < GameWindow.HEIGHT-50) {
            gamePanel.moveP2(p2speed);
        }
        if (rightPlayerKeyUp && gamePanel.getPlayer2Y() > 10) {
            gamePanel.moveP2(-p2speed);
        }
    }

    public static boolean isLeftPlayerKeyUp() {
        return leftPlayerKeyUp;
    }

    public static void setLeftPlayerKeyUp(boolean leftPlayerKeyUp) {
        Game.leftPlayerKeyUp = leftPlayerKeyUp;
    }

    public static boolean isRightPlayerKeyUp() {
        return rightPlayerKeyUp;
    }

    public static void setRightPlayerKeyUp(boolean rightPlayerKeyUp) {
        Game.rightPlayerKeyUp = rightPlayerKeyUp;
    }

    public static boolean isLeftPlayerKeyDown() {
        return leftPlayerKeyDown;
    }

    public static void setLeftPlayerKeyDown(boolean leftPlayerKeyDown) {
        Game.leftPlayerKeyDown = leftPlayerKeyDown;
    }

    public static boolean isRightPlayerKeyDown() {
        return rightPlayerKeyDown;
    }

    public static void setRightPlayerKeyDown(boolean rightPlayerKeyDown) {
        Game.rightPlayerKeyDown = rightPlayerKeyDown;
    }
}
