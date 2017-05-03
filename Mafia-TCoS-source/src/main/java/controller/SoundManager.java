package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {

    private final Media pick = new Media(getClass().getClassLoader().getResource("Mafia-MainTheme.mp3").toString());
    private final MediaPlayer player = new MediaPlayer(pick);
    public static SoundManager soundManager;

    public SoundManager(){

    }

    public void play() {
        soundManager = this;
        player.setMute(false);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }

    public void stop() {
        player.setMute(true);
        player.pause();
    }

    public static SoundManager get() {
        return soundManager;
    }

}
