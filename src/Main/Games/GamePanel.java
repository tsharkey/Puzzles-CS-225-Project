package Main.Games;


import Main.Assets.Constants;
import Main.Managers.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Created by Tom Sharkey and Catherine Huang on 4/15/15.
 *
 * IMPORTANT!!!
 * Sets the panel size for the game and also gives it focus for the
 * Action Events that will be passed through
 *
 *
 * !!!********************************************************************************!!!
 *    DANGER: Do not use the key ESCAPE as this is a global key press that will always
 *    bring you back to the main menu
 */
public abstract class GamePanel extends JPanel implements KeyListener, ActionListener{

    //pass the manager for all games to change states
    protected GameManager manager;
    //check to see if the game threads are finished
    protected boolean areThreadsDone;

    public GamePanel() {
        areThreadsDone = true;

    }

    /**
     * Implement this constructor
     * @param manager
     */
    public GamePanel(GameManager manager){
        this.manager = manager;
        areThreadsDone = true;
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        setFocusable(true);


    }

    public void startSound(){

    }

    public void stopSound(){
        
    }
}
