package game.controller;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame jFrame;
    public static final int WIDTH = 1300, HEIGHT = 800;

    public GameWindow(GamePanel gamePanel) {
        this.jFrame = new JFrame();
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        gamePanel.setBackground(Color.BLACK);
        jFrame.add(gamePanel);
    }

    public void gameOver(JLabel gameoverLabel){
        jFrame.add(gameoverLabel);
    }
}
