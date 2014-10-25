import javax.swing.JLabel;
import javax.swing.JPanel;


public class HelloWorldGUI extends JPanel {
	private JLabel label;
	
	public HelloWorldGUI() {
		//setLayout(null);
		label = new JLabel("Hello World");
		label.setName("abc");
		label.setSize(100, 100);
		label.setVisible(true);
		add(label);
		
	}
	

}
 