package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
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
            Parent root = FXMLLoader.load(getClass().getResource("mapSample.fxml"));
            current.setScene(new Scene(root, 1080, 720));
        }
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        current.setMaximized(true);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        current.setX(primaryScreenBounds.getMinX());
        current.setY(primaryScreenBounds.getMinY());
        current.setWidth(primaryScreenBounds.getWidth());
        current.setHeight(primaryScreenBounds.getHeight());
        //current.setFullScreen(true);
        current.show();
    }
}