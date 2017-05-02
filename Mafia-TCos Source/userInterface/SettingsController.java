package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
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

    SoundManager box = new SoundManager();

    @FXML
    Slider volumeSlider;
    public Button backButtonSettings;

    public void volumeChanged(MouseEvent scrollEvent) {
        System.out.println("Volume changed!");
    }

    public void enableSounds(MouseEvent mouseEvent) {
        if (!box.getSoundStatus())
            box.playSound(0);
        else
            box.pauseSound();
    }

    public void enableMusic(MouseEvent mouseEvent) {
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
            current.setScene(new Scene(root, 1080, 720));
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            current.setX(bounds.getMinX());
            current.setY(bounds.getMinY());
            current.setWidth(bounds.getWidth());
            current.setHeight(bounds.getHeight());
            //current.setFullScreen(true);
            current.show();
        }
    }
}