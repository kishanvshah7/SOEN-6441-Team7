/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import tdgame.model.MapCreationModel;
import tdgame.view.MapCreationView;

/**
 *
 * @author Rahul K Kikani
 */
public class MapCreationController {
    
    MapCreationView theView;
    MapCreationModel theModel;
    
    MapBoxController mbCon;
    
    public MapCreationController(){
        
    }
    
    public MapCreationController(MapCreationView view, MapCreationModel model){
        this.theView = view;
        this.theModel = model;
        this.theView.addButtonClickEventListner(new ButtonActionDetector());
    }
    
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
                if(tempBtnStr.equals("Set Grid")){
                    int xC = theView.getXBlockInput();
                    int yC = theView.getYBlockInput();
                    String errMsg = "";
                    if(xC == 0 || yC ==0){
                        errMsg += "Input is Invalid.";
                    }
                    if(xC > 15){
                        errMsg += "Sorry, Your X Block Size is bigger then 15. Please use small value.\n";
                    }
                    if(yC > 15){
                        errMsg += "Sorry, Your Y Block Size is bigger then 15. Please use small value.\n";
                    }
                    
                    if(errMsg.equals("")){
                        System.out.println("0");
                        mbCon = new MapBoxController();
                        mbCon.setXBlockCount(xC);
                        mbCon.setYBlockCount(yC);
                        mbCon.setGridArray();
                        theView.addGridMap(mbCon);
                        theView.disableSubmitButton();
                    }
                    else
                    {
                        theView.displayMessage(errMsg);
                    }
                }
                
                if(tempBtnStr.equals("Entry Point")){
                    mbCon.setEntryPointFlag();
                }
                
                if(tempBtnStr.equals("Draw Path")){
                    mbCon.setPathPointFlag();
                }
                
                if(tempBtnStr.equals("Exit Point")){
                    mbCon.setExitPointFlag();
                }

                if(tempBtnStr.equals("Save Map")){
                    mbCon.saveMap(theView.getFileName());
                    //mbCon.validPath();
                }

                if(tempBtnStr.equals("Exit")){
                    theView.displayMessage("Create Map Exit Button Clicked.");
                }
            }
        }
    }
    
    public void startMapCreation(){
        this.theView.setVisible(true);
    }
    
    public void setXBlockCount(int x){
        theModel.setXBlockCount(x);
    }
    public void setYBlockCount(int y){
        theModel.setYBlockCount(y);
    }
    public int getXBlockCount(){
        return theModel.getXBlockCount();
    }
    public int getYBlockCount(){
        return theModel.getYBlockCount();
    }
}
