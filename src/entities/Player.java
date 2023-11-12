package entities;

import main.GameWindow;


public class Player extends Entity {
    private final String PLAYER_NAME;
    private boolean keyUp = false;
    private boolean keyDown = false;


    public Player(int xPos, String name) {
        super(xPos, (GameWindow.HEIGHT / 2) - 25, 20, 100);
        this.PLAYER_NAME = name;
        this.yDir = 2;
    }

    public void movePlayer(int negative) {
        this.yPos += negative * yDir;
    }


    public void updatePosition() {
        if (keyDown && yPos + height < GameWindow.HEIGHT - 50) {
            movePlayer(1);
        }
        if (keyUp && yPos > 10) {
            movePlayer(-1);
        }
    }

    public void setKeyUp(boolean keyUp) {
        this.keyUp = keyUp;
    }

    public void setKeyDown(boolean keyDown) {
        this.keyDown = keyDown;
    }
}
