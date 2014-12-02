package tdgame.model;

import tdgame.model.Strategy;

/**
 *
 * @author Ankit
 */
public class WeakestCritter implements Strategy {
    CreatureModel cModel =new CreatureModel();
//    int health = cModel[getShotMob()].getHealth();

    @Override
    public int execute(int a, int b) {
        return (a+b);
        
    }
}
