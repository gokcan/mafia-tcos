package sample;

import java.util.Random;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Basak Melis OCAL on 5/6/2017.
 */
public class LaunderingController {

    @FXML
    private ToggleGroup group;
    public JFXButton buyButton;

    @FXML
    public JFXRadioButton launderingTool1;
    public JFXRadioButton launderingTool2;
    public JFXRadioButton launderingTool3;
    public JFXRadioButton launderingTool4;

    int crimeSize = GameEngine.getCrimes().size();
    private ArrayList<JFXRadioButton> buttons = new ArrayList<>();
    Random rand = new Random();
    int n;
    int s = crimeSize;

    @FXML
    public void initialize()
    {
        int s = crimeSize;
        buttons.add(launderingTool1);
        buttons.add(launderingTool2);
        buttons.add(launderingTool3);
        buttons.add(launderingTool4);
        int i = 0;
        while(i < 4)
        {
            n = rand.nextInt(crimeSize -1 );
            for(int m = 0; m < 4; m ++)
            {
                if(buttons.get(i).getText() == buttons.get(m).getText())
                {
                    i--;
                }
                else
                {
                    buttons.get(i).setText(GameEngine.getCrimes().get(n).getDescription());
                }
                i++;
            }
        }
    }

    public void buyClicked(MouseEvent mouseEvent) {
        JFXButton button = (JFXButton) mouseEvent.getSource();
        JFXRadioButton selectedRadio = (JFXRadioButton)group.getSelectedToggle();
        String launderingTool = selectedRadio.getText();
        if ((button).getText().equals("Buy!")){
            for(int i = 0; i < crimeSize; i++)
            {
                if(selectedRadio.getText().equals(GameEngine.getCrimes.get(i).getDescription()))
                {
                    GameEngine.purchaseAsset(GameEngine.getCrimes.get(i));
                }
            }
        }
    }
}
