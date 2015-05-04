package Main.Games.tigers;

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
    
     /*getClass().getResource("images/prison.png")
       AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
                "./src/Main/Games/tigers/sounds/"+soundFile+".wav"));*/
       
    try {
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File(getClass().getResource(path).toURI()));
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
    }

    catch (UnsupportedAudioFileException uae) {
        System.out.println(uae);
    } catch (IOException ioe) {
        System.out.println(ioe);
    } catch (LineUnavailableException lua) {
        System.out.println(lua);
    } catch (URISyntaxException ese) {
    }

}
 
}