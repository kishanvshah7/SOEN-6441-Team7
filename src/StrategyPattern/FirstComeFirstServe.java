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
public class FirstComeFirstServe implements Strategy {

    /**
     * Targeting Strategy Algorithm
     *
     * @param tm Tower Object
     * @param gcModel Grid Model Object
     * @param cModel Creatures Object
     * @return Creatures Id for targeting
     */
    @Override
    public int doOperation(TowerModel tm, GridCellModel gcModel, CreatureModel[] cModel) {
        for (int i = 0; i < cModel.length; i++) {
            if (cModel[i].isInGame()) {
                if (tm.towerRange.intersects(cModel[i])) {
                    gcModel.setFiring(true);
                    return i;
                }
            }
        }
        return -1;
    }

}
