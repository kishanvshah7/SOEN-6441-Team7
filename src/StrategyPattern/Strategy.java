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
 * Strategy Interface
 * @author Rahul K Kikani
 */
public interface Strategy {
   public int doOperation(TowerModel tm, GridCellModel gcModel, CreatureModel[] cModel);
}