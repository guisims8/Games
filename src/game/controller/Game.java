package game.controller;

import game.display.GamePanel;
import game.display.GameWindow;
import game.entities.Cube;
import game.entities.MisteryBox;
import game.entities.Player;
import game.entities.Projectile;

import java.awt.*;

public class Game implements Runnable {
    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private Thread gameLoop;
    private int FPS_SET = 140;
    private final int UPS_SET = 190;

    CubeNyanController gameModeManager;
    MisteryBoxManager misteryBoxManager;
    MisteryBox misteryBox;
    Player player1, player2;
    Projectile projectile;

    public static boolean isGameOver = false;
    private static int score = 0;


    public Game() {
        this.projectile = new Cube(100, 105, 1.5, 1.5, true);
        this.player1 = new Player(10, "UM");
        this.player2 = new Player(GameWindow.WIDTH - 45, "DOIS");
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        gameModeManager = new CubeNyanController(this);
        misteryBoxManager = new MisteryBoxManager(this);

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

            // Update Game
            if (deltaU >= 1) {
                if (!isGameOver) {
                    update();
                } else {

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

            // Update Screen
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
        if (misteryBox != null) misteryBox.render(g);
    }

    public void update() {

        player1.update();
        player2.update();
        projectile.updatePosition(player1, player2);
        gameModeManager.spawnNyanCat();

        misteryBoxManager.doYourThing();
    }


    public MisteryBox spawnMisteryBox() {
        return misteryBox = new MisteryBox(this);
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

    public static int getScore() {
        return score;
    }

    public static void increaseScore(int value) {
        Game.score += value;
    }
}
