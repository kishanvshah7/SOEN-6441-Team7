package tdgame.view;

import static org.junit.Assert.*;

import org.junit.Test;

import tdgame.controller.MapBoxController;

public class MapCreationViewTest {
	// Test Case to check if Adding Grid Function is working as expected or not
	
	@Test
	public void testAddGridMap() {
		boolean exp = true;
		MapCreationView mctest = new MapCreationView();
		MapBoxController mbtest = new MapBoxController();
		boolean act = mctest.addGridMap(mbtest);
		assertEquals(exp, act);
		
		
	}

}
