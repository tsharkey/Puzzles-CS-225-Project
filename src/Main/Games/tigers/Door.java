package Main.Games.tigers;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;


/**
 * Door Class is part of the implementation of the Tiger Puzzle application
 * creates a label with a image call door, each object door will be able to draw itself
 * and each door will display text related to that door. The door will also have a label
 * that marks the door as door #1 or door #2. 
 * 
 * @author (Maria, Henrique, Hannah) 
 * @version (2015-04-16)
 */
public class Door extends JPanel
{
    // text area where the statements are printing 
    private JTextArea txtArea;

    // private JLabel label1,label2;
    private JTextArea text1, trialArea;
    
    // if the lover is behind of the door
    private boolean hasLover;
    
    //variable to hold the images;
    private ImageIcon doorImage;
    private ImageIcon tigerImage;
    private ImageIcon loverImage;

    //bufferedImages
    private BufferedImage doorbuffer;
    private BufferedImage tigerBuffer;
    private BufferedImage loveBuffer;
    
    //label for each of the images
    private JLabel label2;

    private JLabel doorLabel;
    private JButton d1;
    private Image img;
    private Sound sound;
    
    // door number label string
    private String labelText;
    
    private boolean doorClicked;
    
    private Display display;
    
    
    /**
     * Constructor for objects of class Door. 
     */
    public Door(Display d, int num)
    {
      // default information
      hasLover = false;
      doorClicked = false;
      
      display = d;
      
       // 
       String path = "/Main/Games/tigers/images/door" + num + ".gif";
       doorbuffer = null;
       tigerBuffer = null;
       loveBuffer = null;
       try {
           doorbuffer = ImageIO.read(getClass().getResourceAsStream(path));
           doorImage = new ImageIcon(doorbuffer);
           tigerBuffer = ImageIO.read(getClass().getResourceAsStream("/Main/Games/tigers/images/tigerImage.gif"));
           tigerImage = new ImageIcon(tigerBuffer);
           loveBuffer = ImageIO.read(getClass().getResourceAsStream("/Main/Games/tigers/images/loverImage.gif"));
           loverImage = new ImageIcon(loveBuffer);
       }
       catch (Exception e) {
           System.out.println("Image file not found");
       }

       // door button specs
       d1 = new JButton(doorImage);
       d1.setOpaque(false);
       d1.setContentAreaFilled(false);
       d1.setBorderPainted(false);
       d1.setFocusPainted(false);

       doorLabel = new JLabel("");
       doorLabel.setOpaque(false);
       doorLabel.setForeground(Color.getHSBColor(50, 100,25));

       // text area specs
       txtArea = new JTextArea();
       txtArea.setFont(new Font("Sans-Serif", Font.BOLD, 10));
       txtArea.setEditable(false);
       txtArea.setOpaque(false);
       txtArea.setBackground(Color.black);
       txtArea.setForeground(Color.getHSBColor(50, 100,25));
       txtArea.setBounds(10, 10, 50, 110);
       txtArea.setWrapStyleWord(true);
       txtArea.setLineWrap(true);
       txtArea.setSize(30, 100);
        
       // add bottom panel
       JPanel bottomPanel = new JPanel();
       bottomPanel.setOpaque(false);
       bottomPanel.add(doorLabel);
       bottomPanel.add(txtArea);
       
       // set Layout and arrange components and transparency of panel
       setLayout(new BorderLayout());
       setOpaque(false);
        
       // add components to layout
       add(d1, BorderLayout.NORTH);
       d1.addActionListener(new doorButton());
       add(bottomPanel, BorderLayout.SOUTH);
       setBackground(Color.black);
    }

    /**
     * Draws a different image when the door is opened. 
     * It is done by making the door a button.
     * Also play the corresponding sound.
     */
    private class doorButton implements ActionListener { 
       
      Sound sound = new Sound();

      public void actionPerformed(ActionEvent evt) {

        if(hasLover) {
          label2 = new JLabel(loverImage);
          add(label2,BorderLayout.NORTH);
          //remove(d1);
          d1.setVisible(false);
          //calls the sound method in Sound Class
//          try {
//             sound.playSound("love");
//          } catch (LineUnavailableException ex) {
//            Logger.getLogger(Door.class.getName()).log(Level.SEVERE, null, ex);
//          }
          
          // update score
          if (!doorClicked) {
            display.increaseScore(true);
          }
          
          revalidate();
          repaint();
        } else {
          label2 = new JLabel(tigerImage);
          add(label2,BorderLayout.NORTH);
          d1.setVisible(false);
//          try {
//            sound.playSound("tiger");
//          } catch (LineUnavailableException ex) {
//             Logger.getLogger(Door.class.getName()).log(Level.SEVERE, null, ex);
//          }
          
          // update score
          if (!doorClicked) {
            display.increaseScore(false);
          }
          
        revalidate();
        repaint();
        }
        
        // the door has been clicked
        doorClicked = true;
     }
    }
    
    /**
     * Display what is behind the door, but do not update the score. 
     * Also play the corresponding sound.
     */
    public void showBehindDoor() {
      Sound sound = new Sound();
      
       if(hasLover) {
          label2 = new JLabel(loverImage);
          add(label2,BorderLayout.NORTH);
          d1.setVisible(false);
          //calls the sound method in Sound Class
//          try {
//             sound.playSound("love");
//          } catch (LineUnavailableException ex) {
//            Logger.getLogger(Door.class.getName()).log(Level.SEVERE, null, ex);
//          }
          revalidate();
          repaint();
        } else {
          label2 = new JLabel(tigerImage);
          add(label2,BorderLayout.NORTH);
          d1.setVisible(false);
//          try {
//            sound.playSound("tiger");
//          } catch (LineUnavailableException ex) {
//             Logger.getLogger(Door.class.getName()).log(Level.SEVERE, null, ex);
//          }
        revalidate();
        repaint();
        }
        // the door has been shown
        doorClicked = true;
    }
    
    /**
     * Sets the text underneath the door.
     * 
     * @param  doorText  the text to set beneath the door
     */
    public void setText(String doorText)
    {
        add(txtArea);
        txtArea.append(doorText);
    }
    
    /**
     * Sets whether or not the door has a lover or tiger behind it.
     * 
     * @param  b  whether or not the door has a lover behind it
     */
    public void setHasLover(boolean b) 
    {
        hasLover = b; 
    }
    
    /**
     * Returns whether or not the door has a lover behind it.
     * 
     * @return  true if lover behind door, otherwise false
     */
    public boolean hasLover() {
      return hasLover;
    }
    
    /**
     * Returns whether or not the door has been clicked already.
     * 
     * @return  true if door clicked, otherwise false
     */
    public boolean wasClicked() {
      return doorClicked; 
    }
    
    /**
     * Reset door to original state.
     */
    public void resetDoor() {
      
      // reset image
      if (doorClicked) {
        remove(label2);
        add(d1, BorderLayout.NORTH);
        d1.setVisible(true);
      }
      
      // reset door
      doorClicked = false;
    }
}