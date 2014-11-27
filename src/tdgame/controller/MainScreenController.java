/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import tdgame.model.MainScreenModel;
import tdgame.model.MapChooserModel;
import tdgame.model.MapCreationModel;
import tdgame.view.MainScreenView;
import tdgame.view.MapChooserView;
import tdgame.view.MapCreationView;
import towerdefensegame.LoadGame;
import towerdefensegame.LogGenerator;

/**
 * This Class will bind and initialize Model-View of Main Screen Module.
 * @author Rahul K Kikani
 */
public class MainScreenController {
    
    MainScreenView theView;
    MainScreenModel theModel;
    MapCreationController theMapCreation;
    
    /**
     * This method initialize view and model object.
     * @param view Main Screen View object
     * @param model Main Screen Model object 
     */
    public MainScreenController(MainScreenView view, MainScreenModel model){
        this.theView = view;
        this.theModel = model;
        this.theView.addButtonClickEventListner(new ButtonActionDetector());
    }
    
    /**
     * This method will initialize Model, View and Controller for Map Creation Module.
     */
    public void initMapCreationController(){
        MapCreationView mcView = new MapCreationView();
        MapCreationModel mcModel = new MapCreationModel();
        mcView.setVisible(true);
        //theView.setEnabled(false);
        theView.setAlwaysOnTop(false);
        mcView.setAlwaysOnTop(true);
        mcView.setFocusable(true);
        theMapCreation = new MapCreationController(mcView, mcModel, this);
        //theMapCreation.startMapCreation();
    }
    
    /**
     * This method will call setTopEnabled() from view class.
     */
    public void setTopEnabled(){
        theView.setTopEnabled();
    }
    
    /**
     * This class will perform action based on button pressed on Main Screen.
     */
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
                if(tempBtnStr.equals("Play")){
                    LogGenerator.addLog("Play button clicked.");
                    MapChooserModel mcModel = new MapChooserModel();
                    MapChooserView mcView = new MapChooserView(theView, mcModel.getMapFileList());
                    MapChooserController mp = new MapChooserController(mcView,mcModel);
                }

                if(tempBtnStr.equals("Create Map")){
                    LogGenerator.addLog("Create Map button clicked.");
                    initMapCreationController();
                }
                if(tempBtnStr.equals("Load Game")){
                    System.out.println("Load Game clicked.");
                    GameChooser gc = new GameChooser(theView);
                    //theView.dispose();
                    //System.exit(0);
                }

                if(tempBtnStr.equals("Exit")){
                    LogGenerator.addLog("Exit clicked.");
                    theView.dispose();
                    //System.exit(0);
                }
            }
        }
    }
}
