package game;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundPlayer {

    private Clip clip;

    public SoundPlayer(String soundFile) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/" + soundFile));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error loading sound: " + soundFile);
        }
    }

    public void play() {
        if (clip == null) return;
        if (clip.isRunning())
            clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }
}
