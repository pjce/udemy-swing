import java.awt.BorderLayout;

import javax.swing.JFrame;


public class MainFrame extends JFrame {

	private TextPanel textPanel;
	private Toolbar toolbar;	
	private FormPanel formPanel;
	
	
	public MainFrame() {
		super("Hello World");
		
		setLayout(new BorderLayout());

		
		textPanel = new TextPanel();
		toolbar = new Toolbar();
		formPanel = new FormPanel();
		
		toolbar.setStringListener(new StringListener() {

			public void textEmitted(String text) {
				textPanel.appendText(text);
				System.out.println(text);
			}
			
		});
		
		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
		
		
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
