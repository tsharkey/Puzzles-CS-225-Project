package Main.Games.MinerPuzzle;

import java.awt.Color;

/**
 * Creates the Miner class to create 4 miners in the Miners and Minutes puzzle,
 * Keeping track of each miner when walking from mine cave to safety and from safety to mine cave
 * 
 * @author Parth Patel, Viet Dinh, Sean Johnston
 */
public class Miner {
    
    // instanve variables/fields
    private String name;
    private int time;
    public boolean isSafe;
    public static boolean LaternInSafeZone = false;
    public static int orignalTime = 15;
    
    private int x;
    private int orig_X;
    private int orig_Y;
    private int y;
    
    private Color color;
    
    /**
     * Constructor of Miner
     * @param n
     * @param t
     * @param color
     * @param x
     * @param y 
     */
    public Miner(String n, int t, Color color, int x, int y){
        
        name = n;
        time = t;
        isSafe = false;
        this.color = color;
        this.x = x;
        this.y = y;
        orig_X = x;
        orig_Y = y;
    }
   
    // getters/setters
    
    public int getOX(){
        return orig_X;
    }
    
    public int getOY(){
        return orig_Y;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    /**
     * gets the name of each miner
     * @return 
     */
    public String getName()
    {
        return name;
    }
    
    public int getTime(){
        
        return time;
    }
    
    /**
     * Move the miner from one side to the other
     */
    public void move()
    {
        if(isSafe){
            x -= 1;
        }
        else{
            x += 1;
        }
    }

    
}
