package Main.Managers;



import Main.Assets.Constants;
import Main.Games.GamePanel;
import Main.Games.MainMenu;
import Main.Games.MinerPuzzle.MinerMainPanel;
import Main.Games.ninestones.Interface;
import Main.Games.tigers.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Tom Sharkey and Catherine Huang on 4/15/15.
 *
 * Handles how to start new games for the user
 *
 * Will create the main menu initially for the user to select what game that they want to play
 *
 * Has a global keylistener on ESCAPE for the user to be able to escape back to the main menu
 * at any time
 *
 * EXTENDABILITY goes in here by following the TODO: tags
 *
 */
public class GameManager extends JFrame implements KeyListener{

    private GamePanel mainPanel;
    private GamePanel currentPanel;
    private JOptionPane option;
    private int currentGame;

    //ints for games to select what to play
    //TODO: EXTENDABILITY GOES HERE FOR NEXT GAMES THAT ARE MADE
    //TODO: Add a new constant int that will be used in the setGame() to allow for a new switch
    public final int MENU = 1;
    public final int MINERS = 2;
    public final int TIGERS = 3;
    public final int NINE_STONES = 4;

    public GameManager(){
        this.addKeyListener(this);
        mainPanel = new MainMenu(this);
        currentPanel = mainPanel;
        option = new JOptionPane();
        currentGame = MENU;

        makeWindow();
    }

    /**
     * will handle all of the calls to create the window
     */
    public void makeWindow(){
        //initializing the JFrame
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setVisible(true);
        this.setResizable(false);
        this.add(currentPanel);
        //re-validates and form fits everything added to the window
        this.pack();
        this.revalidate();

        //will catch key events in all panels what will be used to bring you back to the main menu
        //only checks on press, can be altered for press and release, see KeyEvent static ints
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if(e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == 27){
                            if(currentGame == MENU){
                                int x = option.showConfirmDialog(null, "Would you like to quit?", "Exit", JOptionPane.YES_NO_OPTION);
                                if(x == JOptionPane.YES_OPTION){
                                    System.exit(0);
                                }
                            }else{
                                int x = option.showConfirmDialog(null, "Would you like to quit?","Exit", JOptionPane.YES_NO_OPTION);
                                if(x == JOptionPane.YES_OPTION){
                                    setGame(MENU);
                                }
                            }
                        }
                        return false;
                    }
                });
    }

    /**
     * Will change the game that is played as chosen by the user
     * @param nextGame
     */
    public void setGame(int nextGame){
        //TODO: EXTENDABILITY GOES HERE FOR NEXT GAMES TO BE IMPLEMENTED
        //TODO: else if for the new games that have been to allow for new game options
        this.remove(currentPanel);
        //sets to menu panel
        if(nextGame == MENU){
            currentPanel = mainPanel;
            currentGame = MENU;
        }

        //TODO: change nulls to correct game classes
        //sets to a new miners game
        else if(nextGame == MINERS){
            currentPanel = new MinerMainPanel();
            currentGame = MINERS;
        }
        //sets to a new tigers game
        else if(nextGame == TIGERS){
            try{
                currentPanel = new Display();
            }catch(Exception ex){
                ex.printStackTrace();
            }

            currentGame = TIGERS;
        }
        //sets to a new nine stones game
        else if(nextGame == NINE_STONES){
            currentPanel = new Interface();
            currentGame = NINE_STONES;
        }
        //TODO: ADD NEW GAMES HERE!

        this.add(currentPanel);

        //form fits everything to the screen
        this.pack();
        this.revalidate();
        this.repaint();
    }

    //Listeners that are implemented into the project for the frame

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){

    }

    @Override
    public void keyReleased(KeyEvent e){

    }
}
