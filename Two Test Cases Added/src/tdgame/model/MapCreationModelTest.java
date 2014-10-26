package tdgame.model;

import java.io.File;

import static org.junit.Assert.*;
import jdk.nashorn.internal.ir.annotations.Ignore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import tdgame.controller.MapBoxController;

public class MapCreationModelTest {

	private static final String String = null;

	@Before                        
	public void setUp() {
		
		
		
	}
	
	@Ignore
	public void testSetXBlockCount() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testSetYBlockCount() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetXBlockCount() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetYBlockCount() {
		fail("Not yet implemented");
	}
	
	
	//Testing the ReadFile Function to check if the map is able to determine invalid mapfiles.

	@Test
	public void testReadFile() {
		boolean ans = true;
		MapCreationModel mctest = new MapCreationModel();
		MapBoxController mbtest = new MapBoxController();
		File path = new File("MapFilesTest/MapTest.txt");
		boolean org = mctest.readFile(mbtest, path);
		assertEquals(ans, org);
	}	
	


	@Ignore
	public void testObject() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testClone() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testToString() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testWait() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testFinalize() {
		fail("Not yet implemented");
	}
	
	@After
	public void teardown() {
		
		
	}

}
