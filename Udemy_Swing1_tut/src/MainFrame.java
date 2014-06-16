import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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

		setJMenuBar(createMenuBar());

		toolbar.setStringListener(new StringListener() {

			public void textEmitted(String text) {
				textPanel.appendText(text);
			}

		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();
				String empCat = e.getEmploymentCategory();
				boolean isCit = e.isUsCitizen();
				String taxId = e.getTaxId();
				String gender = e.getGender();

				textPanel.appendText(name + ", " + gender + ", " + occupation
						+ ": " + ageCat + ", " + empCat + "\n");
				textPanel.appendText("US Citizen: " + isCit + ", Tax ID: "
						+ taxId + "\n\n");
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);

		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		JMenu windowMenu = new JMenu("Window"); // create main window menu
		JMenu showMenu = new JMenu("Show"); // create secondary show menu
		JMenuItem showFormItem = new JMenuItem("Person Form"); // create item
		showMenu.add(showFormItem); // add item to show menu
		windowMenu.add(showMenu); // add show menu to window menu

		menuBar.add(fileMenu);
		menuBar.add(windowMenu); // add window to menu bar

		return menuBar;
	}

}
