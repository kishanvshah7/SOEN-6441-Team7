/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.ParseException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import tdgame.model.ShopModel;
import tdgame.model.configModel;
import tdgame.view.ShopView;
import towerdefensegame.LogGenerator;

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
    
    /**
     * Place tower function
     * @param y y Co-ordinate
     * @param x x Co-ordinate
     * @param priceID price of towerID
     * @return successFlag
     */
    public boolean placeTower(int y, int x, int priceID){
        if(ccCont.getgcModelObj(y, x).getgID() != 11 && ccCont.getgcModelObj(y, x).getgID() != configModel.groundRoad && ccCont.getgcModelObj(y, x).getAirID() == configModel.airAir){
            ccCont.getgcModelObj(y, x).setAirID(sModel.getHeldID());
            configModel.money = configModel.money - sModel.getButtonPrice(priceID);
            System.out.println("Tower Placed"+sModel.getHeldID());
            LogGenerator.addLog("Tower Id:"+sModel.getHeldID()+" placed at ("+y+","+x+")");
            ccCont.getgcModelObj(y, x).towerLog[sModel.getHeldID()-3] = LogGenerator.getLogTime()+"Tower Placed at ("+y+","+x+")\n";
            ccCont.getgcModelObj(y, x).setTowerRange(sModel.getTowerID(), new Rectangle(ccCont.getgcModelObj(y, x).x - ((configModel.airTowerRanger[sModel.getTowerID()])/2), ccCont.getgcModelObj(y, x).y - ((configModel.airTowerRanger[sModel.getTowerID()])/2), ccCont.getgcModelObj(y, x).width + configModel.airTowerRanger[sModel.getTowerID()], ccCont.getgcModelObj(y, x).height + configModel.airTowerRanger[sModel.getTowerID()]));
            sModel.setHoldsItem(false);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Remove tower function
     * @param y y Co-ordinate
     * @param x x Co-ordinate
     * @return successFlag
     */
    public boolean removeTower(int y, int x){
        for(int i=0;i<configModel.airTowerLaser.length;i++){
            if(ccCont.getgcModelObj(y, x).getAirID() == configModel.airTowerLaser[i]){
                    ccCont.getgcModelObj(y, x).setFiring(false);
                    ccCont.getgcModelObj(y, x).setAirID(-1);
                    ccCont.getgcModelObj(y, x).setgID(0);
                    double refund_amount = sModel.getButtonPrice(i) * 0.8;
                    sModel.setMoney(configModel.money + (int)refund_amount );
                    configModel.money = configModel.money + (int)refund_amount;
                    ccCont.getgcModelObj(y, x).towerLog[i] += LogGenerator.getLogTime()+"Tower Removed from ("+y+","+x+")\n";
                    LogGenerator.addLog("Tower Id:"+sModel.getHeldID()+" removed from ("+y+","+x+")");
                    LogGenerator.addLog("Tower Sell reward done.");
                    try {
                        ccCont.getgcModelObj(y, x).endTime();
                    } catch (ParseException ex) {
                        Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return true;
                }
        }
        return false;
    }
    
    /**
     * Is tower here function
     * @param y y Co-ordinate
     * @param x x Co-ordinate
     * @return successFlag
     */
    public boolean isTowerHere(int y, int x){
        for(int i=0;i<configModel.airTowerLaser.length;i++){
            System.out.println("Tower Id: "+ccCont.getgcModelObj(y, x).getAirID()+"-"+configModel.airTowerLaser[i]);
            if(ccCont.getgcModelObj(y, x).getAirID() == configModel.airTowerLaser[i]){
                System.out.println("Tower Is Here");
                ccCont.getgcModelObj(y, x).towerLog[i] += LogGenerator.getLogTime()+"Tower Inspection window opened.\n";
                LogGenerator.addLog("Tower Inspection window showed for Tower:"+configModel.airTowerLaser[i]+" at ("+y+","+x+")");
                sModel.setTowerID(i);
                sModel.setTowerInfo(true);
                return true;
            } else {
                sModel.setTowerInfo(false);
            }
        }
        return false;
    }
    
    /**
     * Observer Update Method
     * @param o observer Object
     * @param arg arguments
     */
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
            
            if(sModel.towerUpgrade.contains(configModel.mse)){
                if(sModel.isTowerInfo()){
                    System.out.println("Tower Info");
                    if(configModel.TowerLevel[sModel.getTowerID()] < 5){
                        if(configModel.money >= configModel.TowerPrice[sModel.getTowerID()]){
                            System.out.println("Tower Upgrade: "+sModel.getTowerID());
                            
                            if(sModel.getTowerID() == 2){
                                configModel.TowerFiringRate[sModel.getTowerID()] += 5;
                            } else {
                                configModel.TowerFiringRate[sModel.getTowerID()] += 1;
                            }
                            
                            configModel.airTowerRanger[sModel.getTowerID()] += 20;
                            configModel.TowerLevel[sModel.getTowerID()]++;
                            configModel.money -= configModel.TowerPrice[sModel.getTowerID()];
                            
                            LogGenerator.addLog("Tower Upgrade to Level:"+configModel.TowerLevel[sModel.getTowerID()]+
                                                " Range:" +configModel.airTowerRanger[sModel.getTowerID()]+
                                                " Rate:" +configModel.TowerFiringRate[sModel.getTowerID()]);
                            LogGenerator.addLog("Tower Upgrade cost deducted from total money.");
                            for(int y=0;y<ccCont.getyC();y++){
                                for(int x=0;x<ccCont.getxC();x++){
                                    if(ccCont.getgcModelObj(y, x).getAirID() == (sModel.getTowerID()+3)){
                                        ccCont.getgcModelObj(y, x).setTowerRange(sModel.getTowerID(), new Rectangle(ccCont.getgcModelObj(y, x).x - ((configModel.airTowerRanger[sModel.getTowerID()])/2), ccCont.getgcModelObj(y, x).y - ((configModel.airTowerRanger[sModel.getTowerID()])/2), ccCont.getgcModelObj(y, x).width + configModel.airTowerRanger[sModel.getTowerID()], ccCont.getgcModelObj(y, x).height + configModel.airTowerRanger[sModel.getTowerID()]));
                                        ccCont.getgcModelObj(y, x).towerLog[sModel.getTowerID()] += LogGenerator.getLogTime()+"Tower Upgrade to Level:"+configModel.TowerLevel[sModel.getTowerID()]+
                                                " Range:" +configModel.airTowerRanger[sModel.getTowerID()]+
                                                " Rate:" +configModel.TowerFiringRate[sModel.getTowerID()]+"\n";
                                    }
                                }
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
                                    removeTower(y, x);
                                }
                            }
                        }
            }
        }
    }

    
}
