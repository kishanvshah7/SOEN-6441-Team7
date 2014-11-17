import java.io.File;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import tdgame.controller.ShopController;
import tdgame.model.ShopModel;
import towerdefensegame.GamePlay;

public class ShopControllerTest {
	
	public GamePlay gptest = new GamePlay(new File("MapFiles/20 15.team7"), 5, 5);
        public ShopController sctest = gptest.getPsCont().getsCont();
        public ShopModel sModel = gptest.getPsCont().getsModel();
        
        
        @Before
        public void setup(){
            //gptest = new GamePlay(new File("MapFiles/20 15.team7"), 5, 5);
            //sctest = gptest.getPsCont().getsCont();
            sModel.setHeldID(3);
            sctest.placeTower(5, 5, 0);
        }

	@Test
	public void testPlaceTower_entry() {
            boolean exp = false;
            boolean act = sctest.placeTower(0, 0, 0);
            assertEquals(exp, act);
	}
	
	@Test
	public void testPlaceTower_exit() {
            boolean exp = false;
            boolean act = sctest.placeTower(0, 15, 0);
            assertEquals(exp, act);
	}
        
        @Test
	public void testPlaceTower_true() {
            boolean exp = true;
            boolean act = sctest.placeTower(2, 4, 0);
            assertEquals(exp, act);
	}
        
        @Test
	public void testPlaceTower_replaceTower() {
            boolean exp = false;
            boolean act = sctest.placeTower(5, 5, 0);
            assertEquals(exp, act);
	}

	@Test
	public void testRemoveTower() {
            boolean exp = false;
            boolean act = sctest.removeTower(0, 0);
            assertEquals(exp, act);
	}
        
        @Test
	public void testRemoveTower_true() {
            boolean exp = true;
            boolean act = sctest.removeTower(5, 5);
            assertEquals(exp, act);
	}
        
        @Test
	public void testIsTowerHere() {
            boolean exp = false;
            boolean act = sctest.isTowerHere(0, 0);
            assertEquals(exp, act);
	}

	@Test
	public void testIsTowerHere_true() {
            boolean exp = true;
            boolean act = sctest.isTowerHere(5, 5);
            assertEquals(exp, act);
	}

}