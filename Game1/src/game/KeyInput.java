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
                    temp.setY(temp.getY() + temp.step);
                }
                if (key == KeyEvent.VK_UP && temp.canMoveU()) {
                    temp.setY(temp.getY() - temp.step);
                }
                if (key == KeyEvent.VK_LEFT && temp.canMoveL()) {
                    temp.setX(temp.getX() - temp.step);
                }
                if (key == KeyEvent.VK_RIGHT && temp.canMoveR()) {
                    temp.setX(temp.getX() + temp.step);
                }
                if (key == KeyEvent.VK_SPACE) {
                    for (int k = 0; k < 5; k++) {
                        handler.addObject(new bom((temp.getX() + 20) / 40 * 40, (temp.getY() + 20) / 40 * 40, handler, ID.Bom));
                    }
                    //handler.print();
                }
            }

        }
    }

    public synchronized void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }

}
