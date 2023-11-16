package game.display;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame jFrame;
    public static final int WIDTH = 900, HEIGHT = 600;

    public GameWindow(GamePanel gamePanel) {
        this.jFrame = new JFrame();
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        gamePanel.setBackground(Color.BLACK);
        jFrame.add(gamePanel);
    }

    public void gameOver(JLabel gameoverLabel) {
        jFrame.add(gameoverLabel);
    }
}
