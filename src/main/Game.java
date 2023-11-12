package main;

import entities.Cube;
import entities.Player;
import entities.Projectile;

import java.awt.*;

public class Game implements Runnable {
    private final GameWindow gameWindow;
    Player player1, player2;
    public static int score = 0;
    private final GamePanel gamePanel;
    private Thread gameLoop;
    private int FPS_SET = 140;
    private final int UPS_SET = 190;
    private int nyanCatSpawnTimer = 900;
    private int nyanCatSpawnCounter = 0;
    private int nyanCatTimer = 600;
    private int nyanCatCounter = 0;
    private int levelTimer = 0;
    private int levelCounter = 0;
    Projectile projectile;

    public Game() {
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        this.player1 = new Player(10, "Player 1");
        this.player2 = new Player(GameWindow.WIDTH - 45, "Player 2");
        this.projectile = new Cube(100, 100, 2, 2);
        startGame();
    }

    public void startGame() {
        gameLoop = new Thread(this);
        gameLoop.start();
        gamePanel.startSpeedTimer();

    }

    @Override
    public void run() {
        while (!gamePanel.enterKeyPressed) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        int frames = 0;
        int updates = 0;

        double deltaU = 0;
        double deltaF = 0;

        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        while (true) {

            long currentTime = System.nanoTime();


            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                if (!gamePanel.isGameOver) {
                    update();
                    //gamePanel.udpateProjectilePosition();
                    spawnNyanCat();
                    if (gamePanel.isNyanCat) {
                        //runNyanCatMode();
                    }
                    if (gamePanel.isGameOver) {
                 //       gamePanel.nyanMusic.kill();
                   //     gamePanel.nyanMusic = new Music("resources/sounds/Nyan cat theme song (sped up).wav");
                     //   gamePanel.nyanMusic.play();
                    }
                }
                updates++;
                deltaU--;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }


        }
    }

    public void render(Graphics g) {
        player1.render(g);
        player2.render(g);
        projectile.renderProjectile(g);
    }

    public void update() {
        player1.updatePosition();
        player2.updatePosition();
        projectile.updatePosition();
    }


    private void spawnNyanCat() {
        if (!gamePanel.isNyanCat) {
            nyanCatSpawnCounter++;
            if (nyanCatSpawnCounter >= nyanCatSpawnTimer) {
                gamePanel.isGameOver = true;
                gamePanel.isNyanCat = true;
                nyanCatSpawnCounter = 0;
            }
        }
    }

    private void runNyanCatMode() {
        if (nyanCatCounter == 0) {
          //  gamePanel.nyanMusic.play();
            setNyanCatDefenitions();
            System.out.println("--- NYAN ON ---");
            System.out.println();

        }
        nyanCatCounter++;
        if (nyanCatCounter >= nyanCatTimer) {
            gamePanel.isNyanCat = false;
          //  gamePanel.nyanMusic.stop();
            nyanCatCounter = 0;
            setCubeDefenitions();
            System.out.println("--- NYAN OFF ------");
            System.out.println();

        }
    }

    private void setNyanCatDefenitions() {
        gamePanel.ballWidth = 115;
        gamePanel.ballHeight = 45;

        gamePanel.xDir += 1.8 * Math.signum(gamePanel.xDir);
        gamePanel.yDir += 1.8 * Math.signum(gamePanel.yDir);

        player1.yDir += 1.3;
        player2.yDir += 1.3;
        gamePanel.animationTimer--;
    }

    private void setCubeDefenitions() {
        gamePanel.ballWidth = 20;
        gamePanel.ballHeight = 20;

        gamePanel.xDir -= 1.8 * Math.signum(gamePanel.xDir);
        gamePanel.yDir -= 1.8 * Math.signum(gamePanel.yDir);

        player1.yDir -= 1.3;
        player2.yDir -= 1.3;
    }


    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Projectile getProjectile() {
        return projectile;
    }
}
