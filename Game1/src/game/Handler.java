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

    public ArrayList<GameObject> object = new ArrayList<GameObject>();
    public ID[][] map = new ID[13][13];

    Handler() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                map[i][j] = null;
            }
        }
    }

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

    public void addObject(GameObject object) {
        this.object.add(object);
        this.map[object.getX() / 40][object.getY() / 40] = object.getId();
    }

    public void removeObject(GameObject object) {
        this.map[object.getX() / 40][object.getY() / 40] = null;
        this.object.remove(object);
    }

    public void print() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public boolean checkHaveXY(int x, int y) {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            if (temp.getX() == x && temp.getY() == y) {
                return true;
            }
        }
        return false;

    }
    public void inwall() {
        int dem=0;
        for (int j = 0; j < this.object.size(); j++) {
            GameObject temp = this.object.get(j);
            if (temp.getId() == ID.Wall2) {
                dem++;
            }
        }
        System.out.println(dem);
    }
}
