package game.entities;

import resources.sprites.SpritePath;

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


    public NyanCat(double xPos, double yPos, double xDir, double yDir, boolean isMovingRight) {
        super(xPos, yPos, 115, 45, isMovingRight);
        importRightSpriteImages();
        importLeftSpriteImages();
        this.xDir = xDir;
        this.yDir = yDir;
    }

    @Override
    public void renderProjectile(Graphics g) {
        //super.render(g);
        updateAnimationTick();
        if (isMovingRight) g.drawImage(rightSpriteImages[indexToDraw], (int) xPos, (int) yPos, null);
        else g.drawImage(leftSpriteImages[indexToDraw], (int) xPos, (int) yPos, null);
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

