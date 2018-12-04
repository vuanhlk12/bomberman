/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author vuanhlk12
 */
public class Player extends GameObject {

    double hp;
    int bomnumber = 1;
    int bomsize = 1;
    int move = 0;
    int sp = 3;// dùng để set speed cho player (từ 0-3, càng thấp càng nhanh)
    long currentTime;
    int buocdi = 0;
    int time = 0, oldtime = 0, oldtime2 = 0;
    long lastTime = System.nanoTime();

    public Player(int x, int y, Handler handler, ID id) {
        super(x, y, handler, id);
        hp = 3;
        System.out.println("Life remaining: " + hp);
    }

    public synchronized void tick() {
        currentTime = System.nanoTime();
        if (currentTime - lastTime >= 10000000) {
            time++;
            if (time - oldtime >= sp) {

                oldtime = time;
                switch (move) {// cái giá trị move này thay đổi khi mình nhập vào từ bàn phím (xem class KeyInput)
                    case 3:
                        if (this.canMoveR()) {
                            this.x += this.step;
                        }
                        break;
                    case 4:
                        if (this.canMoveL()) {
                            this.x -= this.step;
                        }
                        break;
                    case 1:
                        if (this.canMoveD()) {
                            this.y += this.step;
                        }
                        break;
                    case 2:
                        if (this.canMoveU()) {
                            this.y -= this.step;
                        }
                        break;
                    default:
                        break;

                }
            }
        }     
        
        if (hp == 0) {
            this.removeits();
            System.out.println("You Die");
        }

    }
    @Override
    public Rectangle getRec(int i, int j) {// hàm trả về 1 hình chữ nhật, i và j dùng để lấy hình chữ nhật có vị trí (+i,+j) so với đối tượng
        return new Rectangle(x + i * step, y + j * step, 30, 30);
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
            g.drawImage(img, x, y, 30, 30, null);
        }

    }

}
