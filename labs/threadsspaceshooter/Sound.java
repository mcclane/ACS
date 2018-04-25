/**
 * <h1>Java Sound Helper</h1>
 * <p>Enables easy playing of mp3 and wav sound files. Use the initializeSound method to cache your sounds prior
 * to playing for quick access. This is done automatically after the first play as well.</p>
 *
 * @author Tyler Chatow
 * @version 1.1
 * @since 2017-12-04
 */
 
 
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
 
import java.util.HashMap;
 
public class Sound {
    private static final JFXPanel soundPanel = new JFXPanel();
    private static HashMap<String, Media> sounds = new HashMap<>();
    private static HashMap<String, AudioClip> clips = new HashMap<>();
 
    private Sound() {};
 
    private static Media getSound(String sound) {
        try {
            Media soundMedia = new Media(Sound.class.getResource(sound).toString());
            return soundMedia;
        } catch (Exception e) {
            System.out.println("Error loading sound file: " + sound);
        }
        return null;
    }
 
    public static boolean initializeSound(String sound) {
        return initializeSound(sound, null);
    }
 
    public static boolean initializeSound(String sound, Runnable onInitialized) {
        if(!sounds.containsKey(sound)) {
            Media soundMedia = getSound(sound);
            if(soundMedia != null) {
                final MediaPlayer player = new MediaPlayer(soundMedia);
                player.setOnReady(() -> {
                    sounds.put(sound, soundMedia);
                    player.dispose();
                    if(onInitialized != null) {
                        onInitialized.run();
                    }
                });
                return true;
            }
            return false;
        }
        if(onInitialized != null) {
            onInitialized.run();
        }
        return true;
    }
 
    /**
     * This method plays the sound specified by the sound parameter.
     * @param sound The string of the sound file to play.
     * @return MediaPlayer object. This can be discarded if not needed.
     */
    public static void playSound(String sound) {
        playSound(sound, 1, false);
    }
 
    /**
     * This method plays the sound specified by the sound parameter allowing looping.
     * @param sound The string of the sound file to play.
     * @param loop True if the audio should loop (such as a background track), false otherwise.
     * @return MediaPlayer object. This can be discarded if not needed.
     */
    public static void playSound(String sound, boolean loop) {
        playSound(sound, 1, loop);
    }
 
    /**
     * This method plays the sound specified by the sound parameter allowing volume specification.
     * @param sound The string of the sound file to play.
     * @param double A double from 0-1 specifying the volume.
     * @return MediaPlayer object. This can be discarded if not needed.
     */
    public static void playSound(String sound, double volume) {
        playSound(sound, volume, false);
    }
 
    /**
     * This method plays the sound specified by the sound parameter allowing looping and volume specification.
     * @param sound The string of the sound file to play.
     * @param double A double from 0-1 specifying the volume.
     * @param loop True if the audio should loop (such as a background track), false otherwise.
     * @return MediaPlayer object. This can be discarded if not needed.
     */
    public static void playSound(String sound, double volume, boolean loop) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                initializeSound(sound, new Runnable() {
                    @Override
                    public void run() {
                        if(sounds.get(sound).getDuration().toSeconds() < 10) {
                            playClip(sound, volume, loop);
                        } else {
                            playMedia(sound, volume, loop);
                        }
                    }
                });
            }
        }).start();
    }
 
    private static void playMedia(String sound, double volume, boolean loop) {
        final MediaPlayer player = new MediaPlayer(sounds.get(sound));
        player.play();
        if(loop) {
            player.setCycleCount(MediaPlayer.INDEFINITE);
        } else {
            player.setOnEndOfMedia(() -> player.dispose());
        }
        player.setVolume(volume);
    }
 
    private static void playClip(String sound, double volume, boolean loop) {
        AudioClip clip;
        if(!clips.containsKey(sound)) {
            clip = new AudioClip(Sound.class.getResource(sound).toString());
            clips.put(sound, clip);
        } else {
            clip = clips.get(sound);
        }
 
        clip.setVolume(volume);
        if(loop) {
            clip.setCycleCount(AudioClip.INDEFINITE);
        }
 
        clip.play();
    }
}