package display;

import entities.Cube;
import main.Game;
import main.GameWindow;

import java.awt.*;

public class BoardScreen {

    public static void Show(Graphics g, Game game) {
        System.out.println("show");
        g.setColor(Color.WHITE);
        int value = 10;
        while (value < GameWindow.HEIGHT) {
            g.fillRect(GameWindow.WIDTH / 2, value, 10, 50);
            value += 81;
        }
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Score : " + Game.score, 30, 30);
        g.drawString("B speed : " + ((Cube)game.getProjectile()).xDir, GameWindow.WIDTH - 140, 15);
        g.drawString("P speed : " + game.getPlayer1().getYDir(), GameWindow.WIDTH - 140, 35);


    }

}
