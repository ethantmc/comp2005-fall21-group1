import javax.swing.*;
import java.awt.*;
import javax.swing.JTextField;


public class PlayerLabel extends JPanel {

    private JButton colorblind;
    private Container contentPane;
    private int playerID;
    private JLabel changePlayerText,changeColorDeficiency;

    public PlayerLabel() {
        super();
        this.setLayout(new GridLayout(4, 1));
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
        colorblind = new JButton("Disbaled");
        this.add(colorblind);
    }
}
