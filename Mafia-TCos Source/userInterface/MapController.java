package sample;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Basak Melis OCAL on 5/4/2017.
 */
public class MapController {
    public void infoClicked(MouseEvent mouseEvent) throws IOException {
        Button button = (Button) mouseEvent.getSource();
        System.out.println(mouseEvent.getSource());
        if ((button).getText().equals("info")) {
            Stage current = (Stage) button.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("infoSample.fxml"));
            current.setScene(new Scene(root, 1080, 720));
            current.setMaximized(true);
            //current.setFullScreen(true);
            current.show();
        }
    }
}
