/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author vuanhlk12
 */
public class Player extends GameObject {

    double hp;
    int bomnumber = 2;
    int bomsize = 1;

    public Player(int x, int y, Handler handler, ID id) {
        super(x, y, handler, id);
        hp = 3;
    }

    public synchronized void tick() {
        x += vx;
        y += vy;
        System.out.println("Life remaining: " + hp);
        if (hp == 0) {
            this.removeits();
        }

    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public int getBomnumber() {
        return bomnumber;
    }

    public void setBomnumber(int bomnumber) {
        this.bomnumber = bomnumber;
    }

    public synchronized void render(Graphics g) {
        if (this.id == ID.Player) {
            Image img = null;
            img = new ImageIcon("img\\Hero.png").getImage();
            g.drawImage(img, x, y, 40, 40, null);
        }

    }

}
