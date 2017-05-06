package view;

import Model.Crime;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.io.IOException;

/**
 * Created by SKYLIFE on 04/05/2017.
 */
public class ViewController {

    public ViewController() {

    }

    public void changeView() {

        Stage stage;
        ToggleGroup toggleGroup = new ToggleGroup();

        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("mapSample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage = Main.getPrimaryStage();
        stage.setScene(new Scene(root));
        stage.setTitle("Commit a crime!");

    }
}
