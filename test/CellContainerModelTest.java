

import static org.junit.Assert.*;

import org.junit.Test;

import tdgame.model.CellContainerModel;
import towerdefensegame.GameLogViewer;
import towerdefensegame.LogGenerator;

public class CellContainerModelTest {

	CellContainerModel ccModel;
	@Test
	public void testCellContainerModel() {
                GameLogViewer.testFlag = false;
		ccModel = new CellContainerModel(5, 5);
		boolean exp = true;
		boolean act = ccModel.setCellContainerModel(5, 5);
		assertEquals(exp, act);
	}

}
