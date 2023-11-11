package Display;

import main.Game;
import main.GameWindow;
import sprites.SpritePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOverScreen {
    private static final Image[] endGameSpriteImages = new Image[5];
    protected static int indexToDraw;
    protected int animationCounter = 0;
    protected int animationTimer = 12;



    public static void Show(Graphics g){
        importEndGameSpriteImages();

        g.drawImage(endGameSpriteImages[indexToDraw], 450, 25, null);
        g.setColor(getRandomColor());
        g.fillRect(GameWindow.WIDTH / 4, GameWindow.HEIGHT / 4, GameWindow.WIDTH / 2, GameWindow.HEIGHT / 2);
        g.setColor(Color.BLACK);
        g.fillRect(GameWindow.WIDTH / 4 + 4, GameWindow.HEIGHT / 4 + 4, GameWindow.WIDTH / 2 - 8, GameWindow.HEIGHT / 2 - 8);

        //Draw final game score
        Font scoreFont = new Font("Arial", Font.BOLD, 30);
        g.setFont(scoreFont);
        g.setColor(Color.WHITE);
        g.drawString("Score : " + Game.score, 50, 30);

        //Draw final game winner
        Font winnerFont = new Font("Arial", Font.BOLD, 60);
        g.setFont(winnerFont);
        g.setColor(Color.WHITE);

        if (1 == 1) {
            g.drawString("Player 1 won!", GameWindow.WIDTH / 2 - 200, GameWindow.HEIGHT / 2);
        } else {
            g.drawString("Player 2 won!", 273, 210);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //repaint();
    }
    public static Color getRandomColor() {
        int r = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        return new Color(r, g, b);
    }
    private static void importEndGameSpriteImages() {
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
}
