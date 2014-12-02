/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StrategyPattern;

import tdgame.model.CreatureModel;
import tdgame.model.GridCellModel;
import tdgame.model.TowerModel;

/**
 *
 * @author Rahul K Kikani
 */
public class MaxHealth implements Strategy {

    @Override
    public int doOperation(TowerModel tm, GridCellModel gcModel, CreatureModel[] cModel) {
        int temp_health = 0;
        int shot_id = -1;
        for (int i = 0; i < cModel.length; i++) {
            if (cModel[i].isInGame()) {
                if (tm.towerRange.intersects(cModel[i])) {
                    gcModel.setFiring(true);
                    //System.out.println("Firing Start:3");
                    if (cModel[i].getHealth() >= temp_health) {
                        shot_id = i;
                        temp_health = cModel[i].getHealth();
                    }
                }
            }
        }
        return shot_id;
    }
}
