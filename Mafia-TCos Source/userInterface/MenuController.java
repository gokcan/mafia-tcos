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

    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    public void buttonPressed(MouseEvent actionEvent) throws IOException {
        Button button = (Button)actionEvent.getSource();
        Stage current = (Stage)button.getScene().getWindow();
        if ((button).getText().equals("SETTINGS"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("settingsSample.fxml"));
            current.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
        }
        else if((button).getText().equals("VIEW CREDITS"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("creditsSample.fxml"));
            current.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
        }
        else if((button).getText().equals("INFO"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("infoSample.fxml"));
            current.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
        }
        else if((button).getText().equals("PROFILE"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("profileSample.fxml"));
            current.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
        }
        else if((button).getText().equals("CONTINUE"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("mapSample.fxml"));
            current.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
        }


        current.setMaximized(true);
        //current.setFullScreen(true);
        current.show();
    }
}