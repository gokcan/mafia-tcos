package sample;

import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

public class InfoController {
    public void backPressed(MouseEvent mouseEvent) {
        System.out.println("Back pressed!");
    }

    public void dragged(MouseEvent mouseEvent) {
        System.out.println("Drag!");
    }
}
