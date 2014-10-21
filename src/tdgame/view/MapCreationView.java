/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 *
 * @author Rahul K Kikani
 */
public class MapCreationView extends JFrame{
    
    public MapCreationView(){
        this.setVisible(true);
        this.setTitle("Map Creation Window");
        this.setSize(500,500);
        this.setBackground(Color.BLUE);
        this.setLayout(new FlowLayout(10));
        this.add(new JButton("Hey"));
    }
}
