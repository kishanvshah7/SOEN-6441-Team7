package tdgame.view;

import static org.junit.Assert.*;
import jdk.nashorn.internal.ir.annotations.Ignore;

import org.junit.Before;
import org.junit.Test;

import tdgame.controller.MapBoxController;

public class MapBoxViewTest {
	
	private static final String expected = null;
	private static final String ArrayIndexOutOfBoundsException = null;
	MapBoxView mbvtest;
	MapBoxController mbctest;
	
	@Before
	public void SetupClassFiles(){
	mbctest = new MapBoxController();
	
	mbvtest = new MapBoxView(mbctest, 10, 10 );
	}
	
	@Test
	public void testSetEntryPointFlag() {
		boolean exp = true;
		boolean act = (boolean) mbvtest.setEntryPointFlag();
		assertEquals(exp, act);
		
	}

	@Test
	public void testSetPathPointFlag() {
		boolean exp = true;
		boolean act = (boolean) mbvtest.setPathPointFlag();
		assertEquals(exp, act);
	}

	@Test
	public void testSetExitPointFlag() {
		boolean exp = true;
		boolean act = (boolean) mbvtest.setEntryPointFlag();
		assertEquals(exp, act);
	}

	@Test
	public void testSetSlectedCell() {
		boolean exp = true;
		boolean act = (boolean) mbvtest.setSlectedCell();
		assertEquals(exp, act);
		
	}
	
	// Testing the Entry Point Location

	@Test
	public void testSetEntryPoint() {
		boolean exp = true;
		boolean act = (boolean) mbvtest.setEntryPoint(8, 0);
		assertEquals(exp, act);
	}

	
  // Testing the Exit Point Location
	
	@Test
	public void testSetExitPoint() {
		
		boolean exp = true;
		boolean act = (boolean) mbvtest.setExitPoint(3, 9);
		assertEquals(exp, act);
		
	}

}
