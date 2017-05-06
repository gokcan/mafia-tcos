package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Basak Melis OCAL on 5/6/2017.
 */

public class DrugController {

    @FXML
    public JFXButton drugButton;
    public JFXRadioButton crimeOption1;
    public JFXRadioButton crimeOption2;
    public JFXRadioButton crimeOption3;

    @FXML
    private ToggleGroup group;

    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

    public void goForItClicked(MouseEvent mouseEvent) throws IOException {
        JFXButton button = (JFXButton) mouseEvent.getSource();
        JFXRadioButton selectedRadio = (JFXRadioButton)group.getSelectedToggle();
        Stage current = (Stage) button.getScene().getWindow();
        if ((button).getText().equals("Go for it!")){
            if(selectedRadio.getText().equals("Manufacturing"))
            {
                Parent root = FXMLLoader.load(getClass().getResource("ManufacturingScene.fxml"));
                current.setScene(new Scene(root, 600, 400));
            }
            else if(selectedRadio.getText().equals("Distribution"))
            {
                Parent root = FXMLLoader.load(getClass().getResource("DistributionScene.fxml"));
                current.setScene(new Scene(root, 600, 400));
            }
            else if(selectedRadio.getText().equals("Laundering"))
            {
                Parent root = FXMLLoader.load(getClass().getResource("LaunderingScene.fxml"));
                current.setScene(new Scene(root, 600, 400));
            }
            current.show();
        }
    }


}
