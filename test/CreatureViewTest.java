
import java.awt.Graphics;
import java.io.File;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import statePattern.Context;
import statePattern.StartState;
import tdgame.model.CreatureModel;
import tdgame.model.configModel;
import tdgame.view.CreatureView;
import tdgame.view.PlayScreenView;
import towerdefensegame.GameLogViewer;
import towerdefensegame.GamePlay;

public class CreatureViewTest {

     GamePlay gptest = new GamePlay(new File("MapFilesTest/Path4.team7"), 5, 5, "GamePlay");

     PlayScreenView psviewtest;
     CreatureView cView;
     CreatureModel Creatures;
    
    Context context = new Context();
    StartState startState = new StartState();

    @BeforeClass
    public static void onlyOnce() {
        GameLogViewer.testFlag = true;
        
    }
    
    @Before
    public void setup() {
        startState.doAction(context);
        psviewtest = new PlayScreenView(gptest, startState, context);
        cView = new CreatureView();
        Creatures = new CreatureModel(gptest.getPsCont().getCcModel(), gptest.getPsCont().getCcCont());
    }

    @Test
    public void testDraw_false() {
        boolean exp = false;
        boolean act = cView.draw(Creatures, 0, null);
        System.out.println("ABCD:" + psviewtest.getGraph());
        assertEquals(exp, act);
    }

}
