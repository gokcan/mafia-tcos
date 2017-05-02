package sample;

import javafx.scene.input.MouseEvent;

public class SettingsController {


    public void volumeChanged(MouseEvent scrollEvent) {
        System.out.println("Volume changed!");
    }

    public void enableSounds(MouseEvent mouseEvent) {
        System.out.println("Sound enabled!");
    }

    public void enableMusic(MouseEvent mouseEvent) {
        System.out.println("Music enabled!");
    }

    public void backPressed(MouseEvent mouseEvent) {
        System.out.println("Back pressed!");
    }
}
