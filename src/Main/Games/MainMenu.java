package Main.Games;


import Main.Assets.Constants;
import Main.Main;
import Main.Managers.GameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Tom Sharkey and Catherine Huang on 4/15/15.
 *
 * The main menu for the games that will have a display for all of the games
 * that are currently available for the user to play.
 *
 */
public class MainMenu extends GamePanel{

    GameManager manager;

    private JScrollPane scrollPane;
    private JPanel imagePane;
    private JButton button;
    private BufferedImage img;
    //TODO: EXPANDABLE read from text file, make sure to add new files and text into appropriated areas.
    private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    private ArrayList<String> gameTexts = new ArrayList<String>();
    private ArrayList<JButton> buttons = new ArrayList<JButton>();

    /**
     * Constructor of the Main Menu to list all the playable games.
     * @param manager
     */
    public MainMenu(GameManager manager){
        //setup the MainPanel
        super(manager);
        this.manager = manager;

        loadInfos();
        createImagePane();
        scrollPane = new JScrollPane(imagePane);

        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);

        this.add(scrollPane, BorderLayout.CENTER);

    }

    /**
     * Create the Game list panel
     */
    private void createImagePane() {
        //make image panel, makes enough row for each game
        imagePane = new JPanel(new GridLayout(images.size(),1));

        for(int i = 0; i < images.size(); i++) {
            ImageIcon icon = new ImageIcon(images.get(i));
            button = new JButton(icon);
            button.setPreferredSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()));
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
            button.addActionListener(this);
            buttons.add(button);
        }

        //
        for(int i = 0; i < buttons.size(); i++) {
            imagePane.add(buttons.get(i));
            imagePane.add(new JLabel(gameTexts.get(i)));
        }
    }

    /**
     * Load the images and info for the imagepane from file
     */
    private void loadInfos() {

        try {
            //scan through the text file for image names and text's label
            Scanner scan = new Scanner(new File("./src/Main/Assets/text.txt"));

            while (scan.hasNext()) {
                String temp = scan.nextLine();
                //check if it's the image file's name
                if (temp.charAt(0) == 'i') {
                    try {
                        img = ImageIO.read(new File("./src/Main/Assets/images/" + temp.substring(2) + ".png"));
                        images.add(img);
                    } catch (Exception e) {
                        System.out.println("Image not found");
                    }
                }
                else {
                    gameTexts.add(temp);
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.out.print("Text file not found");
        }

    }

    /**
     * ActionListner for the buttons, changes states when images are clicked.
     * @param e
     * TODO: EXPENDABLE: add more games' button listeners to switch to
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //when click on the buttons, will switch to the assigned game
        if(e.getSource() == buttons.get(0)){
            manager.setGame(2);
        }
        else if(e.getSource() == buttons.get(1)) {
            System.out.println("To Nine Stones");
//            manager.setGame(3);
        }
        else if(e.getSource() == buttons.get(2)) {
            System.out.println("To Tigers and True Love");
//            manager.setGame(4);
        }

    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyReleased(KeyEvent e){

    }

    @Override
    void handleInput(){

    }

    @Override
    public void keyPressed(KeyEvent e){

    }

}
