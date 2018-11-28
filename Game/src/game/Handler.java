/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author vuanhlk12
 */
public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            temp.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            temp.render(g);
        }
    }
    
    public void addObject(GameObject object){
        
        this.object.add(object);
        
    }
    
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
