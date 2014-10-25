import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;

import junit.extensions.abbot.ComponentTestFixture;
import junit.extensions.abbot.TestHelper;
import abbot.finder.Matcher;


public class HelloWorldGUITest extends ComponentTestFixture {
	
	public void testing() throws Throwable {
		final HelloWorldGUI hello = new HelloWorldGUI();
		
		showFrame(hello);
		
		JLabel label = (JLabel)getFinder().find(hello, new Matcher() {
			public boolean matches(Component c) {
				
				return c.getClass().equals(JLabel.class) && c.getParent() == hello;
					
			}
			
			
		});
		
		assertEquals("Not exactly name", "Hello World", label.getText() );
		
	}
	
	
	public void testing1() throws Throwable {
		final HelloWorldGUI hello = new HelloWorldGUI();
		
		showFrame(hello);
		
		JLabel label = (JLabel)getFinder().find(hello, new Matcher() {
			public boolean matches(Component c) {
				
				return c.getClass().equals(JLabel.class) && c.getParent() == hello;
					
			}
			
			
		});
		
		assertEquals("Not exactly name", "abc", label.getName() );
		
	}
	
	
	public void testing3() throws Throwable {
		final HelloWorldGUI hello = new HelloWorldGUI();
		
		showFrame(hello);
		
		JLabel label = (JLabel)getFinder().find(hello, new Matcher() {
			public boolean matches(Component c) {
				
				return c.getClass().equals(JLabel.class) && c.getParent() == hello;
					
			}
			
			
		});
		
		Dimension dim = new Dimension(100, 100);
		assertEquals("Not exactly name", dim, label.getSize() );
		
	}
	
	
	public HelloWorldGUITest(String name) {
		
		super(name);
	
	}
	
	public static void main(String[] args, Class arg1) {
		
		TestHelper.runTests(args, HelloWorldGUITest.class);
	
	

}
	
}
 