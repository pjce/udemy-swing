import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {

	/* ************ Declarations ********************************************* */

	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;

	/* ************ Constructor *********************************************** */

	public MainFrame() {
		super("Hello World");

		setLayout(new BorderLayout());

		textPanel = new TextPanel(); // main display box
		toolbar = new Toolbar(); // rubbish toolbar
		formPanel = new FormPanel(); // form panel

		setJMenuBar(createMenuBar());

		// receive and deal with signals/input from (rubbish) toolbar
		toolbar.setStringListener(new StringListener() {

			public void textEmitted(String text) {
				textPanel.appendText(text);
			}

		});
		// receive and deal with signals/input from form panel
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				/*
				 * Fires when a formEventOccurred signal comes from FormPanel
				 * (in turn fired by action listener detecting submit button
				 * pressed) retrieves wanted data from event object, using
				 * methods defines in the FormEvent class. Adds desired info to
				 * the text display panel.
				 */
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

	// set up the top dropdown menu
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem); // file filemenu items
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
