/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import sun.font.CreatedFontTracker;
import tdgame.model.CreatureModel;
import tdgame.view.CreatureView;

/**
 *
 * @author Rahul K Kikani
 */
public class CreatureController {

    CreatureModel[] cModel;
    CreatureView cView;
    
    CreatureController(CreatureModel[] cModel, CreatureView cView) {
        this.cModel = new CreatureModel[cModel.length];
        this.cModel = cModel;
        this.cView = cView;
    }
    
    public void createCreatures(){
        
    }
}
