package game.controller;

import game.inputs.KeyboardInputs;
import resources.music.C4;
import resources.music.Music;
import resources.sprites.SpritePath;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class GamePanel extends JPanel implements Runnable {
    private int ballX = 100, ballY = 100;
    protected double xDir = 5.5, yDir = 5.5;
    protected int ballWidth = 20;
    protected int ballHeight = 20;
    private boolean movingRight = true;
    protected boolean enterKeyPressed = false;
    protected boolean isNyanCat = false;
    private double player1Y = (GameWindow.HEIGHT / 2) - 25;
    private int player1X = 10;
    private int player2X = GameWindow.WIDTH - 30;

    private double player2Y = (GameWindow.HEIGHT / 2) - 25;
    private int buffer = 10;
    private int intervalBetweenSpeedIncrease = 5000;
    private int level = 1;
    protected boolean isGameOver = false;
    private int whoWon = 0;
    private int player1Height = 80;
    private int player2Height = 80;
    private final Image[] rightSpriteImages = new Image[5];
    private final Image[] leftSpriteImages = new Image[5];
    private final Image[] endGameSpriteImages = new Image[5];
    protected int indexToDraw, animationCounter = 0;
    protected int animationTimer = 12;
    Music pong;
    Music nyanMusic = new Music("resources/sounds/Nyan cat theme song (sped up).wav");
    ;
    private String[] pongSounds;
    private Color ballColor = Color.WHITE;

    public GamePanel() {
        importRightSpriteImages();
        importLeftSpriteImages();
        importEndGameSpriteImages();
        addKeyListener(new KeyboardInputs(this));
    }

    public void startSpeedTimer() {
        Thread speedIncreaseTimer = new Thread(this);
        pongSounds = C4.NOTES;
        //pong = new Music(pongSounds[0]);
        pong = new Music(pongSounds[level]);
        pong.play();
        pong.kill();
        speedIncreaseTimer.start();

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

    private void importEndGameSpriteImages() {
        String[] spritePaths = SpritePath.END_GAME_SPRITES;
        for (int i = 0; i < spritePaths.length; i++) {
            File file = new File(spritePaths[i]);
            BufferedImage image = null;

            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            endGameSpriteImages[i] = image;
        }
    }

    public void updateAnimationTick() {
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
            if (xDir < 0) {
                xDir -= 0.3;
            } else {
                xDir += 0.3;
            }
            if (yDir < 0) {
                yDir -= 0.3;
            } else {
                yDir += 0.3;
            }
            level++;

            Game.p1speed += 0.2;
            Game.p2speed += 0.2;
            System.out.println("LEVEL INCREASE-------");
            System.out.println(xDir + " " + yDir);
            System.out.println("player speed : " + Game.p1speed);
            System.out.println("Level : " + level);
            System.out.println();
            if (level == 11) {
                level = 0;
            }
        }
    }


    public void moveP1(double value) {
        this.player1Y += value;
    }

    public void moveP2(double value) {
        this.player2Y += value;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isGameOver) {
            updateAnimationTick();
            displayGameOverScreen(g);
        } else {
            updateAnimationTick();
            displayBoard(g);
            displayElements(g);
        }
    }

    public void displayBoard(Graphics g) {
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
    }

    public void displayElements(Graphics g) {
        g.fillRect(player1X, (int) player1Y, 10, player1Height);
        g.fillRect(player2X, (int) player2Y, 10, player2Height);
        if (!isNyanCat) {
            g.setColor(ballColor);
            g.fillRect(ballX, ballY, ballWidth, ballHeight);
        } else {
            if (movingRight)
                //g.drawImage(endGameSpriteImages[indexToDraw], ballX, ballY, null);
                g.drawImage(rightSpriteImages[indexToDraw], ballX, ballY, null);
            else
                g.drawImage(leftSpriteImages[indexToDraw], ballX, ballY, null);
        }
    }

    public void displayGameOverScreen(Graphics g) {
        //Draw container
        g.drawImage(rightSpriteImages[indexToDraw], 140, 20, null);
        g.drawImage(rightSpriteImages[indexToDraw], 39, 250, null);
        g.drawImage(rightSpriteImages[indexToDraw], 650, 180, null);

        g.drawImage(endGameSpriteImages[indexToDraw], 450, 25, null);
        g.drawImage(endGameSpriteImages[indexToDraw], 100, 350, null);

        g.drawImage(leftSpriteImages[indexToDraw], 550, 519, null);
        g.drawImage(leftSpriteImages[indexToDraw], 650, 519, null);

        g.setColor(getRandomColor());
        g.fillRect(GameWindow.WIDTH / 4, GameWindow.HEIGHT / 4, GameWindow.WIDTH/2, GameWindow.HEIGHT/2);
        g.setColor(Color.BLACK);
        g.fillRect(GameWindow.WIDTH / 4 + 4, GameWindow.HEIGHT / 4 + 4, GameWindow.WIDTH/2-8, GameWindow.HEIGHT/2-8);

        //Draw final game score
        Font scoreFont = new Font("Arial", Font.BOLD, 30);
        g.setFont(scoreFont);
        g.setColor(Color.WHITE);
        g.drawString("Score : " + Game.score, 50, 30);

        //Draw final game winner
        Font winnerFont = new Font("Arial", Font.BOLD, 60);
        g.setFont(winnerFont);
        g.setColor(Color.WHITE);

        if (whoWon == 1) {
            g.drawString("Player 1 won!", GameWindow.WIDTH/2-200, GameWindow.HEIGHT/2 );
        } else {
            g.drawString("Player 2 won!", 273, 210);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        repaint();
    }

    public void udpateProjectilePosition() {
        ballY += yDir;
        if (ballY + ballHeight + 40 > GameWindow.HEIGHT || ballY < 0) {
            yDir *= -1;
            pong = new Music(pongSounds[level]);
            pong.play();
            pong.kill();
            ballColor = getRandomColor();
        }
        detectPlayerTouch();
        if (ballX + ballWidth > GameWindow.WIDTH - 15) {
            whoWon = 1;
            isGameOver = true;
            animationTimer = 1;
        }
        if (ballX < 14) {
            whoWon = 2;
            isGameOver = true;
            animationTimer = 1;
        }
    }

    public void detectPlayerTouch() {
        ballX += xDir;
        if (ballX < player1X + 10 + buffer && ballY + ballHeight > player1Y - buffer && ballY < player1Y + player1Height + buffer) {
            if (!movingRight) {
                xDir *= -1;
                Game.score++;
                ballColor = getRandomColor();
                movingRight = true;
                pong = new Music(pongSounds[level]);
                pong.play();
                pong.kill();
            }
        }
        if (ballX + ballWidth > player2X - buffer && ballY + ballHeight > player2Y - buffer && ballY < player2Y + player2Height + buffer) {
            if (movingRight) {
                xDir *= -1;
                Game.score++;
                ballColor = getRandomColor();
                movingRight = false;
                pong = new Music(pongSounds[level]);
                pong.play();
                pong.kill();
            }
        }
    }


    public Color getRandomColor() {
        int r = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        return new Color(r, g, b);
    }

    public double getPlayer1Y() {
        return player1Y;
    }

    public double getPlayer2Y() {
        return player2Y;
    }

    public int getPlayer1Height() {
        return player1Height;
    }

    public int getPlayer2Height() {
        return player2Height;
    }

    public boolean isEnterKeyPressed() {
        return enterKeyPressed;
    }

    public void setEnterKeyPressed(boolean enterKeyPressed) {
        this.enterKeyPressed = enterKeyPressed;
    }
}
