/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;

/**
 *
 * @author vuanhlk12
 */
public abstract class GameObject {

    protected int x, y;
    protected ID id;
    protected int vx, vy;
    protected Handler handler;
    protected int step = 20;

    public GameObject(int x, int y, Handler handler, ID id) {
        this.x = x;
        this.y = y;
        this.handler = handler;
        this.id = id;

    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public Rectangle getRec(int i, int j) {
        return new Rectangle(x + i * step, y + j * step, 40, 40);
    }

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
        for (int i = 0; i < this.handler.object.size(); i++) {
            GameObject temp = this.handler.object.get(i);
            if (temp.getId() != ID.Wall && temp.getId() != ID.Wall2 && temp.getId() != ID.Bom) {
                continue;
            } else if (this.getId() == ID.Player) {
                if (temp.getId() != ID.Wall && temp.getId() != ID.Wall2) {
                    continue;
                }
            }
            if (this.getRec(1, 0).intersects(temp.getRec(0, 0))) {
                return false;
            };
        }
        return true;
    }

    public boolean canMoveL() {
        for (int i = 0; i < this.handler.object.size(); i++) {
            GameObject temp = this.handler.object.get(i);
            if (temp.getId() != ID.Wall && temp.getId() != ID.Wall2 && temp.getId() != ID.Bom) {
                continue;
            } else if (this.getId() == ID.Player) {
                if (temp.getId() != ID.Wall && temp.getId() != ID.Wall2) {
                    continue;
                }
            }
            if (this.getRec(-1, 0).intersects(temp.getRec(0, 0))) {
                return false;
            };
        }
        return true;
    }

    public boolean canMoveD() {
        for (int i = 0; i < this.handler.object.size(); i++) {
            GameObject temp = this.handler.object.get(i);
            if (temp.getId() != ID.Wall && temp.getId() != ID.Wall2 && temp.getId() != ID.Bom) {
                continue;
            } else if (this.getId() == ID.Player) {
                if (temp.getId() != ID.Wall && temp.getId() != ID.Wall2) {
                    continue;
                }
            }
            if (this.getRec(0, 1).intersects(temp.getRec(0, 0))) {
                return false;
            };
        }
        return true;
    }

    public boolean canMoveU() {

        for (int i = 0; i < this.handler.object.size(); i++) {
            GameObject temp = this.handler.object.get(i);
            if (temp.getId() != ID.Wall && temp.getId() != ID.Wall2 && temp.getId() != ID.Bom) {
                continue;
            } else if (this.getId() == ID.Player) {
                if (temp.getId() != ID.Wall && temp.getId() != ID.Wall2) {
                    continue;
                }
            }
            if (this.getRec(0, -1).intersects(temp.getRec(0, 0))) {
                return false;
            };
        }
        return true;
    }

    public boolean equals(GameObject e) {
        return this.x == e.x && this.y == e.y && this.id == e.id && this.vx == e.vx && this.vy == e.vy && this.handler == this.handler;

    }

    public void removeits() {

        handler.removeObject(this);

    }
}
