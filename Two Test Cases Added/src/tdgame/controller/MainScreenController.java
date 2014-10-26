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
import tdgame.model.MapCreationModel;
import tdgame.view.MainScreenView;
import tdgame.view.MapCreationView;

/**
 *
 * @author Rahul K Kikani
 */
public class MainScreenController {
    
    MainScreenView theView;
    MainScreenModel theModel;
    MapCreationController theMapCreation;
    public MainScreenController(MainScreenView view, MainScreenModel model){
        this.theView = view;
        this.theModel = model;
        this.theView.addButtonClickEventListner(new ButtonActionDetector());
    }
    
    public boolean initMapCreationController(){
        MapCreationView theView1 = new MapCreationView();
        MapCreationModel theModel1 = new MapCreationModel();
        theView1.setVisible(true);
        //theView.setEnabled(false);
        theView.setAlwaysOnTop(false);
        theView1.setAlwaysOnTop(true);
        theView1.setFocusable(true);
        theMapCreation = new MapCreationController(theView1, theModel1, this);
        return true;
        //theMapCreation.startMapCreation();
    }
    
    public void setTopEnabled(){
        theView.setTopEnabled();
    }
    
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
                if(tempBtnStr.equals("Play")){
                    theView.displayMessage("Game Play Button Clicked.");
                }

                if(tempBtnStr.equals("Create Map")){
                    initMapCreationController();
                }

                if(tempBtnStr.equals("Exit")){
                    theView.dispose();
                    //System.exit(0);
                }
            }
        }
    }
}
