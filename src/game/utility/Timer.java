package game.utility;

public class Timer {
    public int counter = 0;
    public int timer;


    public Timer(int timer) {
        this.timer = timer;
    }

    public boolean isTimerOver() {
        counter++;
        if (counter >= timer) {
            return true;
        } else return false;
    }
}
