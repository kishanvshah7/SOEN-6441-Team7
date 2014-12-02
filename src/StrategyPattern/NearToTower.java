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
public class NearToTower  implements Strategy{
    /**
     * Strategy Algorithm
     * @param tm Tower Object
     * @param gcModel Grid 
     * @param cModel
     * @return 
     */
   @Override
    public int doOperation(TowerModel tm, GridCellModel gcModel, CreatureModel[] cModel) {
        double temp_health = 1000;
        int shot_id = -1;
        for (int i = 0; i < cModel.length; i++) {
            if (cModel[i].isInGame()) {
                if (tm.towerRange.intersects(cModel[i])) {
                    gcModel.setFiring(true);
                    double temp_temp_health = distance((tm.towerRange.x + tm.towerRange.width/2), (tm.towerRange.y + tm.towerRange.height/2), (cModel[i].x + cModel[i].width/2), (cModel[i].y + cModel[i].height/2));
                    if (temp_temp_health <= temp_health) {
                        shot_id = i;
                        temp_health = temp_temp_health;
                    }
                }
            }
        }
        return shot_id;
    }
    
    public double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(
            (x1 - x2) *  (x1 -x2) + 
            (y1 - y2) *  (y1 - y2)
        );
    }
   
}
