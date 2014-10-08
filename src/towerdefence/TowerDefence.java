/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package towerdefence;

import homescreen.MainScreen;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author Rahul K Kikani
 * 
 * @author Nitish GIT Testing
 */
public class TowerDefence extends JFrame{

    public static JPanel panel_screen;
    public static MainScreen ms;
   
    public TowerDefence() {
        this.setTitle("Tower Defence Game");
        Toolkit tk = Toolkit.getDefaultToolkit();  
        int xSize = ((int) tk.getScreenSize().getWidth());  
        int ySize = ((int) tk.getScreenSize().getHeight());  
        this.setSize(xSize,ySize);  
//        this.setSize(1167, 700);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setBackground(Color.black);

        
        ms = new MainScreen(this);
        this.getContentPane().add(ms);
        this.setVisible(true);
    }
    
    public void closeWindow(){
        System.exit(0);
    }
    
    public void setScreen(JPanel j){
        //panel_screen = j;
        System.out.println("SetScreen Called");
        this.getContentPane().add(j);
        this.validate();
        this.repaint();
    }
    public void removeScreen(){
        System.out.println("removeScreen Called");
        this.getContentPane().removeAll();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TowerDefence td = new TowerDefence();
    }  
}
