package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import Controller.SoundManager;
import Network.AccessManager;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;

import Controller.GameEngine;
/*
    @Author: Skylifee7
 */

public class WelcomeScreen {

    @FXML
    private JFXButton signupBtn;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXTextField username;

    @FXML
    private Pane welcomePane;

    @FXML
    private JFXButton muteButton;

    @FXML
    private JFXCheckBox checkBox;

    private String user;
    private String pass;
    private static boolean isMute;


    @FXML
    void doLogin(ActionEvent event) {

        user = username.getText();
        pass = password.getText();

        if (user != null && !user.isEmpty() && pass != null && !pass.isEmpty())
            if (checkBox.isSelected())
                GameEngine.game().getAccessManager().callLoginService(user, pass);
            else
                showNotification("You need to accept terms.");
    }

    @FXML
    void doSignup(ActionEvent event) {

        user = username.getText();
        pass = password.getText();

        if (user != null && !user.isEmpty() && pass != null && !pass.isEmpty())
            if (checkBox.isSelected())
                GameEngine.game().getAccessManager().callSignupService(user, pass);
            else
                showNotification("You need to accept terms.");
    }

    @FXML
    void mute(ActionEvent event) {

        if (isMute)
            SoundManager.get().play();

        else
            SoundManager.get().stop();

        isMute = !isMute;
    }

    public void showNotification(String notification) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION, notification);
        alert.setHeaderText(null);
        alert.setTitle("-Important-");
        alert.showAndWait();
    }

}


