package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    public Button continueButton;
    public Button profileButton;
    public Button settingsButton;
    public Button creditsButton;
    public Button infoButton;

    public void buttonPressed(MouseEvent actionEvent) throws IOException {
        Button button = (Button)actionEvent.getSource();
        Stage current = (Stage)button.getScene().getWindow();
        if ((button).getText().equals("Settings"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("settingsSample.fxml"));
            current.setScene(new Scene(root, 1080, 720));
        }
        else if((button).getText().equals("view credits"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("creditsSample.fxml"));
            current.setScene(new Scene(root, 1080, 720));
        }
        else if((button).getText().equals("info"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("infoSample.fxml"));
            current.setScene(new Scene(root, 1080, 720));
        }
        else if((button).getText().equals("PROFILE"))
        {

        }
        else if((button).getText().equals("CONTINUE"))
        {

        }
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        current.setX(bounds.getMinX());
        current.setY(bounds.getMinY());
        current.setWidth(bounds.getWidth());
        current.setHeight(bounds.getHeight());
        current.setMaximized(true);
        //current.setFullScreen(true);
        current.show();
    }
}