/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.Graphics;
import tdgame.model.CellContainerModel;
import tdgame.model.GridCellModel;
import tdgame.model.PlayScreenModel;
import tdgame.model.ShopModel;
import tdgame.view.CellContainerView;
import tdgame.view.GridCellView;
import tdgame.view.PlayScreenView;
import tdgame.view.ShopView;

/**
 * This Class will bind and initialize Model-View of Play Screen Module.
 * @author Rahul K Kikani
 */
public class PlayScreenController {

    PlayScreenModel theModel;
    PlayScreenView theView;
    
    CellContainerModel ccModel;
    CellContainerView ccView;
    CellContainerController ccCont;
    
    GridCellModel[][] gcModel;
    GridCellView gcView;
    GridCellController gcCont;
    
    ShopModel sModel;
    ShopView sView;
    ShopController sCont;
    
    /**
     * This method is constructor for Play Screen Controller. It will initialize different views,model and controller.
     * @param psView the PlayScreenView
     * @param psModel the PlayScreenModel
     * @param gcView the GridCellView
     * @param gcModel the GridCellModel
     * @param ccView the CellContainerView
     * @param ccModel the CellContainerModel
     * @param sView the ShopView
     * @param sModel the ShopModel
     */
    
    public PlayScreenController(PlayScreenView psView, PlayScreenModel psModel, GridCellView gcView, GridCellModel[][] gcModel, CellContainerView ccView, CellContainerModel ccModel, ShopView sView, ShopModel sModel) {
        theModel = psModel;
        theView = psView;
        
        this.gcModel = gcModel;
        this.gcView = gcView;
        this.gcCont = new GridCellController(this.gcView,this.gcModel);
        
        this.ccView = ccView;
        this.ccModel = ccModel;
        this.ccCont = new CellContainerController(this.ccView,this.ccModel);
        this.ccCont.setgcCont(gcCont);
        
        this.ccView.setCcCont(ccCont);
        this.ccCont.setgcCont(gcCont);
        ccModel = theModel.getCellContainerModel();
        
        this.sModel = sModel;
        this.sView = sView;
        sCont = new ShopController(this.sModel,this.sView);
        sCont.setccCont(ccCont);
    }

    
    /**
     * This method will call draw() method of Cell Container Controller
     * @param g the Graphics
     */
    public void getccDraw(Graphics g){
            ccCont.getDraw(g);
    }
    /**
     * This method will call draw() method of Shop View
     * @param g the Graphics
     */
    public void getshopDraw(Graphics g){
        sCont.getshopDraw(sModel, g);
    }
}