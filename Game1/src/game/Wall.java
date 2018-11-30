/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

/**
 *
 * @author vuanhlk12
 */
public class Wall extends GameObject {

    public Wall(int x, int y, Handler handler, ID id) {
        super(x, y, handler, id);
    }

    public synchronized void tick() {
        x += vx;
        y += vy;
        
    }

    public synchronized void render(Graphics g) {
        if (this.id == ID.Wall) {
            Image img = null;
            img = new ImageIcon("img\\Wall.png").getImage();
            g.drawImage(img, x, y, 40, 40, null);

        }
    }

}
