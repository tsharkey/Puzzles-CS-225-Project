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
    private GridBagLayout layout;
    private GridBagConstraints constraints;

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
        instruction = new JTextArea(30, 17);
       
        instruction.setWrapStyleWord(true);
        instruction.setFont(new Font("Serif", Font.ITALIC, 12));
        instruction.setForeground(new Color(28, 94, 161));
        instruction.setEditable(false);
        addText();
        
        gameTitle = new JLabel("Miner Puzzle");
        gameTitle.setForeground(Color.PINK);
        gameTitle.setFont(new Font("Serif", Font.BOLD, 40));
        
        addComponent(gameTitle, 0, 0, 1, 1);
        addComponent(instruction, 1, 0, 1, 2);
        addComponent(minerGamePanel, 0, 1, 1, 1);
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
        instruction.append("1) Click on MOVE button\n to move from cave to safety" + '\n');
        instruction.append("2) Click on UNDO button\n to move to the previous state" + '\n');
        instruction.append("3) Click on RESET button\n to allow to restart the puzzle" + '\n');
        instruction.append("4) Click on CLOSE button\n to exit the puzzle" + '\n'); 
        instruction.append("" + '\n');
       // instruction.append("" + '\n');
        instruction.append("GAME INFORMATION: \n \n");
        instruction.append("Welcome to the Miners\n and Minutes Puzzle! " + '\n');
        instruction.append("4 miners have been\n trapped in a cavern\n and collapse in 15 min." + '\n');
        instruction.append("Has an open tunnel\n leading to safety," + '\n');
        instruction.append("And it requires\n a latern to travel," + '\n');
        instruction.append("And the miners only\n have one working\n lantern to use." + '\n');
        instruction.append("Only 2 miners can\n travel the tunnel \nat a time with the latern. " + '\n');
        instruction.append("Four Miners are: " + '\n');
        instruction.append(" a. Onika: uninjured and\n can walk in one minute" + '\n');
        instruction.append(" b. Twitch has a limp and\n can walk in two minutes" + '\n');
        instruction.append(" c. Fiona has a broken foot\n and can walk in\n four minutes" + '\n');
        instruction.append(" d. Edward is seriously\n injured and can walk\n in eight minutes" + '\n');
        instruction.append("" + '\n');
        instruction.append("Bascially, how can\n all the miners\n get out safely?" + '\n');
        instruction.append("Time Dispayed to\n follow your pogress." + '\n');
        instruction.append("At the end, it\n will declare win\n or lose." + '\n');
        
    }
    
    

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
    public void actionPerformed(ActionEvent e) {

    }
}
