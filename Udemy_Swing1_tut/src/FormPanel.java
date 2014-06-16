import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {

	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList ageList;
	private JComboBox empCombo;

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		ageList = new JList();
		empCombo = new JComboBox();

		// set up list box
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "65 or over"));
		ageList.setModel(ageModel);

		ageList.setPreferredSize(new Dimension(110, 68));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1); // sets default selection

		// set up combo box
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");
		empCombo.setModel(empModel);
		
		
		okBtn = new JButton("OK");

		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue(); // JList

				System.out.println(ageCat.getId()); // debug

				FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId());

				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();

	}
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());

		/* setting constraints at start can be essential */
		GridBagConstraints gc = new GridBagConstraints();

		// ///////////// First Row ///////////////////////////////////

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END; // defines where added sticks
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel, gc);

		gc.gridx = 1; // repeat these variables even if same easier to read
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START; // defines where added sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);

		// ///////////// Second Row ///////////////////////////////////

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy = 1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END; // defines where added sticks
		gc.insets = new Insets(0, 0, 0, 5);
		add(occupationLabel, gc);

		gc.gridy = 1;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START; // defines where added sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(occupationField, gc);

		// ///////////// Third Row ///////////////////////////////////

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridy = 2;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // defines where sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(ageList, gc);

		// ///////////// Fourth Row ///////////////////////////////////

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridy = 3;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // defines where sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}

class AgeCategory {
	private int id;
	private String text;
	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public int getId() {
		return id;
	}
}
