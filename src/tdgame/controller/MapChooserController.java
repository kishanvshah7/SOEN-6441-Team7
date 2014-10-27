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
 *
 * @author Rahul K Kikani
 */
public class MapChooserController {

    MapChooserView theView = new MapChooserView();
    MapChooserModel theModel = new MapChooserModel();
    
    MapChooserController(MapChooserView mcView, MapChooserModel mcModel) {
        this.theView = mcView;
        this.theModel = mcModel;
        this.theView.addButtonClickEventListner(new ButtonActionDetector());
    }
    
    public String[] getMapFileList(){
        return theModel.getMapFileList();
    }
    
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
                if(tempBtnStr.equals("Let's Play")){
                    if(theView.getSelectedFile().equals("NONE")){
                        theView.displayMessage("Please Select At least one file.");
                    }else{
                        PlayScreenModel psModel = new PlayScreenModel();
                        boolean temp = psModel.LoadMap(new File("MapFiles/"+theView.getSelectedFile()));
                        if(temp){
                            GamePlay gp = new GamePlay(new File("MapFiles/"+theView.getSelectedFile()), psModel.getxC(), psModel.getyC());
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
