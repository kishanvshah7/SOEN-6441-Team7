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
 * Context for Strategy pattern
 * @author Rahul K Kikani
 */
public class Context {

    private Strategy strategy;

    /**
     * Strategy initiator
     *
     * @param strategy strategy object
     */
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Targeting Strategy Algorithm
     *
     * @param tm Tower Object
     * @param gcModel Grid Model Object
     * @param cModel Creatures Object
     * @return Creatures Id for targeting
     */
    public int executeStrategy(TowerModel tm, GridCellModel gcModel, CreatureModel[] cModel) {
        return strategy.doOperation(tm, gcModel, cModel);
    }
}
