package game.utility;

public abstract class HelperMethods {

    public static double changeSpeedBy(double speed, double amount) {
        if (speed > 0) return speed + amount * 1;
        else return speed + amount * -1;
    }

    public static int randomNumber(double min, double max) {
        return (int) Math.floor((Math.random() * (max + 1 - min)) + min);
    }

    public static double getAbsolutSpeed(double xDir, double yDir) {
        return Math.sqrt(Math.abs(xDir) * Math.abs(xDir) + Math.abs(yDir) * Math.abs(yDir));
    }




}

