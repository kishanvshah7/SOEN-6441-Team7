/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import tdgame.model.ShopModel;
import tdgame.model.configModel;
import tdgame.view.CellContainerView;
import tdgame.view.ShopView;

/**
 * This Class will bind and initialize Model-View of Shop(Tower) Module.
 * @author Rahul K Kikani
 */
public class ShopController implements Observer {

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

    public ShopController() {
        
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
        
    }
    
    public boolean placeTower(int y, int x, int priceID){
        if(ccCont.getgcModelObj(y, x).getgID() != configModel.groundRoad && ccCont.getgcModelObj(y, x).getAirID() == configModel.airAir){
            ccCont.getgcModelObj(y, x).setAirID(sModel.getHeldID());
            configModel.money = configModel.money - sModel.getButtonPrice(priceID);
            System.out.println("Tower Placed"+sModel.getHeldID());
            sModel.setHoldsItem(false);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean removeTower(int y, int x){
        for(int i=0;i<configModel.airTowerLaser.length;i++){
            if(ccCont.getgcModelObj(y, x).getAirID() == configModel.airTowerLaser[i]){
                if(ccCont.getgcModelObj(y, x).getAirID() == configModel.airTowerLaser[i]){
                    ccCont.getgcModelObj(y, x).setFiring(false);
                    ccCont.getgcModelObj(y, x).setAirID(-1);
                    //ccCont.getgcModelObj(y, x).setgID(0);
                    double refund_amount = sModel.getButtonPrice(i) * 0.8;
                    sModel.setMoney(configModel.money + (int)refund_amount );
                    configModel.money = configModel.money + (int)refund_amount;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    
    public boolean isTowerHere(int y, int x){
        for(int i=0;i<configModel.airTowerLaser.length;i++){
            if(ccCont.getgcModelObj(y, x).getAirID() == configModel.airTowerLaser[i]){
                System.out.println("Tower Is Here");
                sModel.setTowerID(i);
                sModel.setTowerInfo(true);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    public void update(Observable o, Object arg) {
        //System.out.println("Bagha");
         if (arg instanceof String) {
            int button = Integer.parseInt((String)arg);
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
                                    placeTower(y, x, sModel.getRealID());
                                }
                            }
                        }
                    }
                }
                        for(int y=0;y<ccCont.getyC();y++){
                            for(int x=0;x<ccCont.getxC();x++){
                                if(ccCont.getgcModelObj(y, x).contains(configModel.mse)){
                                    isTowerHere(y, x);
                                }
                            }
                        }
            }else if(button == 0 && sModel.isHoldsItem()){
                sModel.setHoldsItem(false);
            } else if(button == 0 && !sModel.isHoldsItem()){
                    for(int y=0;y<ccCont.getyC();y++){
                            for(int x=0;x<ccCont.getxC();x++){
                                if(ccCont.getgcModelObj(y, x).contains(configModel.mse)){
                                    removeTower(y, x);
                                }
                            }
                        }
            }
        }
    }

    
}
