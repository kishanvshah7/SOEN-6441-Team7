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
 *
 * @author Rahul K Kikani
 */
public class ShopController {

    static CellContainerController ccCont;
    static ShopModel sModel;
    ShopView sView;
    
    ShopController(ShopModel sModel, ShopView sView) {
        this.sModel = sModel;
        this.sView = sView;
    }
    
    public void setccCont(CellContainerController ccCont) {
        this.ccCont = ccCont;
    }
    
    public void getshopDraw(ShopModel sModel, Graphics g){
        this.sView.draw(sModel, g);
    }
    
    public static void click(int button) {
        
        if(button == 1){
            for(int i=0;i<sModel.getbuttonLength();i++){
                if(sModel.getButtonObj(i).contains(configModel.mse)){
                    if(sModel.getButtonId(i) != configModel.airAir)
                    {
                        if(sModel.getMoney() >= sModel.getButtonPrice(i)){
                            sModel.setHeldID(sModel.getButtonId(i));
                            sModel.setRealID(i);
                            sModel.setHoldsItem(true);
                        }
                    }
                }
            }
            
            if(sModel.isHoldsItem()){
                if(sModel.getMoney() >= sModel.getButtonPrice(sModel.getRealID())){
                    for(int y=0;y<ccCont.getyC();y++){
                        for(int x=0;x<ccCont.getxC();x++){
                            if(ccCont.getgcModelObj(y, x).contains(configModel.mse)){
                                if(ccCont.getgcModelObj(y, x).gID != configModel.groundRoad && ccCont.getgcModelObj(y, x).airID == configModel.airAir){
                                    ccCont.getgcModelObj(y, x).airID = sModel.getHeldID();
                                    sModel.setMoney(sModel.getMoney() - sModel.getButtonPrice(sModel.getRealID()));
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
                                    if(ccCont.getgcModelObj(y, x).airID == configModel.airTowerLaser[i]){
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
                                    if(ccCont.getgcModelObj(y, x).airID == configModel.airTowerLaser[i]){
                                        ccCont.getgcModelObj(y, x).airID = -1;
                                        //ccCont.getgcModelObj(y, x).airID = sModel.getHeldID();
                                        sModel.setMoney(sModel.getMoney() + sModel.getButtonPrice(i));
                                    }
                                }
                            }
                        }
                    }
        }
    }

    
}
