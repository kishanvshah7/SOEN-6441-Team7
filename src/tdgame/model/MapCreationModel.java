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
public class MapCreationModel {
    
    int xBlockCount = 0;
    int yBlockCount = 0;
    
    public void setXBlockCount(int x){
        xBlockCount = x;
    }
    public void setYBlockCount(int y){
        yBlockCount = y;
    }
    public int getXBlockCount(){
        return xBlockCount;
    }
    public int getYBlockCount(){
        return yBlockCount;
    }
}
