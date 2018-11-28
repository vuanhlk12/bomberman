/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author vuanhlk12
 */
public class Enemy extends GameObject{

    public Enemy(int x, int y, Handler handler, ID id) {
        super(x, y, handler, id);
    }



    public void tick() {
        x += vx;
        y += vy;
 
    }

    @Override
    public void render(Graphics g) {
        if (this.id == ID.Enemy) {
            g.setColor(Color.PINK);
        } 
        g.fillRect(x, y, 40, 40);
    }
    
}
