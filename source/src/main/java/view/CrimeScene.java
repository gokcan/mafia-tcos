package view;

import Controller.GameEngine;
import Network.AccessManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.sun.jdi.event.ThreadStartEvent;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DurationDV;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CrimeScene {

    @FXML
    private JFXRadioButton crimeOption2;

    @FXML
    private JFXRadioButton crimeOption1;

    @FXML
    private JFXButton crimeBtn;

    @FXML
    private JFXRadioButton crimeOption4;

    @FXML
    private JFXRadioButton crimeOption3;

    @FXML
    private ToggleGroup group;

    @FXML
    private ImageView crimeImage;

    private static int seconds = 15;

    @FXML
    public void initialize() {
        refresh();
    }

    @FXML
    void commitCrime(ActionEvent event) {

        crimeBtn.setDisable(true);

        JFXRadioButton selectedRadio = (JFXRadioButton) group.getSelectedToggle();
        String extractedCrime = selectedRadio.getText();
        /*
        Extract the selected crime from the selected radio button's text or bindIt and pass it to the GameEngine. */
        GameEngine.game().isSuccessful(extractedCrime);

        Timeline timeline = new Timeline();
        for (int i = 0; i <= seconds; i++) {
            final int timeRemaining = seconds - i;
            KeyFrame frame = new KeyFrame(Duration.seconds(i),
                    e -> {
                        crimeBtn.setDisable(true);
                        crimeBtn.setText("Wait " + timeRemaining + " sec..");
                    });
            timeline.getKeyFrames().add(frame);
        }
        timeline.setOnFinished(e -> {
            crimeBtn.setText("Commit!");
            crimeBtn.setDisable(false);
            refresh();
            seconds += 5;
        });
        timeline.play();
    }

    public void showNotification(String notification) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION, notification);
        alert.setHeaderText(null);
        alert.setTitle("-Important-");
        alert.showAndWait();
    }

    public void refresh() {

        crimeOption1.setText(GameEngine.game().getCrimes().get(0).getDescription());
        crimeOption2.setText(GameEngine.game().getCrimes().get(1).getDescription());
        crimeOption3.setText(GameEngine.game().getCrimes().get(2).getDescription());
        crimeOption4.setText(GameEngine.game().getCrimes().get(3).getDescription());

    }
}

