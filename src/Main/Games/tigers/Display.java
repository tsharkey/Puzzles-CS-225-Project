package Main.Games.tigers;

import Main.Games.GamePanel;
import Main.Assets.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.LineUnavailableException;
import java.util.Scanner;


/**
 * The Display class, which extends the GamePanel class, is in charge of putting all the 
 * components of the tiger puzzle together. It combines a panel with the view of the 
 * doors and jailer, a title panel and panels with buttons and directions to create 
 * a fully functional game. The buttons listen for click events to allow the game to be 
 * interactive. 
 * 
 * @author (Hannah, Henrique, Maria) 
 * @version (2015-04-20)
 */

public class Display extends GamePanel {
  // keep track of trials
  private ArrayList<Trial> trials;
  private int currentIndex; 
  private int trialNum;
  private int loverNum;
  private int numCorrect;
  private boolean chosenAnswer;
 
  // components
  private JPanel southPanel, centerPanel;
  private JTextArea directions;
  private Image img1,img2;
  private JPanel centerLower; 
  private JLabel resultsText;
  private JLabel scoreLabel;
  
  private Sound sound;

  /**
   * Constructor for objects of type Trial. 
   * 
   * @throws javax.sound.sampled.LineUnavailableException
   */
  public Display() throws LineUnavailableException {
    trials = new ArrayList<Trial>(); 
    currentIndex = 0; 
    numCorrect = 0;
    chosenAnswer = false;
    
    setComponents(); 

    // read file to create trials
    readFile("text/tigerText.txt");
    
    //plays sound effects
    sound = null;
    sound = new Sound();
    //sound.playSound("cave");
    
    // display center panel
    createCenter(); 
  
    // set size
    setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
  }
  
  /**
   * Read the file at the given path to add puzzle text.
   * 
   * @param  fileString  path of file
   */
  public void readFile(String fileString) {
    File file = null;
    String resource = "text/tigerText.txt";
    URL res = getClass().getResource(resource);
    if (res.toString().startsWith("jar:")) {
      try {
        InputStream input = getClass().getResourceAsStream(resource);
        file = File.createTempFile("tempfile", ".tmp");
        OutputStream out = new FileOutputStream(file);
        int read;
        byte[] bytes = new byte[1024];

        while ((read = input.read(bytes)) != -1) {
          out.write(bytes, 0, read);
        }
        file.deleteOnExit();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }else {
      //this will probably work in your IDE, but not from a JAR
      file = new File(res.getFile());
    }
     //File file = new File(fileString);
     Scanner scanner = null;
     
     //access file
     try {
       scanner = new Scanner(file);

     } catch (Exception e) {
       System.out.println("Text file not found.");
         e.printStackTrace();
     }
     
     // read line by line
     scanner.useDelimiter(System.getProperty("line.separator"));
     
     String line ="";
     String directText = "";
     String jStartText = "";
     
     // find directions start marker
     do {
     // if more lines in file, go to next line
       if (scanner.hasNext()) {
         line = scanner.nextLine();
       } else
       {
         break;
       }
     } while (!line.equals("DSTART"));
     
     // until end of directions found
     while (scanner.hasNext() && !line.equals("DEND")) {
       line = scanner.nextLine();
       // add more directions text
       if (!line.equals("DEND")) {
         directText += line;
       }
     }
     
     // set directions
     directions.append(directText);
     
     // find jailer start speech marker
     do {
     // if more lines in file, go to next line
       if (scanner.hasNext()) {
         line = scanner.nextLine();
       } else
       {
         break;
       }
     } while (!line.equals("JSTART"));
     
     // until end of jailer speech found
     while (scanner.hasNext() && !line.equals("JEND")) {
       line = scanner.nextLine();
       // add more of jailer's text
       if (!line.equals("JEND")) {
         jStartText += line;
       }
     }
     // find trial start marker
     do {
       // if more lines in file, go to next line
       if (scanner.hasNext()) {
         line = scanner.nextLine();
       } else
       {
         break;
       }
     } while (!line.equals("TSTART"));
     
     // get number of trials
     trialNum = Integer.parseInt(scanner.nextLine());
     int num = trialNum + 1;
     loverNum = Integer.parseInt(scanner.nextLine());
     
     // for every trial
     for (int i = 1; i < num; i++) {
       
       // create new trial
       Trial trial = new Trial(new Door(this, 1), new Door(this, 2), new Jailer(), i);
       
       // set door text
       trial.setDoorText(scanner.nextLine(), 1);
       trial.setDoorText(scanner.nextLine(), 2);
       
       // set jailer text
       trial.setJailerStartText(jStartText);
       trial.setJailerTrialText(scanner.nextLine());
       
       // set what is behind each door
       trial.setDoorHasLover(Boolean.parseBoolean(scanner.nextLine()), 1);
       trial.setDoorHasLover(Boolean.parseBoolean(scanner.nextLine()), 2);

       // add trial to list
       trials.add(trial);
     }
  }
  
  /**
   * Adds and sets the components in the display panel. 
   */
  public void setComponents() {
    setLayout(new BorderLayout());
    
    createTitle();
    
    // create south panel
    southPanel = new JPanel();
    southPanel.setBackground(Color.black);
    southPanel.setLayout(new BorderLayout());
    southPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    // create directions
    createDirections(); 
    
    // create buttons
    createButtons();
    
    // add south panel to main panel
    add(southPanel, BorderLayout.SOUTH);
  }
  
  /**
   * Creates the center panel that contains the trial panel 
   * a panel below that with scoring information.
   */
  public void createCenter() {
    centerPanel = new JPanel(new BorderLayout());
    
    // display trial
    centerPanel.add(trials.get(currentIndex), BorderLayout.CENTER);
    
    // create components for bottom half of center panel
    
    // total trials markers
    JLabel totalLabel = new JLabel("Total trials: " + trialNum);
    totalLabel.setForeground(Color.getHSBColor(50, 100, 25));
    totalLabel.setFont(new Font("Sans-Serif", Font.BOLD, 12));
    
    // results text
    resultsText = new JLabel("Will you choose right?");
    resultsText.setForeground(Color.getHSBColor(50, 100, 25));
    resultsText.setFont(new Font("Sans-Serif", Font.BOLD, 12));
   
    // score label
    scoreLabel = new JLabel("Correct: 0/" + loverNum);
    scoreLabel.setFont(new Font("Sans-Serif", Font.BOLD, 10));
    scoreLabel.setForeground(Color.green );
    
    // button that allows player to choose neither button
    JButton refuseButton = new JButton("I choose Neither!");
    
    // refuse button listens for click events
    refuseButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          
          // increase score if both doors have tigers behind them
          if (trials.get(currentIndex).bothHaveTigers()) {
            resultsText.setText("Yes-- both doors have tigers!");
            increaseScore(true);
          } else {
            resultsText.setText("No-- a lover is behind a door!");
          }
          chosenAnswer = true; 
        }
      });
    
    // set center lower panel
    centerLower = new JPanel();
    centerLower.setBackground(Color.darkGray);
    centerLower.add(totalLabel);
    centerLower.add(refuseButton);
    centerLower.add(resultsText);
    centerLower.add(scoreLabel);
    
    // add center panels to display
    centerPanel.add(centerLower, BorderLayout.SOUTH);
    add(centerPanel, BorderLayout.CENTER);
  }
  
  /**
   * Creates the buttons. 
   */
  private void createButtons() {
    // buttons in own panel in south
    JPanel btnPanel = new JPanel();
    btnPanel.setBackground(Color.black);
       
    btnPanel.setLayout(new FlowLayout()); 
    JButton nextButton = new JButton("Next Trial");
    
    // add event listent to "next trial" button
    nextButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          
          // check if another trial
          if (!(currentIndex + 1 >= trials.size()))
          {
            chosenAnswer = false;
            
            resultsText.setText("Will you choose right?");

            centerPanel.remove(trials.get(currentIndex));
            
            // increment current index
            currentIndex++;
            
            centerPanel.add(trials.get(currentIndex), BorderLayout.CENTER);
            revalidate();
            repaint();
          }
        }
      });
    
    // add buttons to panels
    btnPanel.add(nextButton);
    southPanel.add(btnPanel, BorderLayout.SOUTH);

    JButton startOverButton = new JButton("Start Over");
    
    // add event listent to "start Over" button
    startOverButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
      // reset info
      numCorrect = 0;
      chosenAnswer = false;
      scoreLabel.setText("Score: " + numCorrect + "/" + loverNum);
      resultsText.setText("Will you choose right?");
      
      // reset doors in every trial
      for (Trial trial : trials) {
        trial.resetDoors();
      }
      
      // display first trial
      centerPanel.remove(trials.get(currentIndex));
      currentIndex = 0;
      centerPanel.add(trials.get(currentIndex), BorderLayout.CENTER);
      revalidate();
      repaint();
    }
  });
    
  // add buttons to panels
  btnPanel.add(startOverButton);
  southPanel.add(btnPanel, BorderLayout.SOUTH);
  
  JButton showAnswerButton = new JButton("Show Behind Doors");
  
  // add event listent to "show answer" button
  showAnswerButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
      
      // show what is behind each door
      trials.get(currentIndex).showBehindDoors(); 
      revalidate();
      repaint();
    }
  });

  // add buttons to panels
  btnPanel.add(showAnswerButton);
  southPanel.add(btnPanel, BorderLayout.SOUTH);
}

  /**
   * Adds the directions to the puzzle.
   */
  private void createDirections() {
    
    // add directions label
    JLabel directTitle = new JLabel("DIRECTIONS:");
    directTitle.setFont(new Font("Sans-serif", Font.BOLD, 15));
    directTitle.setForeground(Color.getHSBColor(50, 100,55));
    southPanel.add(directTitle, BorderLayout.NORTH);
    
    // set directions styling
    directions = new JTextArea("");
    directions.setEditable(false);
    directions.setWrapStyleWord(true);
    directions.setLineWrap(true);
    directions.setBackground(Color.black);
    directions.setForeground(Color.getHSBColor(50, 100, 25));
  
    // add directions to south panel
   southPanel.add(directions, BorderLayout.CENTER); 
  }
  
  /**
   * Adds the title to the puzzle.
   */
  private void createTitle() {
    //JPanel northPanel = new JPanel();
    BufferedImage img4Buffer = null;
    try{
      img4Buffer = ImageIO.read(getClass().getResourceAsStream("/Main/Games/tigers/images/prison.png"));
    }catch(Exception e){
      e.printStackTrace();
    }
     final ImageIcon img4 = new ImageIcon(img4Buffer);
    JPanel northPanel = new JPanel(){ 
       
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            img2 = img4.getImage();
            g.drawImage(img2, 0,0, null);
          
        };
    };
    
    // create title
    JLabel title = new JLabel("The Tiger Puzzle");
    title.setFont(new Font("Sans-serif", Font.BOLD, 22));
    title.setForeground(Color.getHSBColor(50, 100,25));
   
    // add to north panel
    northPanel.add(title);
    add(northPanel, BorderLayout.NORTH); 
  }
  
  /**
   * Increase the player's score.
   * 
   * @param  b  whether or not score should be increased. 
   */
  public void increaseScore(boolean b) {

    // do not increase score if answer already chosen.
    if (chosenAnswer) {
      return;
    }
    
    if (b) {
      numCorrect++;
      scoreLabel.setText("Score: " + numCorrect + "/" + loverNum);
    } else {
      chosenAnswer = true;
    }
    
    // check if on final trial
    if (currentIndex >= trialNum - 1) {
      
      // check if passed all trials or not
      if (numCorrect >= loverNum) {
        resultsText.setText("You passed all the trials! You are released from the dungeon!");
      } else {
        resultsText.setText("You did not pass all the trials! You are still trapped in the dungeon!");
      }       
   }
  }
    /**
     * All below necessary because extends GamePanel.
     */
    @Override
    public void actionPerformed(ActionEvent e){

    }

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
