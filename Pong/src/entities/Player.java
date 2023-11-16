package game.entities;

import game.controller.GameWindow;

public class Player extends Entity {
    private int speed = 7;

    public Player(int xPos, int height ,int width) {
        super(xPos, (GameWindow.HEIGHT / 2) - 25, height, width);
    }

    public void movePlayer(int value) {
        this.yPos += value;
    }
}
