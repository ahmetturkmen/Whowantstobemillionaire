import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class PlaySound  {

    boolean isComplete;

    DataLine.Info info = null;
    private Clip clip;

    public void playSound(String path) {
        // Plays sounds with given path (path)
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());

            clip = AudioSystem.getClip();

            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        clip.start();
    }

    public void loop(int s) {
        clip.loop(s);
    }

    public void stop() {
        clip.stop();

    }

    public void start() {
        clip.start();
    }
    public boolean isFinished(){

        clip.addLineListener(new LineListener() {
            public void update(LineEvent evt) {
                if (evt.getType() == LineEvent.Type.STOP) {
                    isComplete=true;
                }
                isComplete=false;
            }
        });
        return isComplete;
    }

    public Clip getClip() {
        return clip;
    }

}
