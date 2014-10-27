/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.awt.Rectangle;

/**
 *
 * @author Rahul K Kikani
 */
public class ShopModel {
    
    private int health = 5;
    private int money = 150;
    
    private int startX = 0;
    private int startY = 0;
    
    public static int shopWidth = 4;
    public static int buttonSize = 52;
    public static int cellSpace = 3;
    public static int iconSize = 20;
    public static int icon_space = 30;
    public static int itemIn = 4;
    private static int heldID = -1;
    private static int realID = -1;
    public static int[] buttonID = {configModel.airTowerLaser[0], configModel.airTowerLaser[1], configModel.airTowerLaser[2], configModel.airTowerLaser[3]};
    private static int[] buttonPrice = {10, 20, 30, 40};
    
    public Rectangle[] button  = new Rectangle[shopWidth];
    public Rectangle btn_health;
    public Rectangle btn_coins;
    
    private boolean holdsItem = false;
    
    private int towerID = 0;
    private boolean towerInfo = false;
    
    public ShopModel(int startX, int startY){
        this.startX = startX;
        this.startY = startY;
        define();
    }
    
    public void define(){
        for(int i=0;i<button.length;i++){
            button[i] = new Rectangle( startX +(i * (cellSpace+buttonSize)), startY+buttonSize+cellSpace, buttonSize, buttonSize);
        }
        btn_health = new Rectangle(startX, 14, iconSize, iconSize);
        btn_coins = new Rectangle(startX + button[0].y + 60, 14, iconSize, iconSize);
    }
    
    public int getbuttonLength(){
        return button.length;
    }
    
    public int getButtonId(int x){
        return buttonID[x];
    }
    
    public Rectangle getButtonObj(int x){
        return button[x];
    }

    /**
     * @return the holdsItem
     */
    public boolean isHoldsItem() {
        return holdsItem;
    }

    /**
     * @param holdsItem the holdsItem to set
     */
    public void setHoldsItem(boolean holdsItem) {
        this.holdsItem = holdsItem;
    }
    
        /**
     * @return the heldID
     */
    public int getHeldID() {
        return heldID;
    }

    /**
     * @param aHeldID the heldID to set
     */
    public void setHeldID(int aHeldID) {
        heldID = aHeldID;
    }

    /**
     * @return the realID
     */
    public int getRealID() {
        return realID;
    }

    /**
     * @param aRealID the realID to set
     */
    public void setRealID(int aRealID) {
        realID = aRealID;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }
    
    /**
     * @return the buttonPrice
     */
    public int getButtonPrice(int i) {
        return buttonPrice[i];
    }

    /**
     * @param aButtonPrice the buttonPrice to set
     */
    public void setButtonPrice(int i, int aButtonPrice) {
        buttonPrice[i] = aButtonPrice;
    }

    /**
     * @return the towerInfo
     */
    public boolean isTowerInfo() {
        return towerInfo;
    }

    /**
     * @param towerInfo the towerInfo to set
     */
    public void setTowerInfo(boolean towerInfo) {
        this.towerInfo = towerInfo;
    }

    /**
     * @return the towerID
     */
    public int getTowerID() {
        return towerID;
    }

    /**
     * @param towerID the towerID to set
     */
    public void setTowerID(int towerID) {
        this.towerID = towerID;
    }

    /**
     * @param startX the startX to set
     */
    public void setStartX(int startX) {
        this.startX = startX;
    }

    /**
     * @param startY the startY to set
     */
    public void setStartY(int startY) {
        this.startY = startY;
    }
}
