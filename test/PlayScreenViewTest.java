

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import statePattern.Context;
import statePattern.StartState;

import tdgame.model.CreatureModel;
import tdgame.model.PlayScreenModel;
import tdgame.model.configModel;
import tdgame.view.PlayScreenView;
import towerdefensegame.GameLogViewer;
import towerdefensegame.GamePlay;

public class PlayScreenViewTest {
	
	
	static GamePlay gptest = new GamePlay(new File("MapFilesTest/Path4.team7"), 5, 5, "GamePlay");
	
        static Context context = new Context();
        static StartState startState = new StartState();
        static PlayScreenView psviewtest;
        
        @BeforeClass
        public static void onlyOnce() {
            GameLogViewer.testFlag = true;
            startState.doAction(context);
            psviewtest = new PlayScreenView(gptest, startState, context);
        }
	
	@Before
        public void setup(){
            GameLogViewer.testFlag = true;
        }
	
	@Test
	public void teststartGame(){
		
		boolean exp = true;
		boolean act = psviewtest.startGame();
		assertEquals(exp, act);
		
		
	}
	
	
	@Test
	public void testMobSpawner() {
		boolean exp = true;
		boolean act = psviewtest.mobSpawner();
		assertEquals(exp, act);
		
	}
	
	@Test
	public void TestinitCreatures(){
		psviewtest.setController(null);
		boolean exp = false;
		boolean act = psviewtest.initCreatures();
		assertEquals(exp, act);
    }
	
	@Test
	public void TestinitCreatures_true(){
		psviewtest.setController(gptest.getPsCont());
		boolean exp = true;
		boolean act = psviewtest.initCreatures();
		assertEquals(exp, act);
    }
	
	@Test
    public void TesthasWon() {
		configModel.killed = 5;
                configModel.killsToWin = 5;
                configModel.maxLevel = 1;
                configModel.level = 2;
		boolean exp = true;
		boolean act = psviewtest.hasWon();
		assertEquals(exp, act);
    }
	
	@Test
    public void TesthasWon_false() {
		configModel.killed = 0;
		boolean exp = false;
		boolean act = psviewtest.hasWon();
		assertEquals(exp, act);
    }
	
	@Test
    public void TestisgameOver() {
		configModel.health = 0;
		boolean exp = true;
		boolean act = psviewtest.isGameOver();
		assertEquals(exp, act);
    }
	
	@Test
    public void TestisgameOver_false() {
		configModel.health = 3;
		boolean exp = false;
		boolean act = psviewtest.isGameOver();
		assertEquals(exp, act);
    }
}
