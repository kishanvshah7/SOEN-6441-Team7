/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Color;
import java.awt.Graphics;
import tdgame.model.CreatureModel;
import tdgame.model.configModel;

/**
 *
 * @author Rahul K Kikani
 */
public class CreatureView {
    /**
     * Draw function for creature's view
     * @param cModel CreatureModel object
     * @param mobID Creature ID
     * @param g Graphics object
     * @return successFlag
     */
    public boolean draw(CreatureModel cModel,int mobID, Graphics g){
        if(g != null){
            g.drawImage(configModel.tileset_mob[0], cModel.x, cModel.y, cModel.width, cModel.height, null);

            //Health Bar
            g.setColor(new Color(180, 50, 50));
            g.fillRect(cModel.x + cModel.getMobSize(), cModel.y, cModel.getHealthWidth(), cModel.height);

            g.setColor(new Color(50, 180, 50));
            g.fillRect(cModel.x + cModel.getMobSize(), cModel.y, cModel.getHealthWidth(), cModel.getHealthHeight());


            g.setColor(Color.BLACK);
            g.drawRect(cModel.x + cModel.getMobSize() - 1, cModel.y - 1, cModel.getHealthWidth(), cModel.height);

            if(cModel.walkSpeed > 20){
                g.drawImage(configModel.ice[0], cModel.x, cModel.y, cModel.width, cModel.height, null);
            }
            return true;
        } else {
            return false;
        }
    }
}
