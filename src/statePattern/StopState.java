/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package statePattern;

/**
 *
 * @author Rahul K Kikani
 */
public class StopState implements State {

   public void doAction(Context context) {
      System.out.println("Game is in stop state");
      context.setState(this);	
   }

   public String toString(){
      return "Stop";
   }
}