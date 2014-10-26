/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Graphics;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Rahul K Kikani
 */
public class PlayScreenFrame extends JFrame{

    public PlayScreenFrame(PlayScreenView psView) {
        this.setTitle("Tower Defense Game : Playing Mode");
        this.setSize(1000, 700);
        this.add(psView);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }
    
    public void paintComponent(Graphics g){
        System.out.println("Main File");
    }
}
