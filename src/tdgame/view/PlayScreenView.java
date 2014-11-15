/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import towerdefensegame.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tdgame.controller.PlayScreenController;
import tdgame.model.CreatureModel;
import tdgame.model.configModel;
import static tdgame.model.configModel.air_level;
import static tdgame.model.configModel.ground_level;
import static tdgame.model.configModel.tileset_mob;
import static tdgame.model.configModel.tileset_res;

/**
  * This is GUI class of Play Screen Module.
 * @author Rahul K Kikani
 */
public class PlayScreenView extends JPanel implements Runnable {

    public Thread gameLoop = new Thread(this);
    
    private static boolean isFirst = true;
    
    public static CreatureModel[] Creatures = new CreatureModel[configModel.creaturesNo];
    CreatureView cView = new CreatureView();
    
    public static boolean isWin = false;
    boolean rFlag =false;
    static PlayScreenController psCont;
    
    public static int winTime = 4000, winFrame =0;
    
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
        tileset_mob[0] = new ImageIcon("resources/mob_level1.png").getImage();
    }
    
    public void initCreatures(){
        System.out.println("initCreatures");
        if(psCont != null){
            for(int i=0;i<Creatures.length;i++){
                Creatures[i] = new CreatureModel(psCont.getCcModel(),psCont.getCcCont());
                //mobs[i].spawnMob(0);
            }
            isFirst = false;
        } else
            System.out.println("psCont not initialized");
    }
    
    /**
     * This method will start thread.
     */
    public void startGame(){
        gameLoop.start();
    }
    
    public static void hasWon() {
        if(configModel.killed == configModel.killsToWin){
            //isWin = true;
            //configModel.killed = 0;
        }
        
        if(configModel.killed == configModel.creaturesNo){
            configModel.creaturesNo = 10;
            configModel.killed = 0;
            configModel.waveLap++;
            Creatures = new CreatureModel[configModel.creaturesNo];
            for(int i=0;i<Creatures.length;i++){
                Creatures[i] = new CreatureModel(psCont.getCcModel(),psCont.getCcCont());
                //mobs[i].spawnMob(0);
            }
        }
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
        
        if(isFirst)
        {
            initCreatures();
        }
        
        if(psCont !=null){
            g.setColor(new Color(50,50,50));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.white);
            psCont.getccDraw(g);
            
            for(int i=0; i< Creatures.length;i++){
                if(Creatures[i].isInGame()){
                    cView.draw(Creatures[i], i, g);
                }
            }
            
            psCont.getshopDraw(g);
        }
    }
    
    public int spawnTime = 2000, spawnFrame = 0;
    public void mobSpawner(){
        if(spawnFrame >= spawnTime){
            int i =0;
            for(i=0;i<Creatures.length;i++){
                if(!Creatures[i].isInGame() && Creatures[i].getHealth() == 100){
                    Creatures[i].spawnCreature(i);
                    break;
                } else{     
                    //System.out.println("In Game: "+i);
                }
            }
            spawnFrame = 0;
        }else{
            spawnFrame += 1;
        }
    }
    
    /**
     * This is run method for Thread.
     */
    @Override
    public void run() {
        while(true){
            
            if(isFirst){
                initCreatures();
            }
            
            if(rFlag && !isFirst && !isWin){
                //System.out.println("Gamp Loop!");
                psCont.getCcModel().physic(Creatures);
                mobSpawner();
                for(int i=0;i<Creatures.length;i++){
                    if(Creatures[i].isInGame()){
                        Creatures[i].physic();
                    }
                }
                //psCont.logic();
                repaint();
                //System.out.println("Test Loop!");
                try{
                    gameLoop.sleep(1);
                } catch (Exception e){
                    System.out.println("Some Error");
                }
            } else {
                if(isWin){
                    if(winFrame >= winTime){
                        if(configModel.level == configModel.maxLevel){
                            //System.exit(0);
                        }else{
                            configModel.level +=1;
                            //initCreatures();
                            isWin = false;
                        }
                        winFrame =0;
                    }else {
                        winFrame +=1;
                    }
                }
            }
        }
    }
    
}
