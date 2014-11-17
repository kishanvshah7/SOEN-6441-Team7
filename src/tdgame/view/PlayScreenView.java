/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import towerdefensegame.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tdgame.controller.PlayScreenController;
import static tdgame.model.configModel.air_level;
import static tdgame.model.configModel.ground_level;
import static tdgame.model.configModel.tileset_res;

/**
  * This is GUI class of Play Screen Module.
 * @author Rahul K Kikani
 */
public class PlayScreenView extends JPanel implements Runnable {

    public Thread gameLoop = new Thread(this);
    
    private static boolean isFirst = true;
    
    boolean rFlag =false;
    PlayScreenController psCont;
    
    /**
     * This method will initialize GUI components for Play Screen.
     * @param j the GamePlay object
     */
    public PlayScreenView(GamePlay j){
        j.addMouseListener(new KeyController());
        j.addMouseMotionListener(new KeyController());
        for(int i=0;i<ground_level.length;i++){
            ground_level[i] = new ImageIcon("resources/grass_tile1.png").getImage();
            ground_level[i] = createImage(new FilteredImageSource(ground_level[i].getSource(), new CropImageFilter(0, 40*i, 40, 40)));
        }
        
        for(int i=0;i<air_level.length;i++){
            air_level[i] = new ImageIcon("resources/air_tile1.png").getImage();
            air_level[i] = createImage(new FilteredImageSource(air_level[i].getSource(), new CropImageFilter(0, 40*i, 40, 40)));
        }
        
        tileset_res[0] = new ImageIcon("resources/cell.png").getImage();
        tileset_res[1] = new ImageIcon("resources/heart.png").getImage();
        tileset_res[2] = new ImageIcon("resources/coin_icon.png").getImage();
    }
    
    /**
     * This method will start thread.
     */
    public void startGame(){
        gameLoop.start();
    }
    
    /**
     * This method will set          
     * @param cont the Play Screen Controller
     */
    public void setController(PlayScreenController cont){
        psCont = cont;
        rFlag = true;
    }
    
    /**
     * This method will draw GUI Components.
     * @param g the Graphics
     */
    public void paintComponent(Graphics g){
        //System.out.println("xyz");
        if(psCont !=null){
            g.setColor(new Color(50,50,50));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.white);
            psCont.getccDraw(g);
            psCont.getshopDraw(g);
            //g.drawRect(100, 100, 400, 400);
            //roomObj.draw(g); //Drawing from room
        }
    }
    
    /**
     * This is run method for Thread.
     */
    @Override
    public void run() {
        while(true){
            if(rFlag){
                //System.out.println("Gamp Loop!");
                //psCont.logic();
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
