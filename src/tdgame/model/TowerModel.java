/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdgame.model;

import StrategyPattern.Context;
import StrategyPattern.FirstComeFirstServe;
import StrategyPattern.MaxHealth;
import StrategyPattern.MinHealth;
import StrategyPattern.NearToTower;
import java.awt.Rectangle;

/**
 * Tower Class
 *
 * @author Rahul K Kikani
 */
public class TowerModel {

    public Rectangle towerRange;

    public int shotMob = -1;
    public int temp_health = 0;
    public int[] MobList = new int[100];

    public boolean freeze = false;
    public boolean fire = false;
    public boolean firing = false;

    /**
     * Tower constructor
     */
    public void TowerModel() {

    }

    /**
     * Creatures targeting selection
     *
     * @param gcModel grid object
     * @param cModel creatures
     * @return targeted Creature id
     */
    public int getShotMobID(GridCellModel gcModel, CreatureModel[] cModel) {

        for (int i = 0; i < configModel.airTowerLaser.length; i++) {
            if (gcModel.getShotMob() != -1 && towerRange.intersects(cModel[gcModel.getShotMob()])) {
                gcModel.setFiring(true);
                //System.out.println("Firing Start:1");
            } else {
                gcModel.setFiring(false);
            }
        }
        for (int tid = 0; tid < configModel.airTowerLaser.length; tid++) {
            if (gcModel.getAirID() == 5) {
                for (int i = 0; i < cModel.length; i++) {
                    if (cModel[i].isInGame()) {
                        if (towerRange.contains(cModel[i])) {
                            gcModel.setFiring(false);
                            //System.out.println("Firing Stop:0");
                        }
                    }
                }
            }
        }
        if (!gcModel.isFiring()) {
            for (int tid = 0; tid < configModel.airTowerLaser.length; tid++) {
                if (gcModel.getAirID() == configModel.airTowerLaser[tid]) {
                    if (configModel.TowerST[tid] == 0) {
                        Context context = new Context(new FirstComeFirstServe());
                        return context.executeStrategy(this, gcModel, cModel);
                    } else if (configModel.TowerST[tid] == 1) {
                        Context context = new Context(new MaxHealth());
                        return context.executeStrategy(this, gcModel, cModel);
                    } else if (configModel.TowerST[tid] == 2) {
                        Context context = new Context(new MinHealth());
                        return context.executeStrategy(this, gcModel, cModel);
                    } else if (configModel.TowerST[tid] == 3) {
                        Context context = new Context(new NearToTower());
                        return context.executeStrategy(this, gcModel, cModel);
                    }
                }
            }
        } else {

        }
        return -1;
    }

}
