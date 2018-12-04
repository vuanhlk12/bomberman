/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author vuanhlk12
 */
public class Enemy2 extends Enemy {

    int tg = 3;

    //int time = 0;
    public Enemy2(int x, int y, Handler handler, ID id) {
        super(x, y, handler, id);
        this.step = 5;

    }

    @Override
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
            if (time - oldtime >= tg) {

                oldtime = time;
                int n = Direction();
                if (n != 4) {
                    switch (n) {
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
                        case 3:
                            if (this.canMoveU()) {
                                y -= step;
                            }
                            break;
                        case 2:
                            if (this.canMoveD()) {
                                y += step;
                            }
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (tempr) {
                        case 0:
                            if (this.canMoveR()) {
                                this.x += this.step;
                            } else {
                                r = new Random();
                                tempr = r.nextInt(4);
                            }
                            break;
                        case 1:
                            if (this.canMoveL()) {
                                x -= step;
                            } else {
                                r = new Random();
                                tempr = r.nextInt(4);
                            }
                            break;
                        case 2:
                            if (this.canMoveU()) {
                                y -= step;
                            } else {
                                r = new Random();
                                tempr = r.nextInt(4);
                            }
                            break;
                        case 3:
                            if (this.canMoveD()) {
                                y += step;
                            } else {
                                r = new Random();
                                tempr = r.nextInt(4);
                            }
                            break;
                        default:
                            break;

                    }
                }
            }
            if (time - oldtime2 >= 100) {
                oldtime2 = time;
                chamPlayer = false;
                r = new Random();
                int tempp = r.nextInt(2);
                switch (tempp) {
                    case 0:
                        this.tg = 2;
                        break;
                    case 1:
                        this.tg = 3;
                        break;
                    default:
                        break;
                }
            }
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject temp = handler.object.get(i);
                if (temp.id == ID.Player) {
                    if (this.getRec(0, 0).intersects(temp.getRec(0, 0)) && chamPlayer == false) {
                        ((Player) temp).hp--;
                        chamPlayer = true;
                        System.out.println("Life remaining: " + ((Player) temp).hp);
                    }
                }
            }

        }

    }

    @Override
    public void render(Graphics g) {
        if (this.id == ID.Enemy2) {
            Image img = null;
            img = new ImageIcon("img\\enemy2.png").getImage();
            g.drawImage(img, x, y, 40, 40, null);

        }
    }

    public int Direction() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.id == ID.Player) {
                if (this.getY() - temp.getY() >= -10 && this.getY() - temp.getY() <= 0) {
                    if (this.getX() < temp.getX()) {
                        return 0;
                    }
                    if (this.getX() > temp.getX()) {
                        return 1;
                    }
                }
                if (this.getX() - temp.getX() >= -10 && this.getX() - temp.getX() <= 0) {
                    if (this.getY() < temp.getY()) {
                        return 2;
                    }
                    if (this.getY() > temp.getY()) {
                        return 3;
                    }
                }
            }
        }
        return 4;
    }
}
