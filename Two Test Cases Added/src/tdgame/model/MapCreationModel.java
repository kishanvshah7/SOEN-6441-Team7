/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.io.File;
import java.util.Scanner;
import tdgame.controller.MapBoxController;

/**
 *
 * @author Rahul K Kikani
 */
public class MapCreationModel {
    
    int xBlockCount = 0;
    int yBlockCount = 0;
    String FileName;
    String FilePath;
    
    public void setXBlockCount(int x){
        xBlockCount = x;
    }
    public void setYBlockCount(int y){
        yBlockCount = y;
    }
    public int getXBlockCount(){
        return xBlockCount;
    }
    public int getYBlockCount(){
        return yBlockCount;
    }
    
    public boolean readFile(MapBoxController mbCon, File path){
        System.out.println("Selected FilePath: "+path);
        
        try{
            Scanner loadScanner = new Scanner(path);
            int xC = loadScanner.nextInt();
            int yC = loadScanner.nextInt();
            System.out.println("MapCreationModel X: "+xC+" Y:"+yC);
             while(loadScanner.hasNext()){
                mbCon.setXBlockCount(xC);
                mbCon.setYBlockCount(yC);
                mbCon.setGridArray();
                 Thread.sleep(100);
                 System.out.println("asdf");
                for(int y=0;y<yC;y++){
                    for(int x=0;x<xC;x++){
                        int val = loadScanner.nextInt();
                        mbCon.setmapGirdArrayElementF(y, x, val);
                        System.out.println("XY X: "+x+" Y:"+y+" val:"+val);
                    }
                }
             }
             System.out.println("ABCXD");
            for(int y=0;y<yC;y++){
                for(int x=0;x<xC;x++){
                    System.out.print(mbCon.getmapGirdArrayElement(y, x)+" ");
                }
                System.out.println("\n");
            }
             loadScanner.close();
             mbCon.setFileFlag(true);
             return true;
         } catch(Exception e){
             //System.out.println("Hey Buddy, Somtething is wrong in file.");
             return false;
         }
    }
}
