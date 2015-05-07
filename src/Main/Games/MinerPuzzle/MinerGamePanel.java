package Main.Games.MinerPuzzle;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *  Creates the Miner Game Panel which creates the buttons and adds to the panel
 *
 * @author vietdinh
 */
public class MinerGamePanel extends GridBagPanel implements ActionListener {

    // instane variable
    private MinerGUI minerGUI;
    // static variables
    public static JLabel time;// show time left for all people to excape to safe place
    public static JButton move, undo, close, reset;
    public static JLabel message = new JLabel();// show the game messages when players try to do something not allowed

    //Sets the constructor for the Game Panel
    public MinerGamePanel() {
        
        message.setForeground(Color.red);
        message.setFont(new Font("Serif", Font.BOLD, 20));

        time = new JLabel();
        time.setForeground(Color.BLUE);
        time.setFont(new Font("Serif", Font.BOLD, 15));
        time.setText(Miner.orignalTime + " minute(s) left");

        minerGUI = new MinerGUI();
        move = new JButton("Move");
        undo = new JButton("Undo");
        close = new JButton("Close");
        reset = new JButton("Reset");

        move.setEnabled(false);
        undo.setEnabled(false);
        close.setEnabled(true);
        reset.setEnabled(false);

        minerGUI.setPreferredSize(new Dimension(500, 220));

        addComponent(time, 0, 0, 3, 1);
        addComponent(minerGUI, 0, 1, 3, 1);
        addComponent(message, 0, 2, 3, 1);
        addComponent(move, 0, 3, 3, 1);
        super.constraints.insets = new Insets(0, 50, 0, 50);
        addComponent(reset, 0, 4, 1, 1);
        addComponent(undo, 1, 4, 1, 1);
        addComponent(close, 2, 4, 1, 1);

        move.addActionListener(this);
        reset.addActionListener(this);
        undo.addActionListener(this);
        close.addActionListener(this);
    }

    /**
     * Override the action Performed method,
     * To set the Listeners for the four buttons,
     * Move, Reset, Undo and Close
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {   //move button
        if (e.getSource() == move)
        {
            minerGUI.saveState();
            minerGUI.update_locations();
            move.setEnabled(false);
        }   
        //reset button
        else if (e.getSource() == reset)
        {
            move.setEnabled(false);
            Miner.orignalTime = 15;
            Miner.LaternInSafeZone = false;
            time.setText(Miner.orignalTime + " minute(s) left");
            minerGUI.reset();
            MinerGUI.undoList.clear();
            MinerGUI.timeList.clear();
            MinerGUI.number_ready_to_go = 0;
        }   
        //undo button
        else if (e.getSource() == undo) 
        {
            if (MinerGUI.timeList.size() > 0) {
                Miner.orignalTime = MinerGUI.timeList.get(MinerGUI.timeList.size() - 1);
                time.setText(Miner.orignalTime + " minute(s) left");
                MinerGUI.timeList.remove(MinerGUI.timeList.size() - 1);
                minerGUI.setPreState();
            }
        }   
        //close button
        else if(e.getSource() == close)
        {
            Main.Main.staticGameManager.setGame(1);
            Miner.LaternInSafeZone = false;
            Miner.orignalTime = 15;
            MinerGUI.undoList.clear();
            MinerGUI.timeList.clear();
            MinerGUI.number_ready_to_go = 0;
        }
    }

}
