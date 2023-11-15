package main;

import entities.Cube;
import entities.NyanCat;
import entities.Player;
import entities.Projectile;
import music.Music;

import java.awt.*;

public class Game implements Runnable {
    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private Thread gameLoop;
    private int FPS_SET = 140;
    private final int UPS_SET = 190;

    Player player1, player2;
    Projectile projectile;
    public boolean isGameOver = false;
    public static int score = 0;



    public Game() {
        this.projectile = new NyanCat(100, 100, 115, 50);
        this.player1 = new Player(10, 1);
        this.player2 = new Player(GameWindow.WIDTH - 45, 2);
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGame();
    }

    public void startGame() {
        gameLoop = new Thread(this);
        gameLoop.start();

    }

    @Override
    public void run() {
        while (!Player.keyEnter) {
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
                if (!isGameOver) {
                    update();
                } else {
                    gamePanel.nyanMusic.kill();
                    gamePanel.nyanMusic = new Music("resources/sounds/Nyan cat theme song (sped up).wav");
                    gamePanel.nyanMusic.play();
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
        player1.update();
        player2.update();
        projectile.updatePosition(player1, player2);
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
