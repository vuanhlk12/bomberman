/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author vuanhlk12
 */
public class Enemy extends GameObject {

    Random r = new Random();
    int tempr = 1;
    long currentTime;
    int buocdi = 0;
    int time = 0, oldtime = 0;
    long lastTime = System.nanoTime();
    //int time = 0;

    public Enemy(int x, int y, Handler handler, ID id) {
        super(x, y, handler, id);
        this.step = 5;

    }

    public void tick() {
        currentTime = System.nanoTime();

        if (currentTime - lastTime >= 100000000) {
            if (x % 40 == 0 && y % 40 == 0) {
                r = new Random();
                tempr = r.nextInt(4);
            }
            lastTime = currentTime;

        }

        if (currentTime - lastTime >= 10000000) {
            time++;
            if (time - oldtime >= 3) {
                oldtime = time;
                switch (tempr) {
                    case 0:
                        if (this.canMoveR()) {
                            this.x += this.step;
                        }
                        break;
                    case 1:
                        if (this.canMoveL()) {
                            x -= step;
                        }
                        break;
                    case 2:
                        if (this.canMoveU()) {
                            y -= step;
                        }
                        break;
                    case 3:
                        if (this.canMoveD()) {
                            y += step;
                        }
                        break;
                    default:
                        break;

                }
            }

        }

    }

    @Override
    public void render(Graphics g) {
        if (this.id == ID.Enemy) {
            Image img = null;
            img = new ImageIcon("img\\enemy.png").getImage();
            g.drawImage(img, x, y, 40, 40, null);

        }
    }

}
