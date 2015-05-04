package Main.Games.MinerPuzzle;

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
    private Miner[] miners;
    private Ellipse2D[] circles;
    private int time;

    public static int number_ready_to_go = 0;
    public static boolean laternIsUsed = false;
    public static ArrayList<boolean[]> undoList = new ArrayList<boolean[]>();
    public static ArrayList<Integer> timeList = new ArrayList<Integer>();

    /**
     * Sets the Constructor for the Miner GUI
     */
    public MinerGUI() {

        miners = new Miner[4];
        miners[0] = new Miner("Onika", 1, Color.CYAN, 0, 0);
        miners[1] = new Miner("Twitch", 2, Color.YELLOW, 0, 50);
        miners[2] = new Miner("Fiona", 4, Color.PINK, 0, 100);
        miners[3] = new Miner("Edward", 8, Color.ORANGE, 0, 150);

        circles = new Ellipse2D[4];
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
        g.setColor(Color.RED);
        g.fillRect(0, 0, 50, 200);

        g.setColor(Color.BLUE);
        g.fillRect(50, 50, 50, 100);

        g.setColor(Color.BLACK);
        g.fillRect(100, 50, 300, 100);

        g.setColor(Color.BLUE);
        g.fillRect(400, 50, 50, 100);

        g.setColor(Color.GREEN);
        g.fillRect(450, 0, 50, 200);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        if (!Miner.LaternInSafeZone) {
            Ellipse2D circle = new Ellipse2D.Double(50, 0, 50, 50);
            g2d.fill(circle);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Latern", 55, 30);
        } else {
            Ellipse2D circle = new Ellipse2D.Double(400, 0, 50, 50);
            g2d.fill(circle);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Latern", 405, 30);
        }
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
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Override the mouseReleaded method
     * @param e 
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < miners.length; i++) {
            if ((e.getButton() == 1) && circles[i].contains(e.getX(), e.getY()) && !laternIsUsed) {
                if (miners[i].isSafe != Miner.LaternInSafeZone) {
                    new Thread() {
                        @Override
                        public void run() {
                            MinerGamePanel.message.setText("I do not have the latern.");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MinerGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            MinerGamePanel.message.setText("");

                        }
                    }.start();
                } else if (miners[i].getX() == 0) {
                    if (number_ready_to_go < 2) {
                        miners[i].isSafe = false;
                        MinerGamePanel.reset.setEnabled(true);
                        MinerGamePanel.undo.setEnabled(true);
                        MinerGamePanel.move.setEnabled(true);
                        number_ready_to_go++;
                        miners[i].setX(50);
                        miners[i].setY(50 * number_ready_to_go);
                        if (number_ready_to_go == 1) {

                            time = miners[i].getTime();
                        } else {
                            if (miners[i].getTime() > time) {
                                time = miners[i].getTime();
                            }
                        }
                    } else {
                        new Thread() {
                            @Override
                            public void run() {
                                MinerGamePanel.message.setText("Too many people ready to go.");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(MinerGUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                MinerGamePanel.message.setText("");

                            }
                        }.start();
                    }
                } else if (miners[i].getX() == 450) {
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
                    } else {
                        new Thread() {
                            @Override
                            public void run() {
                                MinerGamePanel.message.setText("Too many people ready to go.");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(MinerGUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                MinerGamePanel.message.setText("");

                            }
                        }.start();
                    }

                } else if (miners[i].getX() == 50) {
                    number_ready_to_go--;
                    if (number_ready_to_go == 0) {
                        MinerGamePanel.move.setEnabled(false);
                    }
                    miners[i].setX(0);
                    miners[i].setY(miners[i].getOY());
                    for (int j = 0; j < miners.length; j++) {
                        if (miners[j].getX() == 50) {
                            time = miners[j].getTime();
                            if (miners[j].getY() == 100) {
                                miners[j].setY(50);
                            }
                            break;
                        }
                    }
                } else if (miners[i].getX() == 400) {
                    number_ready_to_go--;
                    if (number_ready_to_go == 0) {
                        MinerGamePanel.move.setEnabled(false);
                    }
                    miners[i].setX(450);
                    miners[i].setY(miners[i].getOY());
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
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Creates the update locations method when miners move from cave to safety,
     * it updates the location in the puzzle
     */
    public void update_locations() {

        MinerGamePanel.move.setEnabled(false);
        new Thread() {
            @Override
            public void run() {
                laternIsUsed = true;
                int count = 0;
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
                } while (count < 350);

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
                            Main.Main.staticGameManager.setGame(1);
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
                            Main.Main.staticGameManager.setGame(1);
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
                        Main.Main.staticGameManager.setGame(1);
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
     * Save State method, which saves the state of the game when the user plays
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
