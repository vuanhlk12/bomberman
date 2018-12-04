/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 *
 * @author vuanhlk12
 */
public class bom extends GameObject {

    long currentTime;
    long lastTime = System.nanoTime();
    int time = 0;
    int bomsize = 2;
    boolean chamPlayer = false;
    int[] range = new int[4];
    boolean checkbreak = false;
    boolean[] dano = new boolean[4];
    ArrayList<Integer> x1 = new ArrayList<Integer>();
    ArrayList<Integer> y1 = new ArrayList<Integer>();
    ArrayList<Rectangle> r1 = new ArrayList<Rectangle>();

    public bom(int x, int y, Handler handler, ID id, int bombsize) {
        super(x, y, handler, id);
        this.bomsize = bombsize;
        for (int i = 0; i < 4; i++) {
            range[i] = 0;
        }
        //tạo ra những hình chữ nhật xung quanh quả bom để xác định xem hình chữ nhật nào tương tác với các đối tượng khác (trên dưới phải trái)
        for (int i = 1; i <= bomsize; i++) {
            r1.add(this.getRec(i * (40 / step), 0));
            r1.add(this.getRec(-i * (40 / step), 0));
            r1.add(this.getRec(0, i * (40 / step)));
            r1.add(this.getRec(0, -i * (40 / step)));

        }
        
    }

    @Override
    public void tick() {
        currentTime = System.nanoTime();
        if (currentTime - lastTime >= 100000000) {
            time++;
            lastTime = currentTime;
        }
        breakWall();
        reBreakWall();

    }

    @Override
    public void render(Graphics g) {
        if (this.id == ID.Bom) {
            Image img = null;
            img = new ImageIcon("img\\bomb.png").getImage();
            g.drawImage(img, x, y, 40, 40, null);
            int kc = bomsize;
            if (time == 14) {// cái 14 kia chỉ để điều chỉnh tốc độ nổ bom thôi
                img = new ImageIcon("img\\fire3.png").getImage();
                g.drawImage(img, x, y, 40, 40, null);
                img = new ImageIcon("img\\fire2.png").getImage();
                if (dano[0] == true) {
                    kc = range[0];
                } else {
                    kc = bomsize;
                }
                for (int i = 1; i <= kc; i++) {
                    g.drawImage(img, x + 40 * i, y, 40, 40, null);
                }
                if (dano[1] == true) {
                    kc = range[1];
                } else {
                    kc = bomsize;
                }
                for (int i = 1; i <= kc; i++) {
                    g.drawImage(img, x - 40 * i, y, 40, 40, null);
                }
                img = new ImageIcon("img\\fire1.png").getImage();
                if (dano[2] == true) {
                    kc = range[2];
                } else {
                    kc = bomsize;
                }
                for (int i = 1; i <= kc; i++) {
                    g.drawImage(img, x, y + 40 * i, 40, 40, null);
                }
                if (dano[3] == true) {
                    kc = range[3];
                } else {
                    kc = bomsize;
                }
                for (int i = 1; i <= kc; i++) {
                    g.drawImage(img, x, y - 40 * i, 40, 40, null);
                }

            }
        }

    }

    public void breakWall() {

        if (time == 14) {

            for (int j = 0; j < handler.object.size(); j++) {
                GameObject temp = handler.object.get(j);
                switch (temp.getId()) {
                    case Wall2:
                        for (int i = 0; i < r1.size(); i++) {
                            if (r1.get(i).intersects(temp.getRec(0, 0))) {
                                if (dano[i % 4] == false) {
                                    range[i % 4] = i / 4 + 1;
                                    dano[i % 4] = true;

                                }
                            }
                        }
                        break;
                    case Wall:
                        for (int i = 0; i < r1.size(); i++) {
                            if (r1.get(i).intersects(temp.getRec(0, 0))) {
                                if (dano[i % 4] == false) {
                                    range[i % 4] = i / 4;
                                    dano[i % 4] = true;
                                }
                            }
                        }
                        break;
                    case Enemy:
                        for (int i = 0; i < r1.size(); i++) {
                            if (r1.get(i).intersects(temp.getRec(0, 0))) {
                                if (dano[i % 4] == false) {
                                    range[i % 4] = i / 4 + 1;
                                    dano[i % 4] = true;

                                }
                            }
                        }
                        break;
                    case Enemy2:
                        for (int i = 0; i < r1.size(); i++) {
                            if (r1.get(i).intersects(temp.getRec(0, 0))) {
                                if (dano[i % 4] == false) {
                                    range[i % 4] = i / 4 + 1;
                                    dano[i % 4] = true;

                                }
                            }
                        }
                        break;
                    case Player:
                        for (int i = 0; i < r1.size(); i++) {
                            if (r1.get(i).intersects(temp.getRec(0, 0)) || this.getRec(0, 0).intersects(temp.getRec(0, 0))) {
                                if (chamPlayer == false) {
                                    ((Player) temp).hp -= 1;
                                    System.out.println("Life remaining: " + ((Player) temp).hp);
                                    chamPlayer = true;
                                }
                            }
                        }
                    default:
                        break;

                }

            }

        }
        if (time == 15) {
            handler.removeObject(this);
            for (int j = 0; j < handler.object.size(); j++) {
                GameObject temp = handler.object.get(j);
                if (temp.id == ID.Player) {
                    ((Player) temp).bomnumber++;
                }
            }
        }

    }

    public void reBreakWall() {

        if (time == 14) {
            for (int j = 0; j < handler.object.size(); j++) {
                GameObject temp = handler.object.get(j);
                switch (temp.getId()) {
                    case Wall2:
                        if (temp.getRec(0, 0).intersects(this.getRec(range[0] * (40 / step), 0)) || temp.getRec(0, 0).intersects(this.getRec(-range[1] * (40 / step), 0)) || temp.getRec(0, 0).intersects(this.getRec(0, range[2] * (40 / step))) || temp.getRec(0, 0).intersects(this.getRec(0, -range[3] * (40 / step)))) {
                            temp.removeits();
                        }
                        break;
                    case Enemy:

                        if (temp.getRec(0, 0).intersects(this.getRec(range[0] * (40 / step), 0)) || temp.getRec(0, 0).intersects(this.getRec(-range[1] * (40 / step), 0)) || temp.getRec(0, 0).intersects(this.getRec(0, range[2] * (40 / step))) || temp.getRec(0, 0).intersects(this.getRec(0, -range[3] * (40 / step)))) {
                            temp.removeits();
                        }
                        break;
                    case Enemy2:
                        if (temp.getRec(0, 0).intersects(this.getRec(range[0] * (40 / step), 0)) || temp.getRec(0, 0).intersects(this.getRec(-range[1] * (40 / step), 0)) || temp.getRec(0, 0).intersects(this.getRec(0, range[2] * (40 / step))) || temp.getRec(0, 0).intersects(this.getRec(0, -range[3] * (40 / step)))) {
                            temp.removeits();
                        }
                        break;
                    default:
                        break;

                }

            }
        }
    }

    public int getBomsize() {
        return bomsize;
    }

    public void setBomsize(int bomsize) {
        this.bomsize = bomsize;
    }

}
