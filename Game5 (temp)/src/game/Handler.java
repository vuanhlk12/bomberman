/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author vuanhlk12
 */
public class Handler {

    public ArrayList<GameObject> object = new ArrayList<GameObject>();


    public void tick() {// gọi ra hàm tick() của các đối tượng trong danh sách
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
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
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
        int dem = 0;
        for (int j = 0; j < this.object.size(); j++) {
            GameObject temp = this.object.get(j);
            if (temp.getId() == ID.Wall2) {
                dem++;
            }
        }
        System.out.println(dem);
    }

    public void addFromFile(String Path) {
        int i = 0, j;
        ArrayList<String> word = new ArrayList<String>();
        File f = new File(Path);
        try {
            Scanner sc = new Scanner(f);
            try {
                while (sc.hasNextLine()) {
                    String str = sc.nextLine();
                    word.add(str);
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (sc != null) {
                    sc.close();
                }
            }
        } catch (Exception ep) {
            System.out.println(ep.getMessage());
        }
        for (i = 0; i < word.size(); i++) {
            String temp = word.get(i);
            for (j = 0; j < temp.length(); j++) {
                char c = temp.charAt(j);
                switch (c) {
                    case '#':
                        object.add(new Wall(40 * j, 40 * i, this, ID.Wall));
                        break;
                    case '*':
                        object.add(new Wall2(40 * j, 40 * i, this, ID.Wall2));
                        break;
                    case 'x':
                        object.add(new Portal(40 * j, 40 * i, this, ID.Portal));
                        break;
                    case 'p':
                        object.add(new Player(40 * j, 40 * i, this, ID.Player));
                        break;
                    case '1':
                        object.add(new Enemy(40 * j, 40 * i, this, ID.Enemy));
                        break;
                    case '2':
                        object.add(new Enemy2(40 * j, 40 * i, this, ID.Enemy2));
                        break;
                    case 'b':
                        object.add(new BomPlus(40 * j, 40 * i, this, ID.BomPlus));
                        break;
                    case 'f':
                        object.add(new BomSizePlus(40 * j, 40 * i, this, ID.BomSizePlus));
                        break;
                    case 's':
                        object.add(new speed(40 * j, 40 * i, this, ID.Speed));
                        break;
                            
                    default:
                        break;
                }
            }
            System.out.println("");
        }
    }
}
