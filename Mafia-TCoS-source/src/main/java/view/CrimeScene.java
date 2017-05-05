package view;

import Controller.GameEngine;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;

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
    public void initialize() {

        /*
        Of course we'll improve this logic. This is just a test.
        */
        crimeOption1.setText(GameEngine.getCrimes().get(0).getDescription());
        crimeOption2.setText(GameEngine.getCrimes().get(1).getDescription());
        crimeOption3.setText(GameEngine.getCrimes().get(2).getDescription());
        crimeOption4.setText(GameEngine.getCrimes().get(4).getDescription());
    }

    @FXML
    void commitCrime(ActionEvent event) {

        JFXRadioButton selectedRadio = (JFXRadioButton) group.getSelectedToggle();
        String crime = selectedRadio.getText(); 

        /*
        Extract the selected crime from the selected radio button's text or bindIt and pass it to the GameEngine.

        GameEngine.game().isSuccessful(GameEngine.getPlayerUnique(), extractedCrime);
        */
    }

    public void showNotification(String notification) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION, notification);
        alert.setHeaderText(null);
        alert.setTitle("-Important-");
        alert.showAndWait();

    }
}

