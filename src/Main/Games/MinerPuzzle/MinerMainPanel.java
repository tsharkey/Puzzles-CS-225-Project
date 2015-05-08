package Main.Games.MinerPuzzle;

import Main.Games.GamePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *  Creates the Main Panel for the Miners, which sets the instruction/Game Info for the game
 * 
 * @author Viet Dinh, Parth Patel
 */

public class MinerMainPanel extends GamePanel {

    //instance varaibles
    private JLabel gameTitle;
    private MinerGamePanel minerGamePanel;
    private JTextArea instruction;
    private GridBagLayout layout;//apply gridbag layout for this panel
    private GridBagConstraints constraints;//apply gridbag constraints for this panel

    // Sets the constructor for the Miner Main Panel for the puzzle
    public MinerMainPanel() 
    {    
        Miner.LaternInSafeZone = false;
        Miner.orignalTime = 15;
        MinerGUI.number_ready_to_go = 0;
        MinerGUI.undoList.clear();
        MinerGUI.timeList.clear();
        
        layout = new GridBagLayout();
        setLayout(layout);
        constraints = new GridBagConstraints();
        this.setAlignmentY(TOP_ALIGNMENT);
        this.setAlignmentX(RIGHT_ALIGNMENT);

        minerGamePanel = new MinerGamePanel();
        instruction = new JTextArea();
        instruction.setPreferredSize(new Dimension(800,450));
       
        instruction.setWrapStyleWord(true);
        instruction.setFont(new Font("Serif", Font.ITALIC, 18));
        instruction.setForeground(new Color(28, 94, 161));
        instruction.setEditable(false);
        this.addText();
        
        gameTitle = new JLabel("Miner Puzzle");
        gameTitle.setForeground(Color.PINK);
        gameTitle.setFont(new Font("Serif", Font.BOLD, 40));
        
        this.addComponent(gameTitle, 0, 0, 1, 1);
        this.addComponent(instruction, 0, 1, 1, 1);
        this.addComponent(minerGamePanel, 0, 2, 1, 1);
    }

    /**
     * This method used to add elements to this panel, using the showing rules
     * of gridbag layout
     *
     * @param component
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void addComponent(Component component, int x, int y, int width, int height) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        layout.setConstraints(component, constraints);
        add(component);
    }
    
    /**
     * Adds the Game Instructions/ Game Info text to the panel
     */
    public void addText()
    {
        instruction.append("INSTRUCTIONS: \n \n");
        instruction.append("Frist select the Miners, then"+ '\n');
        instruction.append("1) Click on MOVE button to move from cave to safety" + '\n');
        instruction.append("2) Click on UNDO button to move to the previous state" + '\n');
        instruction.append("3) Click on RESET button to allow to restart the puzzle" + '\n');
        instruction.append("4) Click on CLOSE button to exit the puzzle" + '\n'); 
        instruction.append("" + '\n');
       // instruction.append("" + '\n');
        instruction.append("GAME INFORMATION: \n \n");
        instruction.append("Welcome to the Miners and Minutes Puzzle! " + '\n');
        instruction.append("4 miners have been trapped in a cavern and collapse in 15 minutes." + '\n');
        instruction.append("There is an open tunnel leading to safety," + '\n');
        instruction.append("And it requires a latern to travel,");
        instruction.append("And the miners only have one working lantern to use." + '\n');
        instruction.append("Only 2 miners can travel the tunnel at a time with the latern. " + '\n');
        instruction.append("Four Miners are: " + '\n');
        instruction.append(" a. Onika: uninjured and can walk in one minute" + '\n');
        instruction.append(" b. Twitch has a limp and can walk in two minutes" + '\n');
        instruction.append(" c. Fiona has a broken foot and can walk in four minutes" + '\n');
        instruction.append(" d. Edward is seriously injured and can walk in eight minutes" + '\n');
        instruction.append("" + '\n');
        instruction.append("Bascially, how can all the miners get out safely?");
        instruction.append("Time Dispayed to follow your pogress." + '\n');
        instruction.append("At the end, it will declare win or lose.");
    }
    
    
    /**
     *  This game does not use following methods.
     *  Then they are not overrided.
     * @param e
    */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

//    @Override
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
