import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.HashMap;

public class Sound {
    private static HashMap<String, Clip> sounds;
    public static void initializeSound(String filename) {
        if(sounds == null) {
            sounds = new HashMap<String, Clip>();
        }
        if(!sounds.containsKey(filename)) {
            try {
                URL url = Sound.class.getResource(filename);
                Clip clip = AudioSystem.getClip();
                sounds.put(filename, clip);
                clip.open(AudioSystem.getAudioInputStream(url));
            } catch(Exception exc) {
                exc.printStackTrace(System.out);
            }
        }
    }
    public static void playSound(String filename) {
        if(sounds == null || !sounds.containsKey(filename)) {
            initializeSound(filename);
        }
        sounds.get(filename).setFramePosition(0);
        sounds.get(filename).start();
    }
}
