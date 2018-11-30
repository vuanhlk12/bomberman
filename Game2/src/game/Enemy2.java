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
public class Enemy2 extends GameObject {

    Random r = new Random();
    int tempr = 1;
    long currentTime;
    int buocdi = 0;
    int time = 0, oldtime = 0, oldtime2 = 0;
    long lastTime = System.nanoTime();
    boolean chamPlayer = false;
    //int time = 0;

    public Enemy2(int x, int y, Handler handler, ID id) {
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
                if (Direction() != 4) {
                    switch (Direction()) {
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
                                tempr = r.nextInt(4);
                            }
                            break;
                        case 1:
                            if (this.canMoveL()) {
                                x -= step;
                            } else {
                                tempr = r.nextInt(4);
                            }
                            break;
                        case 2:
                            if (this.canMoveU()) {
                                y -= step;
                            } else {
                                tempr = r.nextInt(4);
                            }
                            break;
                        case 3:
                            if (this.canMoveD()) {
                                y += step;
                            } else {
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
                        this.step = 5;
                        break;
                    case 1:
                        this.step = 10;
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
                if (this.getY() == temp.getY()) {
                    if (this.getX() < temp.getX()) {
                        return 0;
                    }
                    if (this.getX() > temp.getX()) {
                        return 1;
                    }
                }
                if (this.getX() == temp.getX()) {
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
