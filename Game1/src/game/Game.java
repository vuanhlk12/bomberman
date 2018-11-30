/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author vuanhlk12
 */
public class Game extends Canvas implements Runnable {

    int width = 525, height = 545;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    ArrayList<Wall> wall = new ArrayList<Wall>();

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        new Window(width, height, "game", this);

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
        for (i = 0; i < wall.size(); i++) {
            handler.addObject(wall.get(i));
        }
        int [] a1= {1,1,1,1,2,2,3,3,3,3,3,6,7,8};
        int [] b1= {3,6,5,8,3,5,5,6,7,8,9,7,7,7};
        for(i=0;i<a1.length;i++){
            GameObject n = new Wall2(a1[i] * 40, b1[i] * 40, handler, ID.Wall2);
            handler.addObject(n);
        }
        handler.object.add(new Enemy(40*10,40*11, handler, ID.Enemy));
      //  handler.object.add(new Enemy(40*10,40*11, handler, ID.Enemy));
        /*Random r = new Random();
        for (i = 1; i <= 20; i++) {
            r = new Random();
            int x = r.nextInt(11) + 1;
            r = new Random();
            int y = r.nextInt(11) + 1;
            if (x % 2 == 0 && y % 2 == 0 || x == 1 && y == 1) {
                i--;
            } else {
                // if (!handler.checkHaveXY(x , y )) {
                GameObject n = new Wall2(x * 40, y * 40, handler, ID.Wall2);
                handler.addObject(n);
                if (!n.canMoveD()) {
                    handler.object.remove(n);
                }else i--;
                // }
                //else i--;
            }

        }*/

        handler.addObject(new Player(40, 40, handler, ID.Player));

    }

    public synchronized void start() {
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //    System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Color customColor = new Color(181,230,29);
        g.setColor(customColor);
        g.fillRect(0, 0, width, height);
        handler.render(g);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        // TODO code application logic here

        new Game();
    }

}
