/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    public void initMapCreationController(){
        MapCreationView theView1 = new MapCreationView();
    MapCreationModel theModel1 = new MapCreationModel();
        theView1.setVisible(true);
        theView.setEnabled(false);
        theView.setAlwaysOnTop(false);
        theView1.setAlwaysOnTop(true);
        theView1.setFocusable(true);
        //theMapCreation = new MapCreationController(theView1, theModel1);
        //theMapCreation.startMapCreation();
    }
    
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //theView.displayMessage("Button Clicked");
            System.out.println("Button Clicked");
            initMapCreationController();
        }
        
    }
}
