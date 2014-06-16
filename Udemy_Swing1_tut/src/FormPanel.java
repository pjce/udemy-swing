import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {

	/* ************ Declarations ********************************************* */
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JLabel taxLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JTextField taxField;
	private JButton okBtn;
	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox citizenCheck;

	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;

	private FormListener formListener;

	/* ************ Constructor *********************************************** */
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		ageList = new JList();
		empCombo = new JComboBox(); // like JList but potential editable
		citizenCheck = new JCheckBox();
		taxField = new JTextField(10); // 10 sets textfield size
		taxLabel = new JLabel("Tax ID");

		// set up gender radios
		maleRadio = new JRadioButton("Male"); // init + labels
		femaleRadio = new JRadioButton("Female");

		maleRadio.setActionCommand("male"); // internal value, i.e.
		femaleRadio.setActionCommand("female"); // for introspection

		maleRadio.setSelected(true); // sets default radio button

		genderGroup = new ButtonGroup(); // groups radio buttons
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);

		// set up tax ID
		taxLabel.setEnabled(false);
		taxField.setEnabled(false);

		citizenCheck.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				boolean isTicked = citizenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
			}
		});

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
		empCombo.setSelectedIndex(0); // sets default selection
		empCombo.setEditable(true); // may return object or String (if using
									// IDs)

		okBtn = new JButton("OK");

		// submit button action listener
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * detects submit button press and loads all wanted information
				 * into an event ev of class FormEvent, which is then passed to
				 * the FormListener, from there the information can be accessed
				 * by the MainFrame, using FormEvent methods.
				 */
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue(); // JList
				String empCat = (String) empCombo.getSelectedItem();
				String taxId = taxField.getText();
				boolean usCitizen = citizenCheck.isSelected();

				String gender = genderGroup.getSelection().getActionCommand();

				FormEvent ev = new FormEvent(this, name, occupation, ageCat
						.getId(), empCat, taxId, usCitizen, gender);

				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents(); // layout operations separated for clarity

	}

	/* ************ Layout Function ******************************************* */

	public void layoutComponents() {
		setLayout(new GridBagLayout());
		/* setting constraints at start can be essential */
		GridBagConstraints gc = new GridBagConstraints();

		// ///////////// First Row ///////////////////////////////////

		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END; // defines where added sticks
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel, gc);

		gc.gridx = 1; // repeat these variables even if same easier to read
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START; // defines where added sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);

		// ///////////// Next Row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END; // defines where added sticks
		gc.insets = new Insets(0, 0, 0, 5);
		add(occupationLabel, gc);

		gc.gridy = 1;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START; // defines where added sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(occupationField, gc);

		// ///////////// Next Row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END; // defines where added
														// sticks
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Age: "), gc);// create/instantiate JLabel on the fly,
										// unlikely that it is going to be
										// altered.

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // defines where sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(ageList, gc);

		// ///////////// Next Row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END; // defines where added
														// sticks
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Employment: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // defines where sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(empCombo, gc);

		// ///////////// Next Row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END; // defines where added
														// sticks
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("US Citizen: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // defines where sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(citizenCheck, gc);

		// ///////////// Next Row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END; // defines where added
														// sticks
		gc.insets = new Insets(0, 0, 0, 5);
		add(taxLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // defines where sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(taxField, gc);

		// ///////////// Next Row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END; // defines where added
													// sticks
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Gender: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // defines where sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(maleRadio, gc);

		// ///////////// Next Row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // defines where sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(femaleRadio, gc);

		// ///////////// Next Row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; // defines where sticks
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);
	}

	/* ************ Form Interface Connection ********************************* */

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
