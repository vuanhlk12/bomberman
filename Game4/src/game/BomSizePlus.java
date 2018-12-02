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
public class BomSizePlus extends GameObject{

    public BomSizePlus(int x, int y, Handler handler, ID id) {
        super(x, y, handler, id);
    }
    
    public synchronized void tick() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.id == ID.Player) {
                if (this.getRec(0, 0).intersects(temp.getRec(0, 0))) {
                    ((Player) temp).bomsize += 1;
                    this.removeits();
                }
            }
        }
    }

    public synchronized void render(Graphics g) {
        if (this.id == ID.BomSizePlus) {
            Image img = null;
            img = new ImageIcon("img\\bomsizeplus.png").getImage();
            g.drawImage(img, x, y, 40, 40, null);

        }
    }
}
