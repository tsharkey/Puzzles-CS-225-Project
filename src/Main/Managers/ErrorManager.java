package Main.Managers;

import javax.swing.*;

/**
 * Created by Tom on 8/20/15.
 */
public class ErrorManager extends JOptionPane{


    public ErrorManager(String message, JFrame frame) {
        this.showConfirmDialog(frame, message, "Error", JOptionPane.OK_CANCEL_OPTION);
    }
}
