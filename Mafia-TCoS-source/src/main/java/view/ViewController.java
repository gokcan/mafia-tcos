package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @Author: Skylifee7 on 02/05/2017.
 */

public class ViewController {

    public ViewController() {

    }

    public void changeView() {

        Stage stage;
        Parent root = null;

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

        stage = Main.getPrimaryStage();
        stage.setScene(new Scene(root));
        stage.setTitle("Commit a crime!");

    }
}
