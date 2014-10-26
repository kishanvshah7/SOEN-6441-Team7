package tdgame.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import tdgame.model.MainScreenModel;
import tdgame.view.MainScreenView;

public class MainScreenControllerTest {

	@Test
	public void testInitMapCreationController() {
		boolean exp = true;
		MainScreenView msvtest = new MainScreenView();
		MainScreenModel msmtest = new MainScreenModel();
		MainScreenController msctest = new MainScreenController(msvtest, msmtest);
		boolean act = (boolean) msctest.initMapCreationController();
		assertEquals(exp, act);
		
	}

}
