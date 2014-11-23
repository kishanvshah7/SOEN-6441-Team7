/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.text.ParseException;
import tdgame.controller.GridCellController;

/**
 * This is model class of Cell Container
 * @author Rahul K Kikani
 */
public class CellContainerModel {
    private int xC = 10;
    private int yC = 10;
    private int cellPixels = 44;
    
    public static GridCellModel[][] gcModel;
    
    /**
     * this method is constructor for Cell Container Model. It will set xC and xY.
     * @param yC the y coordinate
     * @param xC the x coordinate
     */
    public CellContainerModel(int yC, int xC) {
        this.xC = xC;
        this.yC = yC;
        gcModel = new GridCellModel[yC][xC];
        
        for(int y=0;y<gcModel.length;y++){
            for(int x=0;x<gcModel[0].length;x++){
                gcModel[y][x] = new GridCellModel(x*cellPixels+10, y*cellPixels+10, cellPixels, cellPixels, configModel.groundGrass, configModel.airAir);
            }
        }
    }
    
    /**
     * this method is constructor for Cell Container Model. It will set xC and xY.
     * @param yC the y coordinate
     * @param xC the x coordinate
     * @return successFlag
     */
    public boolean setCellContainerModel(int yC, int xC){
    	this.xC = xC;
        this.yC = yC;
        gcModel = new GridCellModel[yC][xC];
        
        for(int y=0;y<gcModel.length;y++){
            for(int x=0;x<gcModel[0].length;x++){
                gcModel[y][x] = new GridCellModel(x*cellPixels+10, y*cellPixels+10, cellPixels, cellPixels, configModel.groundGrass, configModel.airAir);
            }
        }
        return true;
    }
    
    /**
     * Creation of creatures.
     * @param cModel array of creatures object
     */
    public void physic(CreatureModel[] cModel) throws ParseException{
        for(int y=0;y<gcModel.length;y++){
            for(int x=0;x<gcModel[0].length;x++){
                gcModel[y][x].physic(cModel);
            }
        }
    }

    /**
     * @return the xC
     */
    public int getxC() {
        return xC;
    }

    /**
     * @param xC the xC to set
     */
    public void setxC(int xC) {
        this.xC = xC;
    }

    /**
     * @return the yC
     */
    public int getyC() {
        return yC;
    }

    /**
     * @param yC the yC to set
     */
    public void setyC(int yC) {
        this.yC = yC;
    }

    /**
     * @return the gcModel
     */
    public GridCellModel[][] getGcModel() {
        return gcModel;
    }
    
    /**
     * @return the gcModel
     * @param x xCo-ordinates
     * @param y yCo-ordinates
     */
    public GridCellModel getGcModelObj(int y, int x) {
        return gcModel[y][x];
    }
    
    /**
     * this method will set gid and airId for gcModel object
     * @param x xCo-ordinates
     * @param y yCo-ordinates
     * @param gVal the gVal
     * @param airVal the airVal
     */
    public void setGcModelObj(int y, int x, int gVal, int airVal) {
        gcModel[y][x].setgID(gVal);
        gcModel[y][x].setAirID(airVal);
    }
}
