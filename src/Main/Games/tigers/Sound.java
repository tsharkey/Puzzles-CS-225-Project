package Main.Games.tigers;

import Main.Main;
import Main.Managers.ErrorManager;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.net.URISyntaxException;
/**
 * Sound class retrieves sound files from sound folder.
 * 
 * @author Henrique
 * 
 * @version (2015-04-30)
 */
public class Sound {
  
  /**
   * Plays the given sound file.
   * 
   * @param  soundFile  the name of the sound file
   */
   public void playSound(String soundFile) throws LineUnavailableException {
     
     String path = "sounds/" + soundFile + ".wav";

    try {
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File(getClass().getResource(path).toURI()));
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
    }

    catch (UnsupportedAudioFileException uae) {
        new ErrorManager(uae.getMessage(), Main.staticGameManager);
    } catch (IOException ioe) {
        new ErrorManager(ioe.getMessage(), Main.staticGameManager);
    } catch (LineUnavailableException lua) {
        new ErrorManager(lua.getMessage(), Main.staticGameManager);
    } catch (URISyntaxException ese) {
        new ErrorManager(ese.getMessage(), Main.staticGameManager);
    }

}
 
}