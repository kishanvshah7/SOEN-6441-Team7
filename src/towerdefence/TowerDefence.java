/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package towerdefence;

import homescreen.MainScreen;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author Rahul K Kikani
 */
public class TowerDefence extends JFrame{

    public static JPanel panel_screen;
    public static MainScreen ms;
   
    public TowerDefence() {
        this.setTitle("Tower Defence Game");
        this.setSize(1167, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        
        ms = new MainScreen();
        this.add(ms);
        this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TowerDefence td = new TowerDefence();
    }  
}
