package Main.Games.tigers;

import Main.Main;
import Main.Managers.ErrorManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * The Trial class is responsible for an individual trial in the puzzle.
 * A Trial has two Door objects and one Jailer object. 
 * It extends JPanel, so that it can be added to the main JPanel of the game. 
 * 
 * @author (Hannah, Henrique, Maria) 
 * @version (2015-04-20)
 */

public class Trial extends JPanel
{
  
  private JLabel trialLabel; 
  private Door doorOne, doorTwo;
  private Jailer jailer;
  private Image img1,img2;
  private int trialNum;
    private BufferedImage img1Buffer,
                          img2Buffer;
  
  /**
   * Constructor for objects of type Trial. 
   */
  public Trial(Door d1, Door d2, Jailer j, int n) {
    trialNum = n;
    setLayout(new BorderLayout()); 
    trialLabel = new JLabel("Trial No. " + trialNum);
    trialLabel.setForeground(Color.getHSBColor(50, 100, 25));
    trialLabel.setFont(new Font("Sans-serif", Font.BOLD, 12));

      img1Buffer = null;
      img2Buffer = null;

      try{
          img1Buffer = ImageIO.read(getClass().getResourceAsStream("/Main/Games/tigers/images/prison.png"));
          img2Buffer = ImageIO.read(getClass().getResourceAsStream("/Main/Games/tigers/images/prison.png"));

      }catch(Exception e){
          new ErrorManager(e.getMessage(), Main.staticGameManager);
      }

    doorOne = d1;
    doorTwo = d2;
    jailer = j; 
    
    // add objects to panel
    addComponents(); 
  }
  
  /**
   * Add components to trial panel.
   */
  public void addComponents() {

    // add items to center panel
    final ImageIcon img = new ImageIcon(img1Buffer);
   JPanel centerPanel = new JPanel(){

    protected void paintComponent(Graphics g) {
           super.paintComponent(g);
           img1 = img.getImage();
           g.drawImage(img1, 0,0, null);
       };
   };
    
    // add items to center panel
    centerPanel.add(doorOne);
    centerPanel.add(doorTwo);
    centerPanel.add(jailer);
    
    // add center panel to trial panel
    add(centerPanel, BorderLayout.CENTER);
       final ImageIcon img3 = new ImageIcon(img2Buffer);

    JPanel topPanel = new JPanel(new BorderLayout()){ 
       
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            img2 = img3.getImage();
            g.drawImage(img2, 0,0, null);
          
        };
    };
    topPanel.add(trialLabel, BorderLayout.CENTER);
    
    // add left panel in order to center label
    JPanel lPanel = new JPanel();
    
    // change 800 to constant
    int westSize = (int) ((800 - trialLabel.getPreferredSize().width)/2); 
    lPanel.setPreferredSize(new Dimension(westSize, 5));
    topPanel.add(lPanel, BorderLayout.WEST);
    lPanel.setOpaque(false);
    topPanel.setOpaque(false);
    
    // add top panel in north
    add(topPanel, BorderLayout.NORTH);
  }
  
  /**
   * Set text of the jailer.
   * 
   * @param  text  new text to be given to jailer
   */
  public void setJailerTrialText(String text) {
    jailer.setTrialText(text);
  }
  
  /**
   * Set text of the jailer.
   * 
   * @param  text  new text to be given to jailer
   */
  public void setJailerStartText(String text) {
    jailer.setStartText(text);
  }
  
  /**
   * Set the text of the given door. 
   * 
   * @param  text  the text for the door
   * @param  doorNum  which door to give the text to
   */
  public void setDoorText(String text, int doorNum) {
    // set the text of the specified door
    if (doorNum == 1) {
      doorOne.setText(text);
    } else
    {
      doorTwo.setText(text);
    }
  }
  
  /**
   * Set whether or not the given door has a lover behind it.
   * 
   * @param  hasLover  true if lover should be behind door, otherwise false
   * @param  doorNum  which door to pass information to
   */
  public void setDoorHasLover(boolean hasLover, int doorNum) {
     // set if the specified door has a lover or tiger behind it
    if (doorNum == 1) {
      doorOne.setHasLover(hasLover);
    } else
    {
      doorTwo.setHasLover(hasLover);
    }
  }
  
  /**
   * Reveal what is behind each door in trial.
   */
  public void showBehindDoors() {
    doorOne.showBehindDoor();
    doorTwo.showBehindDoor(); 
  }
  
  /**
   * Returns true if both doors have tigers behind them.
   * 
   * @return  true if both doors have tigers, otherwise return false
   */
  public boolean bothHaveTigers() {
    if (doorOne.hasLover() || doorTwo.hasLover()) {
      return false; 
    } else {
      return true; 
    }
  }
  
  /**
   * Reset both doors to original states. 
   */
  public void resetDoors() {
    doorOne.resetDoor();
    doorTwo.resetDoor();
  }
}
