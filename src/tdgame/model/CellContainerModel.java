/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import tdgame.controller.GridCellController;

/**
 *
 * @author Rahul K Kikani
 */
public class CellContainerModel {
    private int xC = 10;
    private int yC = 10;
    private int cellPixels = 44;
    
    private static GridCellModel[][] gcModel;
    
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
     */
    public GridCellModel getGcModelObj(int y, int x) {
        return gcModel[y][x];
    }
    
    public void setGcModelObj(int y, int x, int gVal, int airVal) {
        gcModel[y][x].gID = gVal;
        gcModel[y][x].airID = airVal;
    }
}
