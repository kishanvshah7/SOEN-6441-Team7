

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import tdgame.controller.MapValidation;
import tdgame.model.PlayScreenModel;
import towerdefensegame.GameLogViewer;

public class MapValidationTest {
	
	PlayScreenModel psmodeltest = new PlayScreenModel();
	
	@Before
        public void setup(){
            GameLogViewer.testFlag = true;
        }
        
	@Test
	public void testIsValid_withoutEntryExit() {
		boolean temp = psmodeltest.LoadMap_XML(new File("MapFilesTest/path.team7"));
		MapValidation mvtest = new MapValidation(psmodeltest.getGridCellArray());
		boolean exp = false;
		boolean act = mvtest.isValid();
		assertEquals(exp, act);
	}
	
	@Test
	public void testIsValid_withoutPath() {
		boolean temp2 = psmodeltest.LoadMap_XML(new File("MapFilesTest/path2.team7"));
		MapValidation mvtest = new MapValidation(psmodeltest.getGridCellArray());
		boolean exp = false;
		boolean act = mvtest.isValid();
		assertEquals(exp, act);
	}
	
	@Test
	public void testIsValid_brokenPath() {

		boolean temp3 = psmodeltest.LoadMap_XML(new File("MapFilesTest/path3.team7"));
		MapValidation mvtest = new MapValidation(psmodeltest.getGridCellArray());
		boolean exp = false;
		boolean act = mvtest.isValid();
		assertEquals(exp, act);
	}
	
	@Test
	public void testIsValid_validPath() {
		boolean temp4 = psmodeltest.LoadMap_XML(new File("MapFilesTest/path4.team7"));
		MapValidation mvtest = new MapValidation(psmodeltest.getGridCellArray());
		boolean exp = true;
		boolean act = mvtest.isValid();
		assertEquals(exp, act);
	}
}
