package Main;

import Main.Managers.GameManager;

/**
 * Created by Tom Sharkey and Catherine Huang on 4/15/15.
 *
 * contains a static GameManager that can be used by all classes to go back to the main menu
 * upon completing the game.
 */
public class Main{
    public static GameManager staticGameManager = null;
    public static void main(String[] args){
        GameManager gm = new GameManager();
        staticGameManager = gm;
    }
}
