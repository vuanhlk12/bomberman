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
public class Wall2 extends GameObject {

    int hp = 5;

    public Wall2(int x, int y, Handler handler, ID id) {
        super(x, y, handler, id);
    }

    public void tick() {
        x += vx;
        y += vy;

    }

   

    public void render(Graphics g) {
        if (this.id == ID.Wall2) {
            Image img = null;
            img = new ImageIcon("img\\Wall2.png").getImage();
            g.drawImage(img, x, y, 40, 40, null);

        }
    }

}
