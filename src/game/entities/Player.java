package game.entities;

import game.display.GameWindow;

import java.awt.*;


public class Player extends Entity {
    private String PLAYER_NAME;
    protected int buffer = 0;
    private boolean keyUp = false;
    private boolean keyDown = false;
    protected boolean invertedControls = false;
    public static boolean keyEnter = false;


    public Player(int xPos, String playerName) {
        super(xPos, (GameWindow.HEIGHT / 2) - 25, 20, 100);
        this.PLAYER_NAME = playerName;
        this.yDir = 2;
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.GRAY);
        g.fillRoundRect((int) xPos-1, (int) yPos-2, (int) width+2, (int) height / 4,(int) (width+2)/3, (int)( height / 4)/3);
        g.fillRoundRect((int) xPos-1, (int) yPos+2 + (int) height / 4 * 3 + 1, (int) width+2, (int) height / 4, (int) (width+2)/3, (int) (height / 4)/3);
    }

    public void movePlayer(int negative) {
        this.yPos += negative * yDir;
    }

    public void update() {
        updatePlayer();
    }

    public void updatePlayer() {
        if (!invertedControls) {
            if (keyDown && yPos + height < GameWindow.HEIGHT - 50) {
                movePlayer(1);
            }
            if (keyUp && yPos > 10) {
                movePlayer(-1);
            }
        } else {
            if (keyUp && yPos + height < GameWindow.HEIGHT - 50) {
                movePlayer(1);
            }
            if (keyDown && yPos > 10) {
                movePlayer(-1);
            }
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
