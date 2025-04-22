package main;

import javax.sound.sampled.*;
import java.io.File;

public class Sound {
    public Clip clip;

    public void setFile(String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playLoop() {
        if (clip != null) {
            clip.setFramePosition(0); // Reset to beginning
            clip.loop(Clip.LOOP_CONTINUOUSLY); // This starts the loop
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}

