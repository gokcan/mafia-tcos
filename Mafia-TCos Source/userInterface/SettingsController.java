package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.lang.String;

public class SettingsController {

    public Label volumeLabel;
    SoundManager box = new SoundManager();

    @FXML
    Slider volumeSlider;

    public void volumeChanged(MouseEvent scrollEvent) {
        System.out.println("Volume changed!");
    }

    public void enableSounds(MouseEvent mouseEvent) {
        if(!box.getSoundStatus())
            box.playSound(0);
        else
            box.pauseSound();
    }

    public void enableMusic(MouseEvent mouseEvent) {
        if(!box.getMusicStatus())
            box.playMusic();
        else
            box.pauseMusic();
    }

    public void backPressed(MouseEvent mouseEvent) {
        System.out.println("Back pressed!");
    }
}
