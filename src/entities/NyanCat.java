package entities;

import main.GameWindow;
import sprites.SpritePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NyanCat extends Projectile {
    private final Image[] leftSpriteImages = new Image[5];
    private final Image[] rightSpriteImages = new Image[5];
    protected int indexToDraw, animationCounter = 0;
    protected int animationTimer = 12;
    private int nyanCatSpawnTimer = 900;
    private int nyanCatSpawnCounter = 0;
    private int nyanCatTimer = 600;
    private int nyanCatCounter = 0;


    public NyanCat(int xPos, int yPos, int width,int height) {
        super(xPos, yPos, width, height);
        importRightSpriteImages();
        importLeftSpriteImages();
        this.xDir = 2;
        this.yDir = 2;
    }

    @Override
    public void renderProjectile(Graphics g) {
        super.render(g);
        //g.drawImage(rightSpriteImages[0], xPos, yPos, null);
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

