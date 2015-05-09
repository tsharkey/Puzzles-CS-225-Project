package Main.Games.tigers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.*;

public class Jailer extends JPanel{
    private JLabel label1;
    private JTextArea startArea, trialArea;
    private BufferedImage img1 = null;
    
/**
 * The Jailer class contains the information involving the jailer: it can draw itself,
 * it has a place to put his opening speech and has a place to put the speech he says 
 * relating to each trial.
 * 
 * @author (Henrique, Maria, Hannah) 
 * @version (2015-04-16)
 */
    public Jailer(){
      setBackground(Color.black);
      
       try {
         img1 = ImageIO.read(new File(getClass().getResource("images/master_2.gif").toURI()));
       }
       catch (Exception e) {
         System.out.println("Jailer image file not found");
       }
      label1 = new JLabel(new ImageIcon(img1));
        
      startArea = new JTextArea("");
      trialArea = new JTextArea(""); 
        
      startArea.setFont(new Font("Sans-Serif", Font.PLAIN, 11));
      startArea.setForeground(Color.getHSBColor(50, 100,25));
      startArea.setOpaque(false);
      startArea.setEditable(false);
      startArea.setBounds(10, 10, 50, 110);
      startArea.setWrapStyleWord(true);
      startArea.setLineWrap(true);
      startArea.setSize(300, 300); 
      
      trialArea.setFont(new Font("Sans-Serif", Font.BOLD, 12));
      trialArea.setOpaque(false);
      trialArea.setForeground(Color.getHSBColor(50, 100,25));
      trialArea.setEditable(false);
      trialArea.setWrapStyleWord(true);
      trialArea.setLineWrap(true);
      trialArea.setBounds(10, 10, 50, 110);
      trialArea.setSize(300, 450); 
        
      // set Layout and arrange components
      setLayout(new BorderLayout());
      add(startArea, BorderLayout.NORTH);
      add(label1, BorderLayout.CENTER);
      setOpaque(false);
      add(trialArea, BorderLayout.SOUTH);
     }
    
    /**
     * Sets the trial text of the jailer.
     * 
     * @param  speech  the trial speech text
     */
    public void setTrialText(String speech) {
      trialArea.append(speech); 
    }
    
    /**
     * Sets the start text of the jailer.
     * 
     * @param  speech  the start speech text
     */
    public void setStartText(String speech) {
      startArea.append(speech);
    }
}