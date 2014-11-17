/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * This is GUI class of Main Screen Panel Module.
 * @author Rahul K Kikani
 */
public class MainScreenPanel extends JPanel{
    public static Image[] backImage = new Image[1];
    
    /**
     * This method will initialize GUI components for Main Screen Panel.
     */
    public MainScreenPanel() {
        this.setSize(1167, 700);
        backImage[0] = new ImageIcon("res/first_screen.jpg").getImage();
    }
    
    /**
     * This method will draw GUI Components.
     * @param g the Graphics
     */
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(backImage[0], 0, 0, 1167, 700, null);
    }
}
