/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package homescreen;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import mapeditor.MapEditor;
import towerdefence.TowerDefence;

/**
 *
 * @author Rahul K Kikani
 */
public class MainScreen extends JPanel{

    public static Image[] backImage = new Image[1];
    public JButton play_btn = new JButton("Play");
    public JButton mapEditor_btn = new JButton("Create Map");
    public JButton exit_btn = new JButton("Exit");
    
    public MainScreen(TowerDefence d) {
        System.out.println("Main Screen");
        this.setSize(1167, 700);
        backImage[0] = new ImageIcon("res/first_screen.jpg").getImage();
        this.setLayout(new FlowLayout());
        //play_btn.setBounds(this.getWidth()/2, this.getHeight()/2, 100, 100);
        play_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You Clicked Button");
                d.removeScreen();
                //d.repaint();
                MapEditor MapE = new MapEditor();
                d.setScreen(MapE);
                //MapE.repaint();
            }
        });
        exit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(play_btn);
        this.add(mapEditor_btn);
        this.add(exit_btn);
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(backImage[0], 0, 0, 1167, 700, null);
    }
}
