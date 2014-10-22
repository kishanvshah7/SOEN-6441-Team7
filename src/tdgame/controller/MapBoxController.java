/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import tdgame.model.MapBoxModel;
import tdgame.view.MapBoxView;

/**
 *
 * @author Rahul K Kikani
 */
public class MapBoxController {
    
    MapBoxView theView;
    MapBoxModel theModel;
    
    public MapBoxController(){
        System.out.println("MapBoxController");
        this.theModel = new MapBoxModel();
        this.theView = new MapBoxView();
    }
    
    public int getXBlockCount(){
        return theModel.getXBlockCount();
    }
    public int getYBlockCount(){
        return theModel.getYBlockCount();
    }
    
    public void setXBlockCount(int x){
        System.out.println("MapBoxController.setXBlockCount()");
        theModel.setXBlockCount(x);
    }
    public void setYBlockCount(int y){
        System.out.println("MapBoxController.setYBlockCount()");
        theModel.setYBlockCount(y);
    }
}
