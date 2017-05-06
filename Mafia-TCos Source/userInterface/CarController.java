package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;


/**
 * Created by Basak Melis OCAL on 5/5/2017.
 */
public class CarController {

    public static String text;
    @FXML
    public JFXRadioButton carCrimeOption1;
    public JFXRadioButton carCrimeOption2;
    public JFXRadioButton carCrimeOption3;
    public JFXRadioButton carCrimeOption4;
    public JFXButton carTheftCommit;

    public void commitCrime(ActionEvent actionEvent) {


    }

    public void optionClicked(MouseEvent mouseEvent) {
        JFXRadioButton button = (JFXRadioButton) mouseEvent.getSource();
        text = (button).getText();
    }
}
