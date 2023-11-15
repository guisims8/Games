package constants;

import java.awt.*;

public class Constants {


    private int intervalBetweenSpeedIncrease = 5000;
    private int level = 1;
    public void run() {
        while (true) {
            try {
                Thread.sleep(intervalBetweenSpeedIncrease);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // xDir += 0.3 * Math.signum(xDir);
            //yDir += 0.3 * Math.signum(yDir);
            level++;

            //game.getPlayer1().yDir += 0.3;
            //game.getPlayer2().yDir += 0.3;

            //Game.p1speed += 0.3;
            //Game.p2speed += 0.3;
            System.out.println("LEVEL INCREASE-------");
            // System.out.println(xDir + " " + yDir);
           // System.out.println("player speed : " + game.getPlayer1().getYDir());
            System.out.println("Level : " + level);
            System.out.println();
            if (level == 11) {
                level = 0;
            }
        }
    }

    public Color getRandomColor() {
        int r = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        return new Color(r, g, b);
    }


}
