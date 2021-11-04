import javax.swing.*;
import java.awt.Color;

/**
 * An extension to the JButton class so that we can add added functionality to the Button.
 * Some simple extensions include Changing the Color of the Button depending on the
 * Player's turn.
 *
 */

public class GridSquare extends JButton{

    // Field variables for the position of the Squares.
    private int xPos;
    private int yPos;

    /**
     * Constructor for our customized JButton.
     *
     * @param xPos is the x-coordinate of the JButton.
     * @param yPos is the y-coordinate of the JButton.
     */
    public GridSquare(int xPos, int yPos){
        super();
        this.xPos  = xPos;
        this.yPos = yPos;
    }

    /**
     * Set the Background of the button depending on which player's turn it is.
     * If it is Player 1's turn, then the click on the button will make the button green.
     * Otherwise, it will make the button yellow.
     *
     * @param playerID is the Player indicator.
     */
    public void setColor(int playerID) {
        Color color = (playerID == 1) ? Color.green : Color.yellow;
        this.setBackground(color);
    }

    public void resetColor() {
        this.setBackground(null);
    }

    /**
     * Mutator method for the X-Coordinates for the button.
     * @param value
     */
    public void setXPos(int value) {
        xPos = value;
    }

    /**
     * Mutator method for the Y-Coordinates for the button.
     * @param value
     */
    public void setYPos(int value) {
        yPos = value;
    }

    /**
     * Accessor method for the X-Coordinates of the Button.
     * @return X-Coordinates of the Button.
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * Accessor method for the Y-Coordinates of the Button.
     * @return y-Coordinates of the Button.
     */
    public int getYPos() {
        return yPos;
    }

}