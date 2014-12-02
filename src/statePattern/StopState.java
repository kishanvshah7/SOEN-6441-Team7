/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package statePattern;

/**
 * Game Stop State
 * @author Rahul K Kikani
 */
public class StopState implements State {

   /**
    * initialize game stop sate
    * @param context context object
    */
   public void doAction(Context context) {
      System.out.println("Game is in stop state");
      context.setState(this);	
   }
   
   /**
    * return state info
    * @return  State in string
    */
   public String toString(){
      return "Stop";
   }
}
