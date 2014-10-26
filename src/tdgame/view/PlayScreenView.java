/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tdgame.controller.PlayScreenController;

/**
 *
 * @author Rahul K Kikani
 */
public class PlayScreenView extends JPanel implements Runnable {

    public Thread gameLoop = new Thread(this);
    
    private static boolean isFirst = true;
    
    boolean rFlag =false;
    PlayScreenController psCont;
    
    public PlayScreenView(){
        gameLoop.start();
    }
    
    
    public void setController(PlayScreenController cont){
        psCont = cont;
        rFlag = true;
    }
    
    public void initGameComponents(){
        
    }
    
    
    public void paintComponent(Graphics g){
        System.out.println("xyz");
        if(psCont !=null){
            g.setColor(new Color(50,50,50));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.white);
            psCont.getccDraw(g);
            //g.drawRect(100, 100, 400, 400);
            //roomObj.draw(g); //Drawing from room
        }
    }
    
    @Override
    public void run() {
        while(true){
            if(rFlag){
                System.out.println("Gamp Loop!");
                psCont.logic();
                repaint();
                //System.out.println("Test Loop!");
                try{
                    gameLoop.sleep(1);
                } catch (Exception e){
                    System.out.println("Some Error");
                }
            }
        }
    }
    
}
