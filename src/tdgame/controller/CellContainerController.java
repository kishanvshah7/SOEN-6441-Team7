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
    private GridCellModel[][] cgModel;
    private GridCellController gcCont;
    public CellContainerController() {
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

    public void setcGModel(GridCellModel[][] cgModel) {
        this.cgModel = cgModel;
    }

    public void setgcCont(GridCellController gcCont) {
        this.gcCont = gcCont;
    }
    
    public GridCellController getgcCont() {
        return gcCont;
    }
    
    public void getDraw(Graphics g){
        if(theView != null)
            theView.draw(g);
    }
}
