import Controller.GameEngine;
import Controller.SoundManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStager; // **Declare static Stage**

    private void setPrimaryStage(Stage stage) {
        Main.primaryStager = stage;
    }

    static public Stage getPrimaryStage() {
        return Main.primaryStager;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("WelcomeScreen.fxml"));

        String image = Main.class.getClassLoader().getResource("mafia-wallpapers.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); " +
                    "-fx-background-position: center center; " +
                        "-fx-background-size: cover"
        );
        primaryStager = primaryStage;

        SoundManager soundManager = new SoundManager();
        soundManager.play();

        GameEngine gameEngine = GameEngine.game(); /* Nice usage of Singleton Pattern */
        gameEngine.init();

        primaryStager.setTitle("Mafia: The City of Sin");
        primaryStager.setResizable(false);
        primaryStager.setScene(new Scene(root));
        primaryStager.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
