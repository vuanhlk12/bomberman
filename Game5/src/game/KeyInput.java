/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author vuanhlk12
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public synchronized void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_DOWN && temp.canMoveD()) {
                    ((Player) temp).move = 1;
                }
                if (key == KeyEvent.VK_UP && temp.canMoveU()) {
                    ((Player) temp).move = 2;
                }
                if (key == KeyEvent.VK_RIGHT && temp.canMoveR()) {
                    ((Player) temp).move = 3;
                }
                if (key == KeyEvent.VK_LEFT && temp.canMoveL()) {
                    ((Player) temp).move = 4;
                }

                if (key == KeyEvent.VK_SPACE) {
                    for (int k = 0; k < 2; k++) {
                        if (((Player) temp).bomnumber > 0) {
                            handler.addObject(new bom((temp.getX() + 20) / 40 * 40, (temp.getY() + 20) / 40 * 40, handler, ID.Bom, ((Player) temp).bomsize));
                            ((Player) temp).bomnumber--;
                        }

                    }
                }
            }

        }
    }

    public synchronized void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getId() == ID.Player) {
                ((Player) temp).move = 0;
            }

        }
    }

}
