package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // returns number of key pressed

        if (code == KeyEvent.VK_W) // if W is pressed
        {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) // if S is pressed
        {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) // if A is pressed
        {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) // if D is pressed
        {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) // if W is pressed
        {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) // if S is pressed
        {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) // if A is pressed
        {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) // if D is pressed
        {
            rightPressed = false;
        }
    }
}
