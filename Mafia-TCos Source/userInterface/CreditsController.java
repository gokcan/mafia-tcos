package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class CreditsController {

    @FXML
    private Button backButtonCredits;

    @FXML
    public void buttonPressed(MouseEvent actionEvent) throws IOException {
        Button button = (Button)actionEvent.getSource();
        if ((button).getText().equals("BACK"))
        {
            Stage current = (Stage)button.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("menuSample.fxml"));
            current.setMaximized(true);
            current.setScene(new Scene(root, 1080, 720));
            //current.setFullScreen(true);
            current.show();
        }
    }


}
