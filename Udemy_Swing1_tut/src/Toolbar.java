import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {
	private JButton helloButton;
	private JButton goodbyeButton;

	private StringListener textListener;

	public Toolbar() {
		/*
		 * This is from an early tutorial and is not necessarily the best
		 * implementation of action listener. The (basic) logic used to decide
		 * what to emit on action performed is hard coded within this element of
		 * the view. However implementing ActionLister and then referencing
		 * "this" within the addActionListeners is good practice for certain
		 * circumstances, I believe.
		 */

		setBorder(BorderFactory.createEtchedBorder());

		helloButton = new JButton("Hello");
		goodbyeButton = new JButton("Goodbye");

		helloButton.addActionListener(this);
		goodbyeButton.addActionListener(this);

		setLayout(new FlowLayout(FlowLayout.LEFT));

		add(helloButton);
		add(goodbyeButton);

	}

	/* **************************************************** */

	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}

	/* **************************************************** */

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();

		if (clicked == helloButton) {
			if (textListener != null) {
				textListener.textEmitted("Hello\n");
			}
			// textPanel.appendText("Hello\n");
		} else if (clicked == goodbyeButton) {
			// textPanel.appendText("Goodbye\n");
			if (textListener != null) {
				textListener.textEmitted("Goodbye\n");
			}

		}
	}
}
