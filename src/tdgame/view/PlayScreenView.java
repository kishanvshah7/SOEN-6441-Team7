/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import extra.mapReader;
import extra.saveGameFile;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import statePattern.Context;
import statePattern.PauseState;
import statePattern.StartState;
import statePattern.StopState;
import tdgame.controller.PlayScreenController;
import tdgame.controller.ShopController;
import tdgame.model.CreatureModel;
import tdgame.model.GridCellModel;
import tdgame.model.MapBoxModel;
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
    private static boolean isWon = false;
    
    public static CreatureModel[] Creatures = new CreatureModel[configModel.creaturesNo];
    CreatureView cView = new CreatureView();
    
    public static boolean isWin = false;
    boolean rFlag =false;
    static PlayScreenController psCont;
    
    public static int winTime = 2000, winFrame =0;
    public Graphics w;
    public static boolean gamePause = false;
    
    private KeyController eventSource;
    
    public StartState ss;
    public Context c;
    
    public Graphics getGraph(){
        return w;
    }
    
    /**
     * This method will initialize GUI components for Play Screen.
     * @param j the GamePlay object
     */
    public PlayScreenView(LoadGame j){
        LogGenerator.addLog("Game Play Screen initialized.");
        eventSource = new KeyController();

        
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
        LogGenerator.addLog("Basic Images are loaded successfully.");
    }
    
    public PlayScreenView(GamePlay j, StartState ss, Context c){
        this.ss =ss;
        this.c = c;
        LogGenerator.addLog("Game Play Screen initialized.");
        eventSource = new KeyController();
 
        
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
        LogGenerator.addLog("Basic Images are loaded successfully.");
    }
    
    /**
     * Initializing creatures
     * @return successFlag
     */
    public boolean initCreatures(){
        if(configModel.level ==1){
            
            LogGenerator.addLog("First Level get started");
            LogGenerator.addLog(creaturesNo+" New Creatures Created.");
            LogGenerator.addWaveLog("First Level get started");
            LogGenerator.addWaveLog(creaturesNo+" New Creatures will be Created.");
            LogGenerator.addWaveLog("Total Money:"+money);
            LogGenerator.addWaveLog("Game Health:"+health);
            if(psCont != null){
                for(int i=0;i<Creatures.length;i++){
                    Creatures[i] = new CreatureModel(psCont.getCcModel(),psCont.getCcCont());
                    //mobs[i].spawnMob(0);
                }
                isFirst = false;
            } else {
                
            }
        } else if(GameLoadFlag) {
            configModel.creaturesNo = configModel.creaturesLevel[configModel.level];
            configModel.killsToWin = configModel.creaturesNo;
            configModel.killed = 0;
            LogGenerator.addLog("Game Loaded");
            LogGenerator.addLog(creaturesNo+" New Creatures Created.");
            LogGenerator.addWaveLog("Wave started: "+level);
            LogGenerator.addWaveLog(creaturesNo+" New Creatures will be Created.");
            LogGenerator.addWaveLog("Total Money:"+money);
            LogGenerator.addWaveLog("Game Health:"+health);
            Creatures = new CreatureModel[configModel.creaturesNo];
            if(psCont != null){
                for(int i=0;i<Creatures.length;i++){
                    Creatures[i] = new CreatureModel(psCont.getCcModel(),psCont.getCcCont());
                }
                isFirst = false;
            } else {
                
            }
        }
        if(psCont != null){
            return true;
        } else 
            return false;
    }
    
    public void saveGame(){
        if(c.getState().toString().equals("Start")){
            gamePause = true;
            PauseState pauseState = new PauseState();
            pauseState.doAction(c);
            
            LogGenerator.addLog("Game Saved");
            saveGameFile.initFile();
            GridCellModel[][] gcm = psCont.getCcModel().getGcModel();
            saveGameFile.mapInfo(gcm);
            saveGameFile.towerInfo();
            saveGameFile.gameInfo();
            saveGameFile.saveFile();
            updateScore();
            gamePause = false;
            ss.doAction(c);
        }
        
    }
    
    public void updateScore(){
        mapReader sem = new mapReader();
        sem.updateScore(configModel.money);
    }
    
    public void updateMapFile(){
        mapReader sem = new mapReader();
        sem.updateScore(configModel.money);
        sem.initFile();
        sem.mapInfo(psCont.getTheModel().getGridCellArray());
        sem.mapEInfo();
        sem.scoreInfo();
        sem.playerInfo();
        sem.saveFile();
    }
    
    /**
     * Adding new creatures for next level
     */
    public void newCreatures(){
        if((configModel.level > maxLevel) && health != 0){
            isWon = true;
        } else{
            isWon = false;
        }
        if((configModel.health > 0 && checkLiveCreatures()) && !isWon){
            configModel.level++;
            saveGame();
            configModel.creaturesNo = configModel.creaturesLevel[configModel.level];
            configModel.killsToWin = configModel.creaturesNo;
            configModel.killed = 0;
            LogGenerator.addLog("Game Level Upgraded to : "+level);
            LogGenerator.addLog("new Creatures : "+configModel.creaturesNo);
            GameLogViewer.addText_Wjtp("==================================\n");
            LogGenerator.addWaveLog("Game Wave Upgraded to : "+level);
            LogGenerator.addWaveLog(creaturesNo+" New Creatures will be Created.");
            LogGenerator.addWaveLog("Total Money:"+money);
            LogGenerator.addWaveLog("Game Health:"+health);

            Creatures = new CreatureModel[configModel.creaturesNo];
            for(int i=0;i<Creatures.length;i++){
                Creatures[i] = new CreatureModel(psCont.getCcModel(),psCont.getCcCont());
            }
        }
    }
    
    /**
     * This method will start thread.
     * @return successFlag
     */
    public boolean startGame(){
        gameLoop.start();
        return true;
    }
    
    /**
     * Check Game over or not.
     * @return successFlag
     */
    public boolean isGameOver(){
        if(configModel.health <= 0)
            return true;
        else 
            return false;
    }
    
    /**
     * Check user is won the game or not.
     * @return successFlag
     */
    public static boolean hasWon() {
        if((configModel.level >= maxLevel) && configModel.killed == configModel.killsToWin){
            return true;
        } else{
            return false;
        }
    }
    
    /**
     * Check Creatures Life health
     * @return successFlag 
     */
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
        w=g;
        if(isFirst)
        {
            initCreatures();
        } else {
            newCreatures();
        }
        
        if(psCont !=null && !gameOverFlag){
            g.setColor(new Color(50,50,50));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.white);
            psCont.getccDraw(g);
            
            if(Creatures.length > 0){
                for(int i=0; i< Creatures.length;i++){
                    if(Creatures[i].isInGame()){
                        cView.draw(Creatures[i], i, g);
                    }
                }
            }
            
            psCont.getshopDraw(g);
        }
        
        if(hasWon() || isWon){
            if(!gameWonFlag)
            {
                LogGenerator.addLog("User Won Game");
                System.out.println("Congratulations");
                updateMapFile();
            }
            gameWonFlag = true;
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
            g.drawString("Level: "+(level-1), Cp.x/2 - 80, Cp.y/2 + 20);
            g.drawString("Killed: "+total_killed, Cp.x/2 - 80, Cp.y/2 + 40);
            g.drawString("Earned: "+money, Cp.x/2 - 80, Cp.y/2 + 60);
            
            g.drawString("Top Score",Cp.x/2 - 80, Cp.y/2 + 80);
            g.drawString("1. "+mapReader.topScore[0],Cp.x/2 - 70, Cp.y/2 + 100);
            g.drawString("2. "+mapReader.topScore[1],Cp.x/2 - 70, Cp.y/2 + 115);
            g.drawString("3. "+mapReader.topScore[2],Cp.x/2 - 70, Cp.y/2 + 130);
            g.drawString("4. "+mapReader.topScore[3],Cp.x/2 - 70, Cp.y/2 + 145);
            g.drawString("5. "+mapReader.topScore[4],Cp.x/2 - 70, Cp.y/2 + 160);
        }
        
        if(isGameOver()){
            if(!gameOverFlag)
            {
                total_earned = money;
                money = 0;
                System.out.println("Game Over");
                LogGenerator.addLog("User lost game.");
                updateMapFile();
            }
            gameOverFlag = true;
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
            g.drawString("Level: "+(level-1), Cp.x/2 - 80, Cp.y/2 + 20);
            g.drawString("Killed: "+total_killed, Cp.x/2 - 80, Cp.y/2 + 40);
            g.drawString("Earned: "+total_earned, Cp.x/2 - 80, Cp.y/2 + 60);
            
            g.drawString("Top Score",Cp.x/2 - 80, Cp.y/2 + 80);
            g.drawString("1. "+mapReader.topScore[0],Cp.x/2 - 70, Cp.y/2 + 100);
            g.drawString("2. "+mapReader.topScore[1],Cp.x/2 - 70, Cp.y/2 + 115);
            g.drawString("3. "+mapReader.topScore[2],Cp.x/2 - 70, Cp.y/2 + 130);
            g.drawString("4. "+mapReader.topScore[3],Cp.x/2 - 70, Cp.y/2 + 145);
            g.drawString("5. "+mapReader.topScore[4],Cp.x/2 - 70, Cp.y/2 + 160);
        }
    }
    
    /**
     * Creatures Movement for current level
     */
    public int spawnTime = 1000, spawnFrame = 0;
    public boolean mobSpawner(){
        if(spawnFrame >= spawnTime){
            int i =0;
            for(i=0;i<Creatures.length;i++){
                if(!Creatures[i].isInGame() && Creatures[i].getHealth() == 44){
                    Creatures[i].setHealth(44 * configModel.level);
                    Creatures[i].spawnCreature(i);
                    break;
                }
            }
            spawnFrame = 0;
        }else{
            spawnFrame += 1;
        }
        return true;
    }
    
    /**
     * This is run method for Thread.
     */
    @Override
    public void run() {
        while(true){
            if(!gamePause){
                if(isFirst){
                    initCreatures();
                }

                if(rFlag && !isFirst && !isWin){
                    try {
                        psCont.getCcModel().physic(Creatures);
                    } catch (ParseException ex) {
                        Logger.getLogger(PlayScreenView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    mobSpawner();
                    for(int i=0;i<Creatures.length;i++){
                        if(Creatures[i].isInGame()){
                            Creatures[i].physic();
                        }
                    }
                    repaint();
                    try{
                        gameLoop.sleep(1);
                    } catch (Exception e){
                        System.out.println("Some Error");
                    }
                } else {
                    if(isWin){
                        if(winFrame >= winTime){
                            if(configModel.level == configModel.maxLevel){
                            }else{
                                configModel.level +=1;
                                isWin = false;
                            }
                            winFrame =0;
                        }else {
                            winFrame +=1;
                        }
                    }
                }
            }else {
                try {
                    gameLoop.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlayScreenView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * @param eventSource the eventSource to set
     */
    public void setEventSource(KeyController eventSource) {
        this.eventSource = eventSource;
    }

    /**
     * @return the eventSource
     */
    public KeyController getEventSource() {
        return eventSource;
    }
    
}
