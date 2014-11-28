/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extra;

/**
 *
 * @author Rahul K Kikani
 */
public class test {
 
    public static int[] topScore = new int[]{230, 200, 160, 130, 100};
    
    public static void main(String args[]){
        int score = 210;
        for (int i = 0; i < topScore.length; i++) {
            int temp_score = topScore[i];
            if ( temp_score < score) {
                int temp;
                for(int j=i+1;j<topScore.length;j++){
                    temp = topScore[j];
                    topScore[j] = temp_score;
                    temp_score = temp;
                }
                topScore[i] = score;
                break;
            }
        }
        
        for (int i = 0; i < topScore.length; i++) {
            System.out.println(""+topScore[i]);
        }
    }
}
