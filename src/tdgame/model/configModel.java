/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.awt.Image;
import java.awt.Point;
/**
 * This is Default Value Model. To load Different images, tower properties, game levels.
 * @author Rahul K Kikani
 */
public class configModel {
    
    public static Point mse = new Point(0, 0);
    
    public static int groundGrass = 0;
    public static int groundRoad = 1;
    
    public static int airAir = -1;
    public static int airCave = 8;
    
    public static int removeTower = 11;
    public static int[] airTowerLaser  = new int[]{3,4,5,6};
    public static int[] airTowerRanger  = new int[]{90,135,180,225};
    public static int[] TowerFiringRate = new int[]{10,15,20,25};
    public static int[] Towerhealth = new int[]{10,15,20,25};
    
    public static int mobAir = -1;
    public static int mobGreeny = 0;
    
    public static int[] deathReward = {10};
    
    public static Image[] ground_level = new Image[100];
    public static Image[] air_level = new Image[100];
    
    public static Image[] tileset_res = new Image[100];
    
}
