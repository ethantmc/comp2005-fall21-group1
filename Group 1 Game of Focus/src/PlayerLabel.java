import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PlayerLabel extends JPanel implements ActionListener {

	private JButton colorblind;
	private Container contentPane;
	private int playerID;
	private JLabel changePlayerText,changeColorDeficiency;
	private boolean colorblindStatus = false; //TODO: this MUST be replaced with the status of the setting for the correct player.

	public PlayerLabel() {
		super();
		this.setLayout(new GridLayout(4, 1));
	}

	public void actionPerformed(ActionEvent aevt)
	{
		// get the object that was selected in the gui
		Object selected = aevt.getSource();


		if (selected.equals(colorblind) )
		{
			this.toggleColorBlindMode();

		}
	}
	public void addChangePlayerText(String p){
		changePlayerText = new JLabel("Change player Name");
		this.add(changePlayerText);
		JTextField field = new JTextField();
		field.setText(p);
		field.setEditable(true);
		this.add(field);
		changeColorDeficiency = new JLabel("Colour Deficiency Mode: ");
		this.add(changeColorDeficiency);
		colorblind = new JButton("Disabled");
		this.add(colorblind);
	}
	public void toggleColorBlindMode() {
		//TODO: actually toggle Colorblind mode on/off, this just changes the text right now.
		if (colorblindStatus == false) {
			colorblind.setText("Enabled");
			colorblindStatus = true;
		}
		else if (colorblindStatus == true) {
			colorblind.setText("Disabled");
			colorblindStatus = false;
		}

	}
}
