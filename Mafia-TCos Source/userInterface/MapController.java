package sample;

        import com.jfoenix.controls.JFXButton;
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
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

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
        if ((button).getText().equals("CRIME"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("CrimeScene.fxml"));
            Scene trial = new Scene(root, 600, 400);
            popUpStage.setScene(trial);
        }
        else if((button).getText().equals("car theft"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("CarScene.fxml"));
            Scene trial = new Scene(root, 600, 400);
            popUpStage.setScene(trial);
        }
        else if((button).getText().equals("drugs"))
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
