/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.Graphics;
import tdgame.model.CellContainerModel;
import tdgame.model.GridCellModel;
import tdgame.view.CellContainerView;

/**
 *
 * @author Rahul K Kikani
 */
public class CellContainerController {

    CellContainerModel theModel;
    CellContainerView theView;
    private GridCellModel[][] gcModel;
    private GridCellController gcCont;
    public CellContainerController(CellContainerView ccView, CellContainerModel ccModel) {
        this.theModel = ccModel;
        this.theView = ccView;
    }
    
    public void setCCView(CellContainerView ccView){
        theView = ccView;
    }
    
    public void setCCModel(CellContainerModel ccModel){
        theModel = ccModel;
    }
    
    public CellContainerView getCCView(){
        return theView;
    }
    
    public CellContainerModel getCCModel(){
        return theModel;
    }
    
    /**
     * @return the xC
     */
    public int getxC() {
        return theModel.getxC();
    }

    /**
     * @param xC the xC to set
     */
    public void setxC(int xC) {
        theModel.setxC(xC);
    }

    /**
     * @return the yC
     */
    public int getyC() {
        return theModel.getyC();
    }

    /**
     * @param yC the yC to set
     */
    public void setyC(int yC) {
        theModel.setyC(yC);
    }

    public void setgCModel(GridCellModel[][] gcModel) {
        this.gcModel = gcModel;
    }

    public void setgcCont(GridCellController gcCont) {
        this.gcCont = gcCont;
        this.gcModel = gcCont.theModel;
    }
    
    public GridCellController getgcCont() {
        return gcCont;
    }
    
    public GridCellModel getgcModelObj(int y, int x){
            return theModel.getGcModelObj(y, x);
    }
    
    public void getDraw(Graphics g){
        if(theView != null)
            theView.draw(g);
    }
}
