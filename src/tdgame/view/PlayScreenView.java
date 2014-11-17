/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import tdgame.controller.PlayScreenController;
import tdgame.controller.ShopController;
import tdgame.model.CreatureModel;
import tdgame.model.configModel;
import static tdgame.model.configModel.*;
import towerdefensegame.*;

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
    
    public static int winTime = 2000, winFrame =0;
    
    /**
     * This method will initialize GUI components for Play Screen.
     * @param j the GamePlay object
     */
    public PlayScreenView(GamePlay j){
        final KeyController eventSource = new KeyController();
 
        // create an observer
        final ShopController responseHandler = new ShopController();
 
        // subscribe the observer to the event source
        eventSource.addObserver(responseHandler);
        
        j.addMouseListener(eventSource);
        j.addMouseMotionListener(eventSource);
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
        fire[0] = new ImageIcon("resources/fire.gif").getImage();
        ice[0] = new ImageIcon("resources/ice.png").getImage();
        star[0] = new ImageIcon("resources/star.gif").getImage();
        
        happy[0] = new ImageIcon("resources/happy.gif").getImage();
        sad[0] = new ImageIcon("resources/sad.gif").getImage();
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
    
    public void newCreatures(){
        if(configModel.health > 0 && checkLiveCreatures()){
            configModel.creaturesNo = configModel.creaturesNo * 2;
            configModel.killsToWin = configModel.creaturesNo;
            configModel.killed = 0;
            configModel.waveLap++;
            configModel.level++;
            Creatures = new CreatureModel[configModel.creaturesNo];
            for(int i=0;i<Creatures.length;i++){
                Creatures[i] = new CreatureModel(psCont.getCcModel(),psCont.getCcCont());
            }
        }
    }
    
    /**
     * This method will start thread.
     */
    public void startGame(){
        gameLoop.start();
    }
    
    public boolean isGameOver(){
        if(configModel.health <= 0)
            return true;
        else 
            return false;
    }
    
    public static boolean hasWon() {
        if(configModel.level >= maxLevel){
            return true;
        } else{
            return false;
        }
    }
    
    public static boolean checkLiveCreatures(){
        for(int i=0;i<Creatures.length;i++){
                if(Creatures[i].getHealth() != 0)
                    return false;
            }
        return true;
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
        } else {
            newCreatures();
        }
        
        if(psCont !=null && !gameOberFlag){
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
        
        if(hasWon()){
            System.out.println("Game Over");
            Point Cp= GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
            for(int i=0; i< Creatures.length;i++){
                Creatures[i].setHealth(0);
                Creatures[i].setInGame(false);
            }
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(happy[0], Cp.x/2 - 60, Cp.y/2 - 120, 110, 100,  null);
            g.setColor(new Color(240, 20, 20));
            g.setFont(new Font("Courier New",Font.BOLD, 25));
            g.drawString("Congratulations", Cp.x/2 - 120, Cp.y/2);
            
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font("Courier New",Font.BOLD, 15));
            g.drawString("Level: "+level, Cp.x/2 - 80, Cp.y/2 + 20);
            g.drawString("Killed: "+total_killed, Cp.x/2 - 80, Cp.y/2 + 40);
            g.drawString("Earned: "+total_earned, Cp.x/2 - 80, Cp.y/2 + 60);
        }
        
        if(isGameOver()){
            if(!gameOberFlag)
            {
                total_earned = money;
                money = 0;
            }
            gameOberFlag = true;
            System.out.println("Game Over");
            Point Cp= GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
            for(int i=0; i< Creatures.length;i++){
                Creatures[i].setHealth(0);
                Creatures[i].setInGame(false);
            }
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(sad[0], Cp.x/2 - 60, Cp.y/2 - 120, 110, 100,  null);
            g.setColor(new Color(240, 20, 20));
            g.setFont(new Font("Courier New",Font.BOLD, 25));
            g.drawString("Game Over", Cp.x/2 - 80, Cp.y/2);
            
            g.setColor(new Color(0, 0, 0));
            g.setFont(new Font("Courier New",Font.BOLD, 15));
            g.drawString("Level: "+level, Cp.x/2 - 80, Cp.y/2 + 20);
            g.drawString("Killed: "+total_killed, Cp.x/2 - 80, Cp.y/2 + 40);
            g.drawString("Earned: "+total_earned, Cp.x/2 - 80, Cp.y/2 + 60);
        }
    }
    
    public int spawnTime = 1000, spawnFrame = 0;
    public void mobSpawner(){
        if(spawnFrame >= spawnTime){
            int i =0;
            for(i=0;i<Creatures.length;i++){
                if(!Creatures[i].isInGame() && Creatures[i].getHealth() == 44){
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
