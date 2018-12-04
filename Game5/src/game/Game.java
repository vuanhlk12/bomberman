/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author vuanhlk12
 */
// Đây là hàm main, từ hàm này khởi tạo ra cửa sổ window
public class Game extends Canvas {

    int width = 1300, height = 800;//525 545
    private Thread thread;
    private boolean running = true;
    private Handler handler;
    ArrayList<Wall> wall = new ArrayList<Wall>();

    public Game() {
        handler = new Handler();// xem các hàm ở class handler, mục đích của hàm này là để lưu lại tất cả các object trong game
        this.addKeyListener(new KeyInput(handler));
        JFrame frame = new JFrame("Bomberman - PVA");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);

        //handler.addFromFile("map\\1.txt");
        int i, j;
        for (i = 0; i < 13; i++) {
            wall.add(new Wall(i * 40, 0, handler, ID.Wall));
        }
        for (i = 1; i < 13; i++) {
            wall.add(new Wall(0, i * 40, handler, ID.Wall));
        }
        for (i = 1; i < 13; i++) {
            wall.add(new Wall(i * 40, 480, handler, ID.Wall));
        }
        for (i = 1; i < 12; i++) {
            wall.add(new Wall(480, i * 40, handler, ID.Wall));
        }
        for (i = 2; i <= 11; i++) {
            for (j = 2; j <= 11; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    wall.add(new Wall(i * 40, j * 40, handler, ID.Wall));
                }
            }
        }
        handler.object.add(new Portal(40 * 11, 40 * 3, handler, ID.Portal));
        for (i = 0; i < wall.size(); i++) {
            handler.addObject(wall.get(i));
        }
        int[] a1 = {1, 1, 1, 1, 3, 3, 3, 3, 3, 6, 7, 8, 11, 9};
        int[] b1 = {3, 6, 5, 8, 5, 6, 7, 8, 9, 7, 7, 7, 9, 11};
        for (i = 0; i < a1.length; i++) {
            GameObject n = new Wall2(a1[i] * 40, b1[i] * 40, handler, ID.Wall2);
            handler.addObject(n);
        }
        handler.object.add(new Enemy(40, 40 * 11, handler, ID.Enemy));
        handler.object.add(new Enemy2(40 * 10, 40 * 11, handler, ID.Enemy2));

        handler.object.add(new BomPlus(40 * 11, 40, handler, ID.BomPlus));
        handler.object.add(new BomSizePlus(40 * 10, 40, handler, ID.BomSizePlus));
        handler.object.add(new LifePlus(40 * 11, 40 * 2, handler, ID.LifePlus));
        handler.object.add(new speed(40 * 9, 40, handler, ID.Speed));

        handler.addObject(new Player(40, 40, handler, ID.Player));
        // toàn bộ các dòng trên đều là thêm object vào handler
        this.run();// chạy chương trình

    }

    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            /*frames++;//cái dưới để hiển thị fps, thích thì bật k thích thì thôi
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }*/
        }
    }

    private void tick() {//hàm dùng để cập nhật các đối tượng
        handler.tick();// hàm này có tác dụng cập nhật tất cả đối tượng có trong handler

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();//gọi ra cái Graphics g này rồi truyền vào hàm render của các object để các object dùng cái Graphics g này vẽ tiếp (dùng chung 1 Graphics g từ đầu đến cuối)
        Color customColor = new Color(181, 230, 29);
        g.setColor(customColor);
        g.fillRect(0, 0, width, height);
        handler.render(g);
        g.dispose();
        bs.show();//sau khi đã vẽ hết thì show ra
    }

    public static void main(String[] args) {
        // TODO code application logic here

        new Game();
    }

}
