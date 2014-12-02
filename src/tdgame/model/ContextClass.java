package tdgame.model;

import tdgame.model.Strategy;

/**
 *
 * @author Ankit
 */
public class ContextClass {
    private Strategy strategy;
    
    public void setStrategy(Strategy s) {
        this.strategy = s;
    }
    
    public int executeStrategy(int a, int b) {
        return this.strategy.execute(a, b);
    }
}
