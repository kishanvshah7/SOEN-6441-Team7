/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Rahul K Kikani
 */
public class MainScreenView extends JFrame {
    
    JButton start_game;
    JButton map_creation;
    JButton exit_btn;
    
    public MainScreenView(){
        this.setTitle("Tower Defense Game");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.DARK_GRAY);
        
        //
        start_game = new JButton("Start");
        map_creation = new JButton("Map Creation");
        exit_btn = new JButton("Exit");
        
        this.setLayout(new FlowLayout(10));
        
        this.add(start_game);
        this.add(map_creation);
        this.add(exit_btn);
    }
    
    public void addButtonClickEventListner(ActionListener ListnerForButton){
        start_game.addActionListener(ListnerForButton);
        map_creation.addActionListener(ListnerForButton);
        exit_btn.addActionListener(ListnerForButton);
    }
    
    public void displayMessage(String str){
        //JOptionPane.showMessageDialog(this, str);
    }
}
