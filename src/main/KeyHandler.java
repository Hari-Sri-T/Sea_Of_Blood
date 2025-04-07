package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean up_pressed,down_pressed,left_pressed,right_pressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            up_pressed = true;
        }
        if(code == KeyEvent.VK_S){
            down_pressed = true;
        }
        if(code == KeyEvent.VK_A){
            left_pressed = true;
        }
        if(code == KeyEvent.VK_D){
            right_pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            up_pressed = false;
        }
        if(code == KeyEvent.VK_S){
            down_pressed = false;
        }
        if(code == KeyEvent.VK_A){
            left_pressed = false;
        }
        if(code == KeyEvent.VK_D){
            right_pressed = false;
        }

    }
}
