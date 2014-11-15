/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.Graphics;
import tdgame.model.ShopModel;
import tdgame.model.configModel;
import tdgame.view.CellContainerView;
import tdgame.view.ShopView;

/**
 * This Class will bind and initialize Model-View of Shop(Tower) Module.
 * @author Rahul K Kikani
 */
public class ShopController {

    static CellContainerController ccCont;
    static ShopModel sModel;
    ShopView sView;
    
    /**
     * This method is constructor for Shop(Tower)
     * @param sModel the shopModel
     * @param sView the shopView
     */
    ShopController(ShopModel sModel, ShopView sView) {
        this.sModel = sModel;
        this.sView = sView;
    }
    
    /**
     * This method will set CellContainerController object.
     * @param ccCont the CellContainerController
     */
    public void setccCont(CellContainerController ccCont) {
        this.ccCont = ccCont;
    }
    
    /**
     * THis method will call draw method from draw().
     * @param sModel the showModel
     * @param g the Graphics
     */
    public void getshopDraw(ShopModel sModel, Graphics g){
        this.sView.draw(sModel, g);
    }
    
    /**
     * This method will execute by user pressed button. It also used to place tower and delete tower.
     * @param button the mouse button id.
     */
    public static void click(int button) {
        
        if(button == 1){
            for(int i=0;i<sModel.getbuttonLength();i++){
                if(sModel.getButtonObj(i).contains(configModel.mse)){
                    if(sModel.getButtonId(i) != configModel.airAir)
                    {
                        if(configModel.money >= sModel.getButtonPrice(i)){
                            sModel.setHeldID(sModel.getButtonId(i));
                            sModel.setRealID(i);
                            sModel.setHoldsItem(true);
                        }
                    }
                }
            }
            
            if(sModel.isHoldsItem()){
                if(configModel.money >= sModel.getButtonPrice(sModel.getRealID())){
                    for(int y=0;y<ccCont.getyC();y++){
                        for(int x=0;x<ccCont.getxC();x++){
                            if(ccCont.getgcModelObj(y, x).contains(configModel.mse)){
                                if(ccCont.getgcModelObj(y, x).getgID() != configModel.groundRoad && ccCont.getgcModelObj(y, x).getAirID() == configModel.airAir){
                                    ccCont.getgcModelObj(y, x).setAirID(sModel.getHeldID());
                                    configModel.money = configModel.money - sModel.getButtonPrice(sModel.getRealID());
                                    System.out.println("Tower Placed"+sModel.getHeldID());
                                    sModel.setHoldsItem(false);
                                }
                            }
                        }
                    }
                }
            }
                    for(int y=0;y<ccCont.getyC();y++){
                        for(int x=0;x<ccCont.getxC();x++){
                            if(ccCont.getgcModelObj(y, x).contains(configModel.mse)){
                                for(int i=0;i<configModel.airTowerLaser.length;i++){
                                    if(ccCont.getgcModelObj(y, x).getAirID() == configModel.airTowerLaser[i]){
                                        System.out.println("Tower Is Here");
                                        sModel.setTowerID(i);
                                        sModel.setTowerInfo(true);
                                    }
                                }
                            }
                        }
                    }
        }else if(button == 0 && sModel.isHoldsItem()){
            sModel.setHoldsItem(false);
        } else if(button == 0 && !sModel.isHoldsItem()){
                for(int y=0;y<ccCont.getyC();y++){
                        for(int x=0;x<ccCont.getxC();x++){
                            if(ccCont.getgcModelObj(y, x).contains(configModel.mse)){
                                for(int i=0;i<configModel.airTowerLaser.length;i++){
                                    if(ccCont.getgcModelObj(y, x).getAirID() == configModel.airTowerLaser[i]){
                                        System.out.println("Firing: "+ccCont.getgcModelObj(y, x).isFiring());
                                        ccCont.getgcModelObj(y, x).setFiring(false);
                                        System.out.println("Firing2: "+ccCont.getgcModelObj(y, x).isFiring());
                                        ccCont.getgcModelObj(y, x).setAirID(-1);
                                        //ccCont.getgcModelObj(y, x).airID = sModel.getHeldID();
                                        double refund_amount = sModel.getButtonPrice(i) * 0.8;
                                        sModel.setMoney(configModel.money + (int)refund_amount );
                                        configModel.money = configModel.money + (int)refund_amount;
                                    }
                                }
                            }
                        }
                    }
        }
    }

    
}
