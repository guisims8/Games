package game.entities;

import game.controller.Game;
import game.display.GameWindow;
import game.utility.HelperMethods;

import java.awt.*;

public class MisteryBox extends Entity {
    public Color color = Color.MAGENTA;
    Game game;
    PowerUp powerUp;
    public boolean isActive = false;

    public MisteryBox(Game game) {
        super(HelperMethods.randomNumber(100, GameWindow.WIDTH - 100), HelperMethods.randomNumber(100, GameWindow.HEIGHT - 100), 25, 25);
        this.game = game;
        this.powerUp = randomPowerUp();
        System.out.println("new box");

    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) xPos, (int) yPos, (int) width, (int) height);
    }

    public PowerUp randomPowerUp() {
        return PowerUp.BAR;
    }

    public void doAction() {
        switch (powerUp) {
            case BAR -> changeBarSize();
            case CONTROLS -> invertControls();
        }
    }

    public void reverseAction() {
        switch (powerUp) {
            case BAR -> normalBarSize();
            case CONTROLS -> normalControls();
        }
    }

    public void changeBarSize() {
        if (game.getProjectile().isMovingRight) {
            game.getPlayer1().height += 50;
            game.getPlayer2().height -= 50;
        } else {
            game.getPlayer1().height -= 50;
            game.getPlayer2().height += 50;
        }
    }

    public void normalBarSize() {
        game.getPlayer1().height = 100;
        game.getPlayer2().height = 100;
    }

    public void invertControls() {
        if (game.getProjectile().isMovingRight) {
            game.getPlayer2().invertedControls = true;
        } else {
            game.getPlayer1().invertedControls = false;
        }
    }

    public void normalControls() {
        game.getPlayer2().invertedControls = false;
        game.getPlayer1().invertedControls = false;
    }
}
