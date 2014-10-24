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
    
    boolean fileFlag = false;
    
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
    
    public void setmapGirdArrayElement(int y, int x, int val){
        if(val == 7){
            for(int yt=0;yt<yBlockCount;yt++){
                mapGirdArray[yt][0] = 0;
            }
        }else if(val == 8){
            for(int yt=0;yt<yBlockCount;yt++){
                mapGirdArray[yt][xBlockCount-1] = 0;
            }
        } 
        if(mapGirdArray[y][x] == 1)
            mapGirdArray[y][x] = 0;
        else
            mapGirdArray[y][x] = val;
    }
    
    public void setmapGirdArrayElementF(int y, int x, int val){
        if(x == 0){
            for(int yt=0;yt<yBlockCount;yt++){
                if(mapGirdArray[yt][0] != 7)
                    mapGirdArray[yt][0] = 0;
            }
            if(val == 7)
                mapGirdArray[y][x] = val;
        }else if(x == xBlockCount-1){
            for(int yt=0;yt<yBlockCount;yt++){
                if(mapGirdArray[yt][xBlockCount-1] != 8)
                    mapGirdArray[yt][xBlockCount-1] = 0;
            }
            if(val == 8)
                mapGirdArray[y][x] = val;
        }
        else{
            mapGirdArray[y][x] = val;
        }
    }
    
    public int getmapGirdArrayElement(int y, int x){
        return mapGirdArray[y][x];
    }
    
    public int getEntryPointData(){
        for(int y=0;y<yBlockCount;y++){
            if(mapGirdArray[y][0] == 7)
                return y;
        }
        return 9;
    }
    
    public int getExitPointData(){
        for(int y=0;y<yBlockCount;y++){
            if(mapGirdArray[y][xBlockCount-1] == 8)
                return y;
        }
        return 9;
    }
    
    public int[][] getMapGirdArray(){
        return mapGirdArray;
    }
    
    public void setGridArray(){
        mapGirdArray = new int[yBlockCount][xBlockCount];
        System.out.println("MapBoxModel.setGridArray()");
        for(int x=0;x<xBlockCount;x++){
          for(int y=0;y<yBlockCount;y++){
                mapGirdArray[y][x] = 0;
          }
      }
    }
    
    public boolean getFileFlag(){
        return fileFlag;
    }
    
    public void setFileFlag(boolean b){
        fileFlag = b;
    }
}
