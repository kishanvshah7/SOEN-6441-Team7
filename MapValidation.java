/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package towerdegencegamedemo;

import java.util.Arrays;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author Kishan V. Shah
 */
public class MapValidation {
    static int enX = 0;
    static int enY = 0;
    static int exX = 0;
    static int exY = 0;
    //static int[][] GridArray;
    //static int[][] GridArray_temp;
    static int width = 5;
    static int height = 17;
    static int lastCheckX =0;
    static int lastCheckY =0;
    static String flag = "f";
    public static int[][] GridArray = new int[][]{
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {7,1,1,1,1,0,0,0,0,1,1,1,0,0,0,0,0},
            {0,0,0,0,1,1,1,1,1,1,0,1,1,0,0,0,0},
            {0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0},
            {0,1,0,0,0,1,0,1,0,0,0,0,0,0,1,1,8}};
    public static int[][] GridArray_temp = new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    
    public static void main(String args[]){
                MapValidation mp = new MapValidation();
        for(int y=0;y < width; y++){
            if(GridArray[y][0]==7){
                enX = y; //enX = 0
                enY = 0;
            }
            if(GridArray[y][height-1]==8){
                exX = y; //enX = 0
                exY = width-1;
            }
        }
        mp.start();
    }
    
    public void start(){
            if(!flag.equals("e")){
                lastCheckX = enX;
                lastCheckY = enY;
                flag = "a";
                GridArray_temp = new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
                if(checkRight() == 0){
                    System.out.println("Path Not Found.");
                } else{
                    right();
                }
            }
    }
    
    public void right(){
        if(!flag.equals("l") && !flag.equals("e" )){
                flag = "r";
                if(checkRight() == 1){
                    right();
                }else if(checkRight() == 8){
                    System.out.println("Path Found.");
                    flag = "e";
                }else if(checkUp() == 1){
                    up();
                }else if(checkDown() == 1){
                    down();
                }else{
                    GridArray[lastCheckX][lastCheckY] = 0;
                    start();
                }
            }
    }
    
    public void left(){
        if(!flag.equals("r") && !flag.equals("e" )){
                flag = "l";
                if(checkUp() == 1){
                    up();
                }else if(checkDown() == 1){
                    down();
                }else if(checkLeft() == 1){
                    left();
                }else{
                    GridArray[lastCheckX][lastCheckY] = 0;
                    start();
                }
            }
    }
    
    public void up(){
        System.out.println(""+flag.equals("d"));
        if(!flag.equals("d") && !flag.equals("e" )){
                flag = "u";
                if(checkUp() == 1){
                    up();
                }else if(checkRight() == 1){
                    right();
                }else if(checkRight()==8){
                System.out.println("Path Found.");
                    flag = "e";
                }else if(checkLeft() == 1){
                    left();
                }else{
                    GridArray[lastCheckX][lastCheckY] = 0;
                    start();
                }
            }
    }
    
    public void down(){
        if(!flag.equals("u") && !flag.equals("e" )){
                flag = "d";
                if(checkDown() == 1){
                    down();
                }else if(checkRight()==1){
                    right();
                }else if(checkRight() == 8){
                    System.out.println("Path Found.");
                    flag = "e";
                }else if(checkLeft() == 1){
                    left();
                }
                else{
                    GridArray[lastCheckX][lastCheckY] = 0;
                    start();
                }
            }
    }
    
    public int checkRight(){
        if(lastCheckY+1 < height){
            if(GridArray_temp[lastCheckX][lastCheckY+1] != 3){
                if(GridArray[lastCheckX][lastCheckY+1] == 1){
                    lastCheckY++;
                    GridArray_temp[lastCheckX][lastCheckY] = 3;
                    return 1;
                }else if(GridArray[lastCheckX][lastCheckY+1]==8){
                    return 8;
                }else{
                    return 0;
                }
            }
            else
                return 0;
        }
        else
            return 0;
    }
    
    public int checkLeft(){
        if(lastCheckY-1 > 0){
            if(GridArray_temp[lastCheckX][lastCheckY-1] != 3){
                if(GridArray[lastCheckX][lastCheckY-1] == 1){
                    lastCheckY--;
                    GridArray_temp[lastCheckX][lastCheckY] = 3;
                    return 1;
                }else{
                    return 0;
                }
            }
            else
                return 0;
        }
        else
            return 0;
    }
    
    public int checkUp(){
        if(lastCheckX-1 >= 0){
            if(GridArray_temp[lastCheckX-1][lastCheckY] != 3){
                if(GridArray[lastCheckX-1][lastCheckY] == 1){
                    lastCheckX--;
                    GridArray_temp[lastCheckX][lastCheckY] = 3;
                    return 1;
                }else{
                    return 0;
                }
            }
            else
                return 0;
        }
        else
            return 0;
    }
    
    public int checkDown(){
        if(lastCheckX+1 < width){
            if(GridArray_temp[lastCheckX+1][lastCheckY] != 3){
                if(GridArray[lastCheckX+1][lastCheckY] == 1){
                    lastCheckX++;
                    GridArray_temp[lastCheckX][lastCheckY] = 3;
                    return 1;
                }else{
                    return 0;
                }
            }
            else
                return 0;
        }
        else
            return 0;
    }
}