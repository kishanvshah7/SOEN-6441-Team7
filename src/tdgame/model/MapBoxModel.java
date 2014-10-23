/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

/**
 *
 * @author Rahul K Kikani
 */
public class MapBoxModel {
    
    public int xBlockCount;
    public int yBlockCount;
    public int[][] mapGirdArray;
    
    public MapBoxModel()
    {
       System.out.println("MapBoxModel");
    }
    
    public int getXBlockCount(){
        return xBlockCount;
    }
    public int getYBlockCount(){
        return yBlockCount;
    }
    
    public void setXBlockCount(int x){
        System.out.println("MapBoxModel.setXBlockCount()");
        xBlockCount = x;
    }
    public void setYBlockCount(int y){
        System.out.println("MapBoxModel.setYBlockCount()");
        yBlockCount = y;
    }
    
    public void setmapGirdArrayElement(int x, int y, int val){
        if(val == 7){
            for(int yt=0;yt<yBlockCount;yt++){
                mapGirdArray[0][yt] = 0;
            }
        }else if(val == 8){
            for(int yt=0;yt<yBlockCount;yt++){
                mapGirdArray[xBlockCount-1][yt] = 0;
            }
        }
        mapGirdArray[x][y] = val;
    }
    
    public int getmapGirdArrayElement(int x, int y){
        return mapGirdArray[x][y];
    }
    
    public int getEntryPointData(){
        for(int y=0;y<yBlockCount;y++){
            if(mapGirdArray[0][y] == 7)
                return y;
        }
        return 9;
    }
    
    public int getExitPointData(){
        for(int y=0;y<yBlockCount;y++){
            if(mapGirdArray[xBlockCount-1][y] == 8)
                return y;
        }
        return 9;
    }
    
    public int[][] getMapGirdArray(){
        return mapGirdArray;
    }
    
    public void setGridArray(){
        mapGirdArray = new int[xBlockCount][yBlockCount];
        for(int x=0;x<xBlockCount;x++){
          for(int y=0;y<yBlockCount;y++){
                mapGirdArray[x][y] = 0;
          }
      }
    }
}
