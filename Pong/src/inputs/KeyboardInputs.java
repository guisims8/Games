package game.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.controller.Game;
import game.controller.GamePanel;



public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> Game.setLeftPlayerKeyUp(true);
            case KeyEvent.VK_S -> Game.setLeftPlayerKeyDown(true);
            case KeyEvent.VK_UP -> Game.setRightPlayerKeyUp(true);
            case KeyEvent.VK_DOWN -> Game.setRightPlayerKeyDown(true);
            case KeyEvent.VK_ENTER -> gamePanel.setEnterKeyPressed(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> Game.setLeftPlayerKeyUp(false);
            case KeyEvent.VK_S -> Game.setLeftPlayerKeyDown(false);
            case KeyEvent.VK_UP -> Game.setRightPlayerKeyUp(false);
            case KeyEvent.VK_DOWN -> Game.setRightPlayerKeyDown(false);
        }
    }
}
