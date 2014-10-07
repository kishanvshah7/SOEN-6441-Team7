/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeditor;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import towerdefence.TowerDefence;

/**
 *
 * @author Rahul K Kikani
 */
public class MapEditor extends JPanel{

    public static Image[] backImage = new Image[1];
    public JButton play_btn = new JButton("Play");
    public JButton mapEditor_btn = new JButton("Create Map");
    public JButton exit_btn = new JButton("Exit");
    
    public MapEditor() {
        System.out.println("Main Screen");
        this.setSize(1167, 700);
        backImage[0] = new ImageIcon("res/first_screen.jpg").getImage();
        this.setLayout(new FlowLayout());
        //play_btn.setBounds(this.getWidth()/2, this.getHeight()/2, 100, 100);
        exit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        //setBorder (BorderFactory.createTitledBorder ("Log In"));
        this.add(exit_btn);
        this.setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.red);
        g.drawRect(10, 10, 200, 200);
        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}