package game.controller;

import game.entities.MisteryBox;
import game.utility.Timer;

public class MisteryBoxManager {
    private MisteryBox misteryBox;
    private Timer spawnTimer, actionTimer;
    private Timer testTimer;
    Game game;

    public MisteryBoxManager(Game game) {
        this.game = game;
    }

    public void activateMboxEffect() {
        if (!misteryBox.isActive) misteryBox.doAction();
        misteryBox.isActive = true;
        misteryBoxTimer();
        game.misteryBox = null;
    }


    public void misteryBoxTimer() {
        if (actionTimer == null) actionTimer = new Timer(500);
        if (actionTimer.isTimerOver()) {
            misteryBox.reverseAction();
            misteryBox = null;
            actionTimer = null;
            spawnTimer = null;
            testTimer = null;
        }
    }


    public void doYourThing() {
        if (misteryBox == null) {
            spanwMisteryBox();
        } else {
            detectProjectileTouch();
        }

    }

    public void spanwMisteryBox() {
        if (spawnTimer == null) spawnTimer = new Timer(500);
        if (spawnTimer.isTimerOver()) misteryBox = game.spawnMisteryBox();
    }

    public void detectProjectileTouch() {
        if (testTimer == null) testTimer = new Timer(500);
        if (testTimer.isTimerOver()) activateMboxEffect();
    }
}
