package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;
import main.GamePanel;



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
            case KeyEvent.VK_W -> gamePanel.getGame().getPlayer1().setKeyUp(true);
            case KeyEvent.VK_S -> gamePanel.getGame().getPlayer1().setKeyDown(true);
            case KeyEvent.VK_UP -> gamePanel.getGame().getPlayer2().setKeyUp(true);
            case KeyEvent.VK_DOWN -> gamePanel.getGame().getPlayer2().setKeyDown(true);
            case KeyEvent.VK_ENTER -> gamePanel.setEnterKeyPressed(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> gamePanel.getGame().getPlayer1().setKeyUp(false);
            case KeyEvent.VK_S -> gamePanel.getGame().getPlayer1().setKeyDown(false);
            case KeyEvent.VK_UP -> gamePanel.getGame().getPlayer2().setKeyUp(false);
            case KeyEvent.VK_DOWN -> gamePanel.getGame().getPlayer2().setKeyDown(false);
        }
    }
}
