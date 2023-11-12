package main;

import display.BoardScreen;
import display.GameOverScreen;
import inputs.KeyboardInputs;
import sprites.SpritePath;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    Game game;



    public GamePanel(Game game) {
        this.game = game;
        importRightSpriteImages();
        importLeftSpriteImages();
        addKeyListener(new KeyboardInputs(this));
    }

    public void startSpeedTimer() {
        //Thread speedIncreaseTimer = new Thread(this);
        //pongSounds = C4.NOTES;
        //pong = new Music(pongSounds[0]);
        //pong = new Music(pongSounds[level]);
        //pong.play();
        //pong.kill();
        //speedIncreaseTimer.start();

    }


    public void updateGame() {
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isGameOver) {
            updateAnimationTick();
            displayGameOverScreen(g);
        } else {
            //updateAnimationTick();
            displayBoard(g);
            displayElements(g);
            game.render(g);
        }
    }



    private void displayBoard(Graphics g) {
        BoardScreen.Show(g,game);
    }

    private void displayElements(Graphics g) {
        // g.fillRect(player1X, (int) player1Y, playersWidth, player1Height);
        //g.fillRect(player2X, (int) player2Y, playersWidth, player2Height);
        if (!isNyanCat) {
           // g.setColor(ballColor);
            //g.fillRect(ballX, ballY, ballWidth, ballHeight);
        } else {
            if (movingRight)
                //g.drawImage(endGameSpriteImages[indexToDraw], ballX, ballY, null);
                g.drawImage(rightSpriteImages[indexToDraw], ballX, ballY, null);
            else
                g.drawImage(leftSpriteImages[indexToDraw], ballX, ballY, null);
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







    protected boolean enterKeyPressed = false;
    protected boolean isNyanCat = false;

    private int ballX = 100, ballY = 100;
    protected double xDir = 2, yDir = 2;
    protected int ballWidth = 25;
    protected int ballHeight = 25;
    private boolean movingRight = true;
    private Color ballColor = Color.WHITE;
    private final Image[] rightSpriteImages = new Image[5];
    private final Image[] leftSpriteImages = new Image[5];
    private final Image[] endGameSpriteImages = new Image[5];
    protected int indexToDraw, animationCounter = 0;
    protected int animationTimer = 12;

    public void udpateProjectilePosition() {
        ballY += yDir;
        if (ballY + ballHeight + 40 > GameWindow.HEIGHT || ballY < 0) {
            yDir *= -1;
       //     pong = new Music(pongSounds[level]);
         //   pong.play();
          //  pong.kill();
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
    public void changeDirection(boolean movingRight) {
        xDir *= -1;
        Game.score++;
        ballColor = getRandomColor();
        this.movingRight = movingRight;
    //    pong = new Music(pongSounds[level]);
     //   pong.play();
      //  pong.kill();
    }
    public void detectPlayerTouch() {
        ballX += xDir;
        if (ballX < player1X + 10 + buffer && ballY + ballHeight > player1Y - buffer && ballY < player1Y + player1Height + buffer) {
            if (!movingRight) {
                changeDirection(true);
            }
        }
        if (ballX + ballWidth > player2X - buffer && ballY + ballHeight > player2Y - buffer && ballY < player2Y + player2Height + buffer) {
            if (movingRight) {
                changeDirection(false);
            }
        }
    }
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

            game.getPlayer1().yDir += 0.3;
            game.getPlayer2().yDir += 0.3;

            //Game.p1speed += 0.3;
            //Game.p2speed += 0.3;
            System.out.println("LEVEL INCREASE-------");
            System.out.println(xDir + " " + yDir);
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
    private double player1Y = (GameWindow.HEIGHT / 2) - 50;
    private int player1X = 10;
    private int player2X = GameWindow.WIDTH - 45;
    private int playersWidth = 20;
    private int player1Height = 100;
    private int player2Height = 100;
    private double player2Y = (GameWindow.HEIGHT / 2) - 50;


    private int buffer = 10;
    private int intervalBetweenSpeedIncrease = 5000;
    private int level = 1;
    protected boolean isGameOver = false;
    private int whoWon = 0;
}
