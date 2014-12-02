
import java.io.File;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tdgame.controller.ShopController;
import tdgame.model.ShopModel;
import tdgame.model.configModel;
import towerdefensegame.GameLogViewer;
import towerdefensegame.GamePlay;

public class ShopControllerTest {

    public GamePlay gptest = new GamePlay(new File("MapFiles/7_8.team7"), 5, 5, "GamePlay");
    public ShopController sctest = gptest.getPsCont().getsCont();
    public ShopModel sModel = gptest.getPsCont().getsModel();

    
    @BeforeClass public static void onlyOnce() {
       GameLogViewer.testFlag = true;
    }
    
    @Before
    public void setup() {
        
        gptest = new GamePlay(new File("MapFiles/7_8.team7"), 5, 5, "GamePlay");
        sctest = gptest.getPsCont().getsCont();
        configModel.money = 150;
        sModel.setHeldID(3);
        sctest.placeTower(5, 5, 0);
        sctest.placeTower(4, 4, 1);
        sctest.placeTower(3, 3, 2);
        sctest.placeTower(2, 2, 3);
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
        boolean act = sctest.placeTower(1, 6, 0);
        assertEquals(exp, act);
    }

    @Test
    public void testPlaceTower_path() {
        boolean exp = false;
        boolean act = sctest.placeTower(0, 2, 0);
        assertEquals(exp, act);
    }

    @Test
    public void testPlaceTower_true() {
        boolean exp = true;
        boolean act = sctest.placeTower(2, 3, 0);
        assertEquals(exp, act);
    }

    @Test
    public void testPlaceTower_replaceTower() {
        boolean exp = false;
        boolean act = sctest.placeTower(5, 5, 1);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower2() {
        boolean exp = false;
        boolean act = sctest.placeTower(5, 5, 2);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower3() {
        boolean exp = false;
        boolean act = sctest.placeTower(5, 5, 3);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower10() {
        boolean exp = false;
        boolean act = sctest.placeTower(4, 4, 0);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower11() {
        boolean exp = false;
        boolean act = sctest.placeTower(4, 4, 1);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower12() {
        boolean exp = false;
        boolean act = sctest.placeTower(4, 4, 2);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower13() {
        boolean exp = false;
        boolean act = sctest.placeTower(4, 4, 3);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower20() {
        boolean exp = false;
        boolean act = sctest.placeTower(3, 3, 0);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower21() {
        boolean exp = false;
        boolean act = sctest.placeTower(3, 3, 1);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower22() {
        boolean exp = false;
        boolean act = sctest.placeTower(3, 3, 2);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower23() {
        boolean exp = false;
        boolean act = sctest.placeTower(3, 3, 3);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower30() {
        boolean exp = false;
        boolean act = sctest.placeTower(2, 2, 0);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower31() {
        boolean exp = false;
        boolean act = sctest.placeTower(2, 2, 1);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower32() {
        boolean exp = false;
        boolean act = sctest.placeTower(2, 2, 2);
        assertEquals(exp, act);
    }
    
    @Test
    public void testPlaceTower_replaceTower33() {
        boolean exp = false;
        boolean act = sctest.placeTower(2, 2, 3);
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

    @Test
    public void testTowerCharges() {
        int exp = 50;
        boolean act = sctest.placeTower(5, 5, 0);
        int total_money = configModel.money;
        assertEquals(exp, total_money);
    }

    @Test
    public void testTowerReturnCharges() {
        int exp = 58;
        boolean act = sctest.removeTower(5, 5);
        int total_money = configModel.money;
        assertEquals(exp, total_money);
    }

}
