/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;

/**
 *
 * @author vuanhlk12
 */
public abstract class GameObject {

    protected int x, y;
    protected ID id;
    protected int vx, vy;
    protected Handler handler;

    public GameObject(int x, int y, Handler handler, ID id) {
        this.x = x;
        this.y = y;
        this.handler = handler;
        this.id = id;

    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getTx() {
        return x / 40;
    }

    public int getTy() {
        return y / 40;
    }

    public boolean canMoveR() {
        int i;
        for (i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if ((this.x + 40 + 5) > temp.getX() && temp.getX() > this.x) {
                if((this.y >= temp.getY() && this.y < (temp.getY()+40)) || ((this.y+40) > temp.getY() && (this.y+40) <= (temp.getY()+40)))
                return false;
            }
        }
        return true;
    }
    public boolean canMoveL() {
        int i;
        for (i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if ((this.x - 5) < temp.getX()+40 && temp.getX() < this.x) {
                if((this.y >= temp.getY() && this.y < (temp.getY()+40)) || ((this.y+40) > temp.getY() && (this.y+40) <= (temp.getY()+40)))
                return false;
            }
        }
        return true;
    }
    public boolean canMoveD() {
        int i;
        for (i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if ((this.y + 40 + 5) > temp.getY() && temp.getY() > this.y) {
                if((this.x >= temp.getX() && this.x < (temp.getX()+40)) || ((this.x+40) > temp.getX() && (this.x+40) <= (temp.getX()+40)))
                return false;
            }
        }
        return true;
    }
    public boolean canMoveU() {
        int i;
        for (i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if ((this.y - 5) < temp.getY()+40 && temp.getY() < this.y) {
                if((this.x >= temp.getX() && this.x < (temp.getX()+40)) || ((this.x+40) > temp.getX() && (this.x+40) <= (temp.getX()+40)))
                return false;
            }
        }
        return true;
    }
}
