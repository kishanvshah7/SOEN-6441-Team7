/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import tdgame.model.MapCreationModel;
import tdgame.view.MapCreationView;

/**
 *
 * @author Rahul K Kikani
 */
public class MapCreationController {
    
    MapCreationView theView;
    MapCreationModel theModel;
    public MapCreationController(MapCreationView view, MapCreationModel model){
        System.out.println("3");
        this.theView = view;
        this.theModel = model;
    }
    
    public void startMapCreation(){
        this.theView.setVisible(true);
    }
}
