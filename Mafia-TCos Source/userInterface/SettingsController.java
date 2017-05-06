package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.lang.String;

public class SettingsController {

    public Label volumeLabel;
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    SoundManager box = new SoundManager();

    @FXML
    Slider volumeSlider;
    public Button backButtonSettings;

    public void volumeChanged(MouseEvent scrollEvent) {
        System.out.println(volumeSlider.getValue());
        box.adjustVolume((float)volumeSlider.getValue());
    }

    public void musicEnabled(MouseEvent mouseEvent) {
        if (!box.getSoundStatus())
            box.playSound(0);
        else
            box.pauseSound();
    }

    public void soundsEnabled(MouseEvent mouseEvent){
        if (!box.getMusicStatus())
            box.playMusic();
        else
            box.pauseMusic();
    }

    public void backPressed(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        if ((button).getText().equals("BACK")) {
            Stage current = (Stage) button.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("menuSample.fxml"));
            current.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
            current.setMaximized(true);
            current.show();
        }
    }
}