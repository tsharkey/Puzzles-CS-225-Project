package Main.Games;


import Main.Managers.GameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
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

    private JScrollPane scrollPane;
    private JPanel imagePane;
    private JButton button;
    private BufferedImage img;
    //TODO: EXPANDABLE read from text file, make sure to add new files and text into appropriated areas.
    private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    private ArrayList<String> gameTexts = new ArrayList<String>();
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private ArrayList<JPanel> gameSections = new ArrayList<JPanel>();

    /**
     * Constructor of the Main Menu to list all the playable games.
     *
     * @param manager
     */
    public MainMenu(GameManager manager) {
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

        imagePane = new JPanel(new GridLayout(images.size(), 1));
        String html1 = "<html><body style='width: ";
        String html2 = "px'>";

        //add the images as buttons
        for (int i = 0; i < images.size(); i++) {
            ImageIcon icon = new ImageIcon(images.get(i));
            button = new JButton(icon);
            button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
            button.addActionListener(this);
            buttons.add(button);
        }

        //add imagebuttons and textlabels on to the individual panel respectively
        for (int i = 0; i < buttons.size(); i++) {
            JPanel game = new JPanel(new GridLayout(1,1));

            if (i % 2 == 0) {
                game.add(buttons.get(i));
                //hard coded in html TODO: make it response to Constants.SCREEN_WIDTH
                game.add(new JLabel(html1 + "300" + html2 + gameTexts.get(i)));
                game.setBorder(BorderFactory.createLineBorder(Color.black));
            } else {
                game.add(new JLabel(html1 + "300" + html2 + gameTexts.get(i)));
                game.add(buttons.get(i));
                game.setBorder(BorderFactory.createLoweredBevelBorder());
            }
            gameSections.add(game);
        }

        //add all individual game's panel to the scollpanel
        for(JPanel g: gameSections)
            imagePane.add(g);
    }

    /**
     * Load the images and info for the imagepane from file
     *
     */
    private void loadInfos() {
        File file = null;
        String resource = "/Main/Assets/text.txt";
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
        try {
            //scan through the text file for image names and text's label
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String temp = scan.nextLine();
                //check if it's the image file's name
                if (temp.charAt(0) == 'i') {
                    try {
                        //img = ImageIO.read(new File(getClass().getResourceAsStream("../Assets/images/" + temp.substring(2) + ".png")));
                        img = ImageIO.read(getClass().getResourceAsStream("/Main/Assets/images/" + temp.substring(2) + ".png"));
                        images.add(img);
                    } catch (IOException e) {
                        System.out.println("Image not found");
                    }
                } else if (temp.charAt(0) == 'n') {
                    gameTexts.add(temp.substring(2));
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.print("Text file not found");
        }

    }


/**
 * ActionListner for the buttons, changes states when images are clicked.
 *
 * @param e TODO: EXPENDABLE: add more games' button listeners to switch to,
 * TODO: number input into setGame is button's index+2
 */
@Override
        public void actionPerformed(ActionEvent e) {
        //when click on the buttons, will switch to the assigned game
        if (e.getSource() == buttons.get(0)) {
            manager.setGame(2);
        } else if (e.getSource() == buttons.get(1)) {
            manager.setGame(3);
        } else if (e.getSource() == buttons.get(2)) {
            manager.setGame(4);
        }

    }

    @Override
        public void keyTyped(KeyEvent e) {

    }

    @Override
        public void keyReleased(KeyEvent e) {

    }

    @Override
    void handleInput() {

    }

    @Override
        public void keyPressed(KeyEvent e) {

    }

}
