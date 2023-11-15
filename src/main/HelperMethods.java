package main;

public abstract class HelperMethods {

    public static double increaseSpeedBy(double speed, double amount) {
        if (speed > 0) return speed + amount * 1;
        else return speed + amount * -1;
    }

    public static double randomNumber(double min, double max) {
        return Math.floor((Math.random() * (max-min)) + min);
    }


}
