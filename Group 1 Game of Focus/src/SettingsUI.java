import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class SettingsUI extends JFrame implements MouseListener {
    private JPanel topPanel,centerPanel,bottomPanel;
    private GridSquare [][]gridsquares;
    private final Container contentPane;
    private JButton colorBlind;
    private Component rigidArea;
    private Color []colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

    public SettingsUI() {
        this.setLayout(new BorderLayout());

        // For cross platform performance.
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        }catch (Exception e) {
            e.printStackTrace();
        }

        this.setBounds(100, 100, 607, 607);
        contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

//        createMenuBar();
        createButtons();
//        createTop();

        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createButtons(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2,2));

        for (int i = 0; i < 4; i++){
            PlayerLabel lbl = new PlayerLabel();
            lbl.setBackground(colors[i]);
            lbl.setBorder(BorderFactory.createLineBorder(Color.black));
            lbl.addChangePlayerText("Player " + String.valueOf(i + 1));
            centerPanel.add(lbl);
        }
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private void ChangeInformation(){

    }



    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
