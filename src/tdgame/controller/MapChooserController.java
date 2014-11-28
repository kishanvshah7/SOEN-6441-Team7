/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import tdgame.model.MapChooserModel;
import tdgame.model.PlayScreenModel;
import tdgame.view.MapChooserView;
import towerdefensegame.*;

/**
 * This Class will bind and initialize Model-View of Map Chooser Module.
 * @author Rahul K Kikani
 */
public class MapChooserController {

    MapChooserView theView = new MapChooserView();
    MapChooserModel theModel = new MapChooserModel();
    
    /**
     * This is constructor of Map Chooser.
     * @param mcView the MapChooserView
     * @param mcModel the MapChooserModel
     */
    MapChooserController(MapChooserView mcView, MapChooserModel mcModel) {
        this.theView = mcView;
        this.theModel = mcModel;
        this.theView.addButtonClickEventListner(new ButtonActionDetector());
    }
    
    /**
     * This method will call getMapFileList() from model.
     * @return the String array of MapFiles.
     */
    public String[] getMapFileList(){
        return theModel.getMapFileList();
    }
    
    /**
     * This class will perform action based on buttons pressed in Map Chooser.
     */
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
                if(tempBtnStr.equals("Let's Play")){
                    LogGenerator.addLog("Let's Play clicked.");
                    if(theView.getSelectedFile().equals("NONE")){
                        theView.displayMessage("Please Select At least one file.");
                    }else{
                        LogGenerator.addLog("User's map file:"+theView.getSelectedFile());
                        PlayScreenModel psModel = new PlayScreenModel();
                        boolean temp = psModel.LoadMap_XML(new File("MapFiles/"+theView.getSelectedFile()));
                        if(temp){
                            GamePlay gp = new GamePlay(new File("MapFiles/"+theView.getSelectedFile()), psModel.getxC(), psModel.getyC(), "GamePlay");
                            theView.setMSTOp(false);
                            theView.dispose();
                        }
                        else{
                            theView.displayMessage("Incorrect Map File");
                        }
                        
                    }
                }
            }
        }
    }
}
