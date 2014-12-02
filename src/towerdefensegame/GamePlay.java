/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package towerdefensegame;

import extra.ReadXMLFile;
import extra.mapReader;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import statePattern.Context;
import statePattern.StartState;
import tdgame.controller.MapValidation;
import tdgame.controller.PlayScreenController;
import tdgame.model.CellContainerModel;
import tdgame.model.GridCellModel;
import tdgame.model.PlayScreenModel;
import tdgame.model.ShopModel;
import tdgame.model.configModel;
import static tdgame.model.configModel.GameLoadFlag;
import tdgame.view.CellContainerView;
import tdgame.view.GridCellView;
import tdgame.view.PlayScreenView;
import tdgame.view.ShopView;

/**
 * This class initialize the game play screen.
 * @author Rahul K Kikani
 */
public class GamePlay extends JFrame implements WindowListener {
    
    private PlayScreenController psCont;
    private PlayScreenModel psModel;
    /**
     * This method will initialize JFrame and set some basic properties(Title, Size, Background Color, Location).
     * @param f Map file which is selected by user from list box.
     * @param w Width of Play screen based on map size
     * @param h Height of Play screen based on map size
     * @param type GameLoad and Play Type
     */
    public GamePlay(File f, int w, int h, String type)
    {
        LogGenerator.addLog("Game Play Started.");
        int width = w*40 + 350;
        int height = h*40 + 120;
        this.setTitle("Tower Defence Game");
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setBackground(Color.darkGray);
        if(type.equals("GamePlay"))
            init_elements(f);
        else if(type.equals("GameLoad"))
        {
            GameLoadFlag = true;
            init_elements_xml();
        }
        this.addWindowListener(this);
    }
    
    /**
     * This method will check the Map file and based on the type of map, it will bind the model to the controller and create the play view using the View.
     * @param f Map file which is selected by user from list box.
     */
    public void init_elements(File f)
    {
        this.setLayout(new GridLayout(1, 1, 0, 0));
        
         psModel = new PlayScreenModel();
                        boolean temp = psModel.LoadMap_XML(f);
                        if(temp){
                            MapValidation mv = new MapValidation(psModel.getGridCellArray());
                            if(mv.isValid()){
                                Context context = new Context();

                                configModel cModel = new configModel(); 
                            
                                psModel.initCellContainerModel();
                                psModel.setGridCellVal();

                                ShopModel sModel = new ShopModel(psModel.getStartX(),psModel.getStartY());
                                ShopView sView = new ShopView();

                                CellContainerView ccView = new CellContainerView();
                                CellContainerModel ccModel = psModel.getCellContainerModel();

                                GridCellView gcView = new GridCellView();
                                GridCellModel[][] gcModel = ccModel.getGcModel();
                                
                                StartState startState = new StartState();
                                startState.doAction(context);

                                PlayScreenView psView = new PlayScreenView(this, startState, context);
                                add(psView);
                                psCont = new PlayScreenController(psView, psModel, gcView, gcModel, ccView, ccModel, sView, sModel);
                                psView.setController(getPsCont());
                                psView.startGame();

                                psCont.getsCont().addObserver(sView);
                                this.setVisible(true);
                            }else{
                                System.out.println("Map Is Invalid");
                                JOptionPane.showMessageDialog(this,"Map is Invalid", null, WIDTH);
                            }  
                        }else{
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "Invalid Map File", null, WIDTH);
                        }
    }
    
    
    public void init_elements_xml()
    {
        this.setLayout(new GridLayout(1, 1, 0, 0));
        
        psModel = new PlayScreenModel();
        psModel.setGridCellArray(ReadXMLFile.getGridArray());
                        if(true){
                            MapValidation mv = new MapValidation(psModel.getGridCellArray());
                            if(mv.isValid()){
                                Context context = new Context();

                                psModel.initCellContainerModel();
                                psModel.setGridCellVal();

                                ShopModel sModel = new ShopModel(psModel.getStartX(),psModel.getStartY());
                                ShopView sView = new ShopView();

                                CellContainerView ccView = new CellContainerView();
                                CellContainerModel ccModel = psModel.getCellContainerModel();

                                GridCellView gcView = new GridCellView();
                                GridCellModel[][] gcModel = ccModel.getGcModel();
                                
                                StartState startState = new StartState();
                                startState.doAction(context);

                                PlayScreenView psView = new PlayScreenView(this, startState, context);
                                
                                add(psView);
                                psCont = new PlayScreenController(psView, psModel, gcView, gcModel, ccView, ccModel, sView, sModel);
                                psView.setController(getPsCont());
                                
                                psCont.getsCont().addObserver(sView);
                                psView.startGame();
                                this.setVisible(true);
                            }else{
                                System.out.println("Map Is Invalid");
                                JOptionPane.showMessageDialog(this,"Map is Invalid", null, WIDTH);
                            }  
                        }else{
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "Invalid Map File", null, WIDTH);
                        }
    }

    /**
     * @return the psCont
     */
    public PlayScreenController getPsCont() {
        return psCont;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        LogGenerator.addLog("Game Play Start");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        LogGenerator.addLog("Game Play Window Closed.");
        LogGenerator.addLog("Game Closed");
        LogGenerator.closeLog();
        LogGenerator.towerLog();
        mapReader sem = new mapReader();
        sem.initFile();
        sem.mapInfo(psModel.getGridCellArray());
        sem.mapEInfo();
        sem.scoreInfo();
        sem.playerInfo();
        sem.saveFile();
        
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        LogGenerator.addLog("Game Play Window Closed.");
        LogGenerator.addLog("Game Closed");
        LogGenerator.closeLog();
        LogGenerator.towerLog();
        
        mapReader sem = new mapReader();
        sem.initFile();
        sem.mapInfo(psModel.getGridCellArray());
        sem.mapEInfo();
        sem.scoreInfo();
        sem.playerInfo();
        sem.saveFile();
        
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
}
