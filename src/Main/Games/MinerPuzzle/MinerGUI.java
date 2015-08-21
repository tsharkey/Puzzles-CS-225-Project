package Main.Games.MinerPuzzle;

import Main.Main;
import Main.Managers.ErrorManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Creates GUI for the Miners and Minutes Puzzle
 * 
 * @author Parth Patel, Viet Dinh, Sean Johnston
 */
public class MinerGUI extends JPanel implements MouseListener {

    // instance variables
    private Miner[] miners;// an array of 4 miners.
    private Ellipse2D[] circles;//present all people by circles with different colors
    private int time;//time that a specific person need to take to go through the tunnel

    public static int number_ready_to_go = 0;//maximum 2 people can move at a time
    public static boolean laternIsUsed = false;//tell if the latern is being used, and other people have to wait.
    public static ArrayList<boolean[]> undoList = new ArrayList<boolean[]>();// the list is used for undo action
    public static ArrayList<Integer> timeList = new ArrayList<Integer>();// save state of time of every movement

    /**
     * Sets the Constructor for the Miner GUI
     */
    public MinerGUI() {

        miners = new Miner[4];
        miners[0] = new Miner("Onika", 1, Color.CYAN, 0, 0);
        miners[1] = new Miner("Twitch", 2, Color.YELLOW, 0, 50);
        miners[2] = new Miner("Fiona", 4, Color.PINK, 0, 100);
        miners[3] = new Miner("Edward", 8, Color.ORANGE, 0, 150);

        circles = new Ellipse2D[4];// 4 circles
        time = 0;
        this.addMouseListener(this);
    }

    /**
     * Override the paintComponet method to create the main puzzle and design
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the game's background (dangerous area, safe area, ready-to-move area, the tunnel)
        g.setColor(Color.RED);
        g.fillRect(0, 0, 50, 200);//dangerous area

        g.setColor(Color.BLUE);//ready-to-move area
        g.fillRect(50, 50, 50, 100);

        g.setColor(Color.BLACK);// the dark tunnel
        g.fillRect(100, 50, 300, 100);

        g.setColor(Color.BLUE);//ready-to-move area
        g.fillRect(400, 50, 50, 100);

        g.setColor(Color.GREEN);//safe area
        g.fillRect(450, 0, 50, 200);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        //when the latern is at safe area
        if (!Miner.LaternInSafeZone) {
            Ellipse2D circle = new Ellipse2D.Double(50, 0, 50, 50);// the circle presenting the latern
            g2d.fill(circle);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Latern", 55, 30);
        } else {//when the latern is at dangerous area
            Ellipse2D circle = new Ellipse2D.Double(400, 0, 50, 50);//the circle presenting the latern
            g2d.fill(circle);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Latern", 405, 30);
        }
        //draw all the circles(people) base on their positions
        for (int i = 0; i < miners.length; i++) {
            g2d.setColor(miners[i].getColor());
            circles[i] = new Ellipse2D.Double(miners[i].getX(), miners[i].getY(), 50, 50);
            g2d.fill(circles[i]);
            g2d.setColor(Color.BLACK);
            g2d.drawString(miners[i].getName(), miners[i].getX() + 5, miners[i].getY() + 30);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //not use
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //not use
    }

    /**
     * Override the mouseReleaded method
     * to move the people from dangerous area to ready-to-move area or vice versa
     * to move the people from safe area to ready-to-move area or vice versa
     * when clicking on a circle(person)
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < miners.length; i++) {
            //when clicking on the person, and the latern is not in use
            if ((e.getButton() == 1) && circles[i].contains(e.getX(), e.getY()) && !laternIsUsed) {
                //when the latern is not at the same side as the miner, who is clicked on
                if (miners[i].isSafe != Miner.LaternInSafeZone) {
                    new Thread() {
                        @Override
                        public void run() {
                            MinerGamePanel.message.setForeground(Color.red);
                            MinerGamePanel.message.setText("I do not have the latern.");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                new ErrorManager(ex.getMessage(), Main.staticGameManager);
                            }
                            MinerGamePanel.message.setForeground(Color.LIGHT_GRAY);
                            MinerGamePanel.message.setText("No message!");

                        }
                    }.start();
                }
                //when the latern is at the same side as the person clicked on
                else if (miners[i].getX() == 0) {//the person is at dangerous area
                    if (number_ready_to_go < 2) {//less than 2 people at ready-to-move area
                        miners[i].isSafe = false;//currently, the person is at dangerous area
                        MinerGamePanel.reset.setEnabled(true);
                        MinerGamePanel.undo.setEnabled(true);
                        MinerGamePanel.move.setEnabled(true);
                        number_ready_to_go++;//increase the number of people, who are at ready-to-move area
                        miners[i].setX(50);//set new location for the person(the person move to ready-to-move area)
                        miners[i].setY(50 * number_ready_to_go);
                        //if there are 2 people moving at a time
                        //then the quicker one will be slow down because of the slower one
                        if (number_ready_to_go == 1) {
                            time = miners[i].getTime();
                        } else {
                            if (miners[i].getTime() > time) {
                                time = miners[i].getTime();
                            }
                        }
                    } 
                    else {//when the player try to get more than 2 miners moving at a time
                        new Thread() {
                            @Override
                            public void run() {
                                MinerGamePanel.message.setForeground(Color.red);
                                MinerGamePanel.message.setText("Too many people ready to go.");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException ex) {
                                    new ErrorManager(ex.getMessage(), Main.staticGameManager);
                                }
                                MinerGamePanel.message.setForeground(Color.LIGHT_GRAY);
                                MinerGamePanel.message.setText("No message!");

                            }
                        }.start();
                    }
                } 
                else if (miners[i].getX() == 450) {//when the person is at safe area
                    //repeat the above piece of code
                    if (number_ready_to_go < 2) {
                        number_ready_to_go++;
                        miners[i].isSafe = true;
                        miners[i].setX(400);
                        miners[i].setY(50 * number_ready_to_go);
                        MinerGamePanel.move.setEnabled(true);
                        if (number_ready_to_go == 1) {
                            time = miners[i].getTime();
                        } else {
                            if (miners[i].getTime() > time) {
                                time = miners[i].getTime();
                            }
                        }
                    } else {//when user try to get more than 2 miners moving at a time
                        new Thread() {
                            @Override
                            public void run() {
                                MinerGamePanel.message.setForeground(Color.red);
                                MinerGamePanel.message.setText("Too many people ready to go.");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException ex) {
                                    new ErrorManager(ex.getMessage(), Main.staticGameManager);
                                }
                                MinerGamePanel.message.setForeground(Color.LIGHT_GRAY);
                                MinerGamePanel.message.setText("No message!");

                            }
                        }.start();
                    }
                //when the person is at ready-to-move area, move him back to dangerous area or safe area
                } else if (miners[i].getX() == 50) {
                    number_ready_to_go--;
                    if (number_ready_to_go == 0) {
                        MinerGamePanel.move.setEnabled(false);
                    }
                    miners[i].setX(0);
                    miners[i].setY(miners[i].getOY());
                    //The second person who is ready to move will take place of the first person, who is removed from ready-to-move area
                    for(int j = 0; j < miners.length; j++) {
                        if (miners[j].getX() == 50) {
                            time = miners[j].getTime();
                            if (miners[j].getY() == 100) {
                                miners[j].setY(50);
                            }
                            break;
                        }
                    }
                //when the person is at ready-to-move area, move him back to safe area
                } else if (miners[i].getX() == 400) {
                    number_ready_to_go--;
                    if (number_ready_to_go == 0) {
                        MinerGamePanel.move.setEnabled(false);
                    }
                    miners[i].setX(450);
                    miners[i].setY(miners[i].getOY());
                    //The second person who is ready to move will take place of the first person, who is removed from ready-to-move area
                    for (int j = 0; j < miners.length; j++) {
                        if (miners[j].getX() == 400) {
                            time = miners[j].getTime();
                            if (miners[j].getY() == 100) {
                                miners[j].setY(50);
                            }
                            break;
                        }
                    }
                }
                repaint();
                break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //not use
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //not use
    }

    /**
     * Creates the update locations method when miners move from cave to safety,
     * it updates the location in the puzzle
     */
    public void update_locations() {

        MinerGamePanel.move.setEnabled(false);
        new Thread() {//the thread to make the circle changing its location continuously
            @Override
            public void run() {
                laternIsUsed = true;
                int count = 0;//make a movement every 1000000*time(of that person) excuting times of the content of while loop
                int speed1 = 0;
                do {
                    speed1++;
                    if (speed1 == 1000000 * time) {
                        for (Miner miner : miners) {
                            if (miner.getX() >= 50 && miner.getX() <= 400) {
                                miner.move();
                            }
                            if (miner.getX() == 400) {
                                miner.setX(450);
                                miner.setY(miner.getOY());
                                miner.isSafe = !miner.isSafe;
                            }
                            if (miner.getX() == 50) {
                                miner.setX(0);
                                miner.setY(miner.getOY());
                                miner.isSafe = !miner.isSafe;
                            }
                        }
                        repaint();
                        count++;
                        speed1 = 0;
                    }
                } while (count < 350);//the length of the tunnel is 350 pixels

                if (Miner.orignalTime == 0) {
                    boolean win = true;
                    for (Miner miner : miners) {
                        if (miner.isSafe == false) {
                            win = false;
                        }
                    }
                    if (win) {
                        //Player won 
                        System.out.println("Win");
                        String str = "You won!\nWould you like to play again?";
                        if (JOptionPane.showConfirmDialog(null, str, "GAME RESULT",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            Miner.orignalTime = 15;
                            Miner.LaternInSafeZone = false;
                            MinerGamePanel.time.setText(Miner.orignalTime + " minute(s) left");
                            reset();
                            undoList.clear();
                            timeList.clear();
                        } //when the player decides to quit the game
                        else {
                            Main.staticGameManager.setGame(1);
                            Miner.LaternInSafeZone = false;
                            Miner.orignalTime = 15;
                            MinerGUI.undoList.clear();
                            MinerGUI.timeList.clear();
                            MinerGUI.number_ready_to_go = 0;
                        }
                    } else {
                        //Player lost
                        System.out.println("Lose");
                        String str = "You lose!\nWould you like to play again?";
                        if (JOptionPane.showConfirmDialog(null, str, "GAME RESULT",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            Miner.orignalTime = 15;
                            Miner.LaternInSafeZone = false;
                            MinerGamePanel.time.setText(Miner.orignalTime + " minute(s) left");
                            reset();
                            undoList.clear();
                            timeList.clear();
                        } //when the player decides to quit the game
                        else {
                            Main.staticGameManager.setGame(1);
                            Miner.LaternInSafeZone = false;
                            Miner.orignalTime = 15;
                            MinerGUI.undoList.clear();
                            MinerGUI.timeList.clear();
                            MinerGUI.number_ready_to_go = 0;
                        }
                    }
                } else if (Miner.orignalTime < 0) {
                    System.out.println("Lose");
                    String str = "You lose!\nWould you like to play again?";
                    if (JOptionPane.showConfirmDialog(null, str, "GAME RESULT",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        Miner.orignalTime = 15;
                        Miner.LaternInSafeZone = false;
                        MinerGamePanel.time.setText(Miner.orignalTime + " minute(s) left");
                        reset();
                        undoList.clear();
                        timeList.clear();
                    } //when the player decides to quit the game
                    else {
                        Main.staticGameManager.setGame(1);
                        Miner.LaternInSafeZone = false;
                        Miner.orignalTime = 15;
                        MinerGUI.undoList.clear();
                        MinerGUI.timeList.clear();
                        MinerGUI.number_ready_to_go = 0;
                    }
                }
                laternIsUsed = false;
            }
        }.start();
        Miner.orignalTime -= time;
        MinerGamePanel.time.setText(Miner.orignalTime + " minute(s) left");
        Miner.LaternInSafeZone = !Miner.LaternInSafeZone;
        number_ready_to_go = 0;
        repaint();
    }

    /**
     * Reset method, to reset the game when the player wants to reset
     * reset to original game state
     */
    public void reset() {
        for (Miner miner : miners) {
            miner.setX(miner.getOX());
            miner.setY(miner.getOY());
            miner.isSafe = false;
        }
        repaint();
    }

    /**
     * Save State method, which saves the state of the game into an arraylist 
     * every time the player make a movement between dangerous area and safe area
     */
    public void saveState() {
        undoList.add(new boolean[5]);
        for (int i = 0; i < miners.length; i++) {
            undoList.get(undoList.size() - 1)[i] = miners[i].isSafe;
        }
        undoList.get(undoList.size() - 1)[4] = Miner.LaternInSafeZone;
        timeList.add(Miner.orignalTime);
    }

    /**
     * Sets the PreState of the Miner puzzle
     * used for undo button
     */
    public void setPreState() {
        for (int i = 0; i < miners.length; i++) {
            miners[i].isSafe = undoList.get(undoList.size() - 1)[i];
            if (miners[i].isSafe == false) {
                miners[i].setX(0);
            } else {
                miners[i].setX(450);
            }
            miners[i].setY(miners[i].getOY());
        }
        Miner.LaternInSafeZone = undoList.get(undoList.size() - 1)[4];
        undoList.remove(undoList.size() - 1);
        number_ready_to_go = 0;
        repaint();
    }

}
