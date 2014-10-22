/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Rahul K Kikani
 */
public class MapBoxView extends JPanel {

    public MapBoxView(){
        System.out.println("MapBoxView");
    }
    public MapBoxView(int xC, int yC) {
      GridBagLayout layout = new GridBagLayout();

      this.setLayout(layout);        
      GridBagConstraints gbc = new GridBagConstraints();

      for(int x=0;x<xC;x++){
          gbc.fill = GridBagConstraints.HORIZONTAL;
          for(int y=0;y<yC;y++){
                gbc.gridx = x;
                gbc.gridy = y;
                this.add(new JButton("NA"),gbc);
          }
      }
    }
}
