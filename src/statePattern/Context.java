/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package statePattern;

/**
 * Context for state pattern
 * @author Rahul K Kikani
 */
public class Context {
   private State state;

   /**
    * Context initialize
    */
   public Context(){
      state = null;
   }
   
   /**
    * set game sate
    * @param state state object
    */
   public void setState(State state){
      this.state = state;		
   }

   /**
    * get game state
    * @return state
    */
   public State getState(){
      return state;
   }
}
