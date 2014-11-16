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
 * This is controller class for Grid Cell Container.
 * @author Rahul K Kikani
 */
public class CellContainerController {

    CellContainerModel theModel;
    CellContainerView theView;
    private GridCellModel[][] gcModel;
    private GridCellController gcCont;
    /**
     * This method will initialize and bind View and model of Grid Cell Container moduel.
     * @param ccView the view object of Cell Container
     * @param ccModel the model object of Cell Container.
     */
    public CellContainerController(CellContainerView ccView, CellContainerModel ccModel) {
        this.theModel = ccModel;
        this.theView = ccView;
    }
    
    /**
     * this method will set view object.
     * @param ccView the ccView to set
     */
    public void setCCView(CellContainerView ccView){
        theView = ccView;
    }
    
    /**
     * this method will set model object.
     * @param ccModel  the ccModel to set
     */
    public void setCCModel(CellContainerModel ccModel){
        theModel = ccModel;
    }
    
    /**
     * this method will return view object.
     * @return the view object
     */
    public CellContainerView getCCView(){
        return theView;
    }
    
    /**
     * this method will return model object.
     * @return the model object
     */
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

    /**
     * this method will set model object of Grid Cell Model.
     * @param gcModel  the gcModel to set
     */
    public void setgCModel(GridCellModel[][] gcModel) {
        this.gcModel = gcModel;
    }

    /**
     * this method will set Grid Cell Controller object.
     * @param gcCont  the Grid Cell Controller object
     */
    public void setgcCont(GridCellController gcCont) {
        this.gcCont = gcCont;
        this.gcModel = gcCont.theModel;
    }
    
    /**
     * this method will return Grid Cell Controller object.
     * @return the Grid Cell Controller object
     */
    public GridCellController getgcCont() {
        return gcCont;
    }
    
    /**
     * this method will return Grid Cell Controller object based on x and y.
     * @return the Grid Cell Controller object
     * @param y the y coordinate
     * @param x the x coordinate
     */
    public GridCellModel getgcModelObj(int y, int x){
            return theModel.getGcModelObj(y, x);
    }
    
    /**
     * this method will call draw() from view class
     * @param g the Graphics
     */
    public void getDraw(Graphics g){
        if(theView != null)
            theView.draw(g);
    }
}
