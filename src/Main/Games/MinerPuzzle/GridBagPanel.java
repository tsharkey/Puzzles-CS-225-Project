package Main.Games.MinerPuzzle;

import java.awt.*;
import javax.swing.JPanel;
/**
 * The GridBagPanel Class is a part of implementation of the “Logic Puzzle” game.
 * This class is a Panel that extends the JPanel Class, it organizes all the elements of the matrix
 * by GridBagLayout and It has a method addComponent() for adding elements at the panel
 * 
 * @author Viet Dinh
 */
public abstract class GridBagPanel extends JPanel
{
    private GridBagLayout layout;
    protected GridBagConstraints constraints;

    /**
     * Constructor for objects of class GridBagPanel
     */
    public GridBagPanel()
    {
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();
        this.setAlignmentY(TOP_ALIGNMENT);
        this.setAlignmentX(RIGHT_ALIGNMENT);
    }

    /**
     * This method used to add elements to this panel, using the showing rules of gridbag layout
     * @param component
     * @param x
     * @param y
     * @param width
     * @param height    */
    public void addComponent(Component component, int x, int y, int width, int height)
    {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        layout.setConstraints(component, constraints);
        add(component);
    }
 
}

