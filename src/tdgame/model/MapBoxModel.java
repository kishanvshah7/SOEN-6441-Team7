/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

/**
 *
 * @author Rahul K Kikani
 */
public class MapBoxModel {
    
    public int xBlockCount;
    public int yBlockCount;
    public int blockSize;
    
    public MapBoxModel()
    {
       System.out.println("MapBoxModel");
    }
    
    public int getXBlockCount(){
        return xBlockCount;
    }
    public int getYBlockCount(){
        return yBlockCount;
    }
    
    public void setXBlockCount(int x){
        System.out.println("MapBoxModel.setXBlockCount()");
        xBlockCount = x;
    }
    public void setYBlockCount(int y){
        System.out.println("MapBoxModel.setYBlockCount()");
        yBlockCount = y;
    }
}
