package main;

import display.GameOverScreen;
import inputs.KeyboardInputs;
import music.C4;
import music.Music;
import sprites.SpritePath;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    private int intervalBetweenSpeedIncrease = 5000;
    private int level = 1;
    protected boolean isGameOver = false;
    private int whoWon = 0;

    Game game;
    Music nyanMusic = new Music("resources/sounds/Nyan cat theme song (sped up).wav");
    ;

    public GamePanel(Game game) {
        this.game = game;
        importRightSpriteImages();
        importLeftSpriteImages();
        addKeyListener(new KeyboardInputs(this));
    }

    public void startSpeedTimer() {
        Thread speedIncreaseTimer = new Thread(this);
        speedIncreaseTimer.start();

    }


    public void updateGame() {
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isGameOver) {
            updateAnimationTick();
            displayGameOverScreen(g);
        } else {
           // updateAnimationTick();
           displayBoard(g);
           // displayElements(g);
            game.render(g);
        }
    }



    private void displayBoard(Graphics g) {
        g.setColor(Color.WHITE);
        int value = 10;
        while (value < GameWindow.HEIGHT) {
            g.fillRect(GameWindow.WIDTH / 2, value, 10, 50);
            value += 81;
        }
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Score : " + Game.score, 30, 30);
       // g.drawString("B speed : " + xDir, GameWindow.WIDTH - 140, 15);
        g.drawString("P speed : " + game.getPlayer1().getYDir(), GameWindow.WIDTH - 140, 35);


    }

    private void displayElements(Graphics g) {
       //  g.fillRect(player1X, (int) player1Y, playersWidth, player1Height);
       // g.fillRect(player2X, (int) player2Y, playersWidth, player2Height);
        if (!isNyanCat) {
        } else {
                //g.drawImage(endGameSpriteImages[indexToDraw], ballX, ballY, null);
        }
    }

    private void displayGameOverScreen(Graphics g) {
        GameOverScreen.Show(g);
    }
    public Game getGame() {
        return game;
    }
    public void setEnterKeyPressed(boolean enterKeyPressed) {
        this.enterKeyPressed = enterKeyPressed;
    }

    public Color getRandomColor() {
        int r = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        return new Color(r, g, b);
    }












    private final Image[] rightSpriteImages = new Image[5];
    private final Image[] leftSpriteImages = new Image[5];
    private final Image[] endGameSpriteImages = new Image[5];
    protected int indexToDraw, animationCounter = 0;
    protected int animationTimer = 12;

    protected boolean enterKeyPressed = false;
    protected boolean isNyanCat = false;


    private double player1Y = (GameWindow.HEIGHT / 2) - 50;
    private int player1X = 10;
    private int player2X = GameWindow.WIDTH - 45;
    private int playersWidth = 20;
    private int player1Height = 100;
    private int player2Height = 100;
    private double player2Y = (GameWindow.HEIGHT / 2) - 50;


    private int buffer = 10;
    private void updateAnimationTick() {
        animationCounter++;
        if (animationCounter >= animationTimer) {
            animationCounter = 0;
            indexToDraw++;
            if (indexToDraw == rightSpriteImages.length) {
                indexToDraw = 0;
            }
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(intervalBetweenSpeedIncrease);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // xDir += 0.3 * Math.signum(xDir);
            //yDir += 0.3 * Math.signum(yDir);
            level++;

            //game.getPlayer1().yDir += 0.3;
            //game.getPlayer2().yDir += 0.3;

            //Game.p1speed += 0.3;
            //Game.p2speed += 0.3;
            System.out.println("LEVEL INCREASE-------");
           // System.out.println(xDir + " " + yDir);
            System.out.println("player speed : " + game.getPlayer1().getYDir());
            System.out.println("Level : " + level);
            System.out.println();
            if (level == 11) {
                level = 0;
            }
        }
    }
    private void importRightSpriteImages() {
        String[] spritePaths = SpritePath.GOING_RIGHT_SPRITES;
        for (int i = 0; i < spritePaths.length; i++) {
            File file = new File(spritePaths[i]);
            BufferedImage image = null;

            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            rightSpriteImages[i] = image;
        }
    }

    private void importLeftSpriteImages() {
        String[] spritePaths = SpritePath.GOING_LEFT_SPRITES;
        for (int i = 0; i < spritePaths.length; i++) {
            File file = new File(spritePaths[i]);
            BufferedImage image;

            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            leftSpriteImages[i] = image;
        }
    }
}
