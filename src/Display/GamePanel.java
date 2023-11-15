package display;

import display.BoardScreen;
import display.GameOverScreen;
import inputs.KeyboardInputs;
import main.Game;
import music.Music;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    Game game;

    public GamePanel(Game game) {
        this.game = game;
        addKeyListener(new KeyboardInputs(this));
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (game.isGameOver) {
            displayGameOverScreen(g);
        } else {
            displayBoard(g);
            game.render(g);
        }
    }

    private void displayBoard(Graphics g) {
        BoardScreen.Show(g, game);
    }
    private void displayGameOverScreen(Graphics g) {
        GameOverScreen.Show(g);
    }
    public Game getGame() {
        return game;
    }

}
