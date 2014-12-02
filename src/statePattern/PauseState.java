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
public class PauseState implements State {

    /**
     * initialize game pause sate
     *
     * @param context context object
     */
    public void doAction(Context context) {
        System.out.println("Gsme is in Pause state");
        context.setState(this);
    }

    /**
     * Game state
     *
     * @return state
     */
    public String toString() {
        return "Pause";
    }
}
