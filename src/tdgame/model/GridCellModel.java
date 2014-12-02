/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdgame.model;

import java.awt.Rectangle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import tdgame.view.PlayScreenView;

/**
 * This is model for Grid Cells Module.
 *
 * @author Rahul K Kikani
 */
public class GridCellModel extends Rectangle {

    TowerModel[] towers;
    //private Rectangle[] towerRange;
    public String[] towerLog;
    public int[] towerCKilled;

    private int towerRangeSize = 100;
    private int gID;
    private int airID;
    private int loseTime = 100, loseFrame = 0;

    private int shotMob = -1;
    private int[] MobList = new int[100];
    private boolean freeze = false;
    private boolean fire = false;
    private boolean firing = false;

    private int xC = 0, yC = 0;

    private String startTime;
    private boolean startFlag = false;
    private String endTime;

    /**
     * * This is constructor method for Grid Cell. It will set different
     * properties for each grid cell.
     *
     * @param x x point
     * @param y y point
     * @param width width of cell
     * @param height height of cell
     * @param gId ground id of cell
     * @param airId air id of cell
     */
    GridCellModel(int x, int y, int width, int height, int gId, int airId) {
        Arrays.fill(MobList, 0);
        setBounds(x, y, width, height);
        towers = new TowerModel[configModel.airTowerLaser.length];

        towerLog = new String[configModel.airTowerLaser.length];
        towerCKilled = new int[configModel.airTowerLaser.length];
        
        for (int i = 0; i < configModel.airTowerLaser.length; i++) {
            towers[i] = new TowerModel();
            towers[i].towerRange = new Rectangle(x - ((configModel.airTowerRanger[i]) / 2), y - ((configModel.airTowerRanger[i]) / 2), width + configModel.airTowerRanger[i], height + configModel.airTowerRanger[i]);
            towerCKilled[i] = 0;
        }
        this.gID = gID;
        this.airID = airID;
        //xC = (x/44);
        //yC = (y/44);
    }

    public void physic(CreatureModel[] cModel) {
        
        shotMob = towers[gID].getShotMobID(this, cModel);

//        for (int i = 0; i < configModel.airTowerLaser.length; i++) {
//            if (getShotMob() != -1 && getTowerRange()[gID].intersects(cModel[getShotMob()])) {
//                setFiring(true);
//            } else {
//                setFiring(false);
//            }
//        }
//        for (int tid = 0; tid < configModel.airTowerLaser.length; tid++) {
//            if (airID == 5) {
//                for (int i = 0; i < cModel.length; i++) {
//                    if (cModel[i].isInGame()) {
//                        if (getTowerRange()[tid].contains(cModel[i])) {
//                            setFiring(false);
//                        }
//                    }
//                }
//            }
//        }
//        if (!isFiring()) {
//            for (int tid = 0; tid < configModel.airTowerLaser.length; tid++) {
//                if (airID == configModel.airTowerLaser[tid]) {
//                    for (int i = 0; i < cModel.length; i++) {
//                        if (cModel[i].isInGame()) {
//                            if (getTowerRange()[tid].intersects(cModel[i])) {
//                                setFiring(true);
//                                setShotMob(i);
//                            }
//                        }
//                    }
//                }
//            }
//        }

        if (getShotMob() != -1 && isFiring() && getAirID() != -1) {
            if (loseFrame >= loseTime) {
                if (getAirID() != 5) {
                    //System.out.println("Air Id: "+getAirID()+" Rate:"+configModel.TowerFiringRate[getAirID()-3]);
                    cModel[getShotMob()].loseHealth(configModel.TowerFiringRate[getAirID() - 3]);
                } else if (getAirID() == 5) {
                    System.out.println("Mob " + getShotMob() + " Freezed : "+cModel[getShotMob()].walkSpeed);
                    if (cModel[getShotMob()].walkSpeed < 20 + configModel.TowerFiringRate[getAirID() - 3]) {
                        cModel[getShotMob()].walkSpeed++;
                        if (!cModel[getShotMob()].isFreezed()) {
                            cModel[getShotMob()].setFreezed(true);
                            cModel[getShotMob()].setTimeNow(System.currentTimeMillis());
                            
                        }
                    } else {
                        setFiring(false);
                        setShotMob(-1);
                    }
                }
                loseFrame = 0;
            } else {
                loseFrame += 1;
            }
            if (getShotMob() != -1 && cModel[getShotMob()].isDead()) {
                towerCKilled[getAirID() - 3]++;
                setFiring(false);
                setShotMob(-1);
                PlayScreenView.hasWon();
            }
        }

        for (int i = 0; i < cModel.length; i++) {
            if (cModel[i].isFreezed()) {
                if (cModel[i].getTimeNow() + (1000 * configModel.TowerLevel[2]) < System.currentTimeMillis()) {
                    System.out.println("Mob " + i + " unFreezed");
                    cModel[i].setFreezed(false);
                }
            }
        }
    }

    /**
     * @param gID the gID to set
     */
    public void setgID(int gID) {
        this.gID = gID;
    }

    /**
     * @param airID the airID to set
     */
    public void setAirID(int airID) {
        this.airID = airID;
    }

    /**
     * @return the gID
     */
    public int getgID() {
        return gID;
    }

    /**
     * @return the airID
     */
    public int getAirID() {
        return airID;
    }

    /**
     * @return the towerRange
     * @param x x index
     */
    public Rectangle getTowerRange(int x) {
        return this.towers[x].towerRange;
    }

    public void getMoney(int mobID) {
        System.out.println("Money Is increased");
        configModel.money += configModel.deathReward[0];
    }

    /**
     * @return the shotMob
     */
    public int getShotMob() {
        return shotMob;
    }

    /**
     * @return the firing
     */
    public boolean isFiring() {
        return firing;
    }

    /**
     * @param firing the firing to set
     */
    public void setFiring(boolean firing) {
        this.firing = firing;
    }


    /**
     * @param towerRange the towerRange to set
     * @param i the towerRange ID
     */
    public void setTowerRange(int i, Rectangle towerRange) {
        this.towers[i].towerRange = towerRange;
    }

    public String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    /**
     * @param shotMob the shotMob to set
     */
    public void setShotMob(int shotMob) {
        this.shotMob = shotMob;
    }
}
