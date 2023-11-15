package entities;

import main.GameWindow;


public class Player extends Entity {
    private final int PLAYER_NUMBER;
    protected int buffer = 10;
    private boolean keyUp = false;
    private boolean keyDown = false;
    public static boolean keyEnter = false;


    public Player(int xPos, int playerNum) {
        super(xPos, (GameWindow.HEIGHT / 2) - 25, 20, 100);
        this.PLAYER_NUMBER = playerNum;
        this.yDir = 2;
    }

    public void movePlayer(int negative) {
        this.yPos += negative * yDir;
    }

    public void update() {
        updatePlayer();
    }

    public void updatePlayer() {
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

    public static void setKeyEnter(boolean keyEnter) {
        Player.keyEnter = keyEnter;
    }
}
