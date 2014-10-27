/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.util.Scanner;
import tdgame.controller.CellContainerController;

/**
 *
 * @author Rahul K Kikani
 */
public class PlayScreenModel {
    
    private int xC;
    private int yC;
    
    public static int myWidth, myHeight;
    public static int money = 20, health = 50;
    public static int killed =0, killsToWin =0, Level =1, maxLevel =3;
    public static int winTime = 4000, winFrame =0;
    
    private static boolean isFirst = true;
    public static boolean isDebug = false;
    private static boolean isDead = false;
    public static boolean isWin = false;
    
    public static Image[] ground_level = new Image[100];
    public static Image[] air_level = new Image[100];
    public static Image[] tileset_res = new Image[100];
    public static Image[] tileset_mob = new Image[100];
    public static Image[] tileset_air = new Image[100];
    
    public static Point mse = new Point(0, 0);
    
    public static CellContainerModel ccModel;
    
    public int[][] gridCellArray;
    
    public PlayScreenModel(){
        System.out.println("PlayScreenModel()");
    }

    public boolean LoadMap(File file) {
        try{
            Scanner loadScanner = new Scanner(file);
            this.xC = loadScanner.nextInt();
            this.yC = loadScanner.nextInt();
            gridCellArray = new int[getyC()][getxC()];
            //System.out.println("MapCreationModel X: "+xC+" Y:"+yC);
             while(loadScanner.hasNext()){
                 Thread.sleep(100);
                 System.out.println("asdf");
                for(int y=0;y<getyC();y++){
                    for(int x=0;x<getxC();x++){
                        int val = loadScanner.nextInt();
                        gridCellArray[y][x] = val;
                        //System.out.println("XY X: "+x+" Y:"+y+" val:"+val);
                    }
                }
             }
             System.out.println("ABCXD");
            for(int y=0;y<getyC();y++){
                for(int x=0;x<getxC();x++){
                    System.out.print(gridCellArray[y][x]+" ");
                }
                System.out.println("\n");
            }
             loadScanner.close();
             return true;
         } catch(Exception e){
             System.out.println("Hey Buddy, Somtething is wrong in file.");
             return false;
         }
    }

    public void initCellContainerModel() {
        //System.out.println(""+gridCellArray.length+" "+gridCellArray[0].length);
        ccModel = new CellContainerModel(gridCellArray.length,gridCellArray[0].length);
    }
    
    public void setGridCellVal(){
        for(int y=0;y<gridCellArray.length;y++){
            for(int x=0;x<gridCellArray[0].length;x++){
                if(gridCellArray[y][x] == 7 || gridCellArray[y][x] == 8){
                    System.out.println("ExitPoint");
                    ccModel.setGcModelObj(y, x, 1, gridCellArray[y][x]);
                }
                else
                    ccModel.setGcModelObj(y, x, gridCellArray[y][x],-1);
            }
            System.out.println("\n");
        }
    }
    
    public int getStartX(){
        return ((gridCellArray[0].length * 40)) + 60;
    }
    
    public int getStartY(){
        return 0;
    }
    
    public CellContainerModel getCellContainerModel(){
        return ccModel;
    }

    /**
     * @return the xC
     */
    public int getxC() {
        return xC;
    }

    /**
     * @return the yC
     */
    public int getyC() {
        return yC;
    }
}
