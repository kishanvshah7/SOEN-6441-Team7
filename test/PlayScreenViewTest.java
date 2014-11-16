

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import tdgame.model.PlayScreenModel;
import tdgame.view.PlayScreenView;
import towerdefensegame.GamePlay;

public class PlayScreenViewTest {
	
	
	GamePlay gptest = new GamePlay(new File("MapFilesTest/Path4.team7"), 5, 5);
	
	PlayScreenView psviewtest = new PlayScreenView(gptest);
	
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
	
	
	

}
