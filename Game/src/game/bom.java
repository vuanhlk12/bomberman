/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author vuanhlk12
 */
public class bom extends GameObject {

    long currentTime;
    long lastTime = System.nanoTime();
    int time = 0;
    int bomsize = 2;
    int[] range = new int[4];
    boolean[] dano = new boolean[4];

    public bom(int x, int y, Handler handler, ID id) {
        super(x, y, handler, id);
    }

    @Override
    public void tick() {
        currentTime = System.nanoTime();
        if (currentTime - lastTime >= 1000000000) {
            time++;
            lastTime = currentTime;
        }

        if(time >=2)
         {
            for (int i = 1; i <= bomsize; i++) {
                for (int j = 0; j < handler.object.size(); j++) {
                    GameObject temp = handler.object.get(j);
                    if (temp.id == ID.Wall) {
                        if (temp.getX() == this.x + 40 * i && temp.getY() == this.y&& dano[0] == false) {
                            range[0] = i - 1;
                            dano[0] = true;
                        }
                        if (temp.getX() == this.x - 40 * i && temp.getY() == this.y&& dano[1] == false) {
                            range[1] = i - 1;
                            dano[1] = true;
                        }
                        if (temp.getY() == this.y + 40 * i && temp.getX() == this.x&& dano[2] == false) {
                            range[2] = i - 1;
                            dano[2] = true;
                        }
                        if (temp.getY() == this.y - 40 * i && temp.getX() == this.x&& dano[3] == false) {
                            range[3] = i - 1;
                            dano[3] = true;
                        }
                    }
                    if (temp.id == ID.Wall2) {
                        if (temp.getX() == this.x + 40 * i && temp.getY() == this.y && dano[0] == false) {
                            temp.setX(10000);
                            temp.setY(10000);                         
                            range[0] = i;
                            dano[0] = true;
                        }
                        if (temp.getX() == this.x - 40 * i && temp.getY() == this.y && dano[1] == false) {
                            temp.setX(10000);
                            temp.setY(10000); 
                            dano[1] = true;
                            range[1] = i;
                        }
                        if (temp.getY() == this.y + 40 * i && temp.getX() == this.x && dano[2] == false) {
                            temp.setX(10000);
                            temp.setY(10000); 
                            dano[2] = true;
                            range[2] = i;
                        }
                        if (temp.getY() == this.y - 40 * i && temp.getX() == this.x && dano[3] == false) {
                            temp.setX(10000);
                            temp.setY(10000); 
                            dano[3] = true;
                            range[3] = i;
                        }
                    }

                }
            }

        }
        if (time >= 3) {
            handler.object.remove(this);
        }
        this.handler = handler;
    }

    @Override
    public void render(Graphics g) {
        if (this.id == ID.Bom) {
            Image img = null;
            img = new ImageIcon("img\\bomb.png").getImage();
            g.drawImage(img, x, y, 40, 40, null);
            int kc = bomsize;
            if (time >= 2) {
                img = new ImageIcon("img\\fire3.png").getImage();
                g.drawImage(img, x, y, 40, 40, null);
                img = new ImageIcon("img\\fire2.png").getImage();
                if(dano[0]==true)
                    kc = range[0];
                else kc = bomsize;
                for (int i = 1; i <= kc; i++) {
                    g.drawImage(img, x + 40 * i, y, 40, 40, null);
                }
                if(dano[1]==true)
                    kc = range[1];
                else kc = bomsize;
                for (int i = 1; i <= kc; i++) {
                    g.drawImage(img, x - 40 * i, y, 40, 40, null);
                }
                img = new ImageIcon("img\\fire1.png").getImage();
                if(dano[2]==true)
                    kc = range[2];
                else kc = bomsize;
                for (int i = 1; i <= kc; i++) {
                    g.drawImage(img, x, y + 40 * i, 40, 40, null);
                }
                if(dano[3]==true)
                    kc = range[3];
                else kc = bomsize;
                for (int i = 1; i <= kc; i++) {
                    g.drawImage(img, x, y - 40 * i, 40, 40, null);
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
