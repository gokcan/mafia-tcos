package View;

import Controller.GameEngine;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by Basak Melis OCAL on 5/4/2017.
 */

public class MapController {

    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

    @FXML
    private Button crimeButton4;

    @FXML
    private Button carTheftButton;

    @FXML
    public void initialize() {

        String image = getClass().getClassLoader().getResource("Gun-48.png").toExternalForm();
        crimeButton4.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center; " +
                "-fx-background-size: inherit;" +
                "-fx-background-repeat: no-repeat;"
        );

        crimeButton4.setText("");

        image = getClass().getClassLoader().getResource("car-theft-icon.png").toExternalForm();

        carTheftButton.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center; " +
                "-fx-background-size: inherit;" +
                "-fx-background-repeat: no-repeat;"
        );

        carTheftButton.setText("");

    }

    public void buttonClicked(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        Stage current = (Stage) button.getScene().getWindow();
        if ((button).getText().equals("info")) {
            Parent root = FXMLLoader.load(getClass().getResource("infoSample.fxml"));
            current.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
        }
        else if((button).getText().equals("MENU"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("menuSample.fxml"));
            current.setScene(new Scene(root, screenSize.getWidth(), screenSize.getHeight()));
        }
        current.setMaximized(true);
        //current.setFullScreen(true);
        current.show();
    }

    public void subMenuClicked(MouseEvent mouseEvent) throws IOException {
        JFXButton button = (JFXButton) mouseEvent.getSource();
        Stage popUpStage = new Stage();
        if((button).getText().equals("drugs"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("DrugScene.fxml"));
            Scene trial = new Scene(root, 600, 400);
            popUpStage.setScene(trial);
        }
        else if((button).getText().equals("buy bullet"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("infoSample.fxml"));
            Scene trial = new Scene(root, 600, 400);
            popUpStage.setScene(trial);
        }
        else if((button).getText().equals("sell car"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("infoSample.fxml"));
            Scene trial = new Scene(root, 600, 400);
            popUpStage.setScene(trial);
        }
        else if((button).getText().equals("buy weapon"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("infoSample.fxml"));
            Scene trial = new Scene(root, 600, 400);
            popUpStage.setScene(trial);
        }
        popUpStage.show();
    }
}

    @FXML
    void openCrime(ActionEvent event) {

        Stage current = new Stage(StageStyle.DECORATED);
        Parent root = null;

        GameEngine.game().setIsPropertyCrime(false);
        GameEngine.game().getAccessManager().callGetCrimes();

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("CrimeScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String image = getClass().getClassLoader().getResource("mafia-tcos-wallpaper.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-size: cover"
        );
        current.setScene(new Scene(root));
        //current.setFullScreen(true);
        current.show();

    }

    @FXML
    void openCarTheft(ActionEvent event) {

        Stage current = new Stage(StageStyle.DECORATED);
        Parent root = null;

        GameEngine.game().setIsPropertyCrime(true);
        GameEngine.game().getAccessManager().callGetCrimes();

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("CrimeScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String image = getClass().getClassLoader().getResource("mafia-blurred.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-size: cover"
        );
        current.setScene(new Scene(root));
        //current.setFullScreen(true);
        current.show();

    }


}
