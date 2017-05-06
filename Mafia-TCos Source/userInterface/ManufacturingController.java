package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Basak Melis OCAL on 5/6/2017.
 */
public class ManufacturingController {
    public JFXButton buyButton;
    public JFXRadioButton manufacturingTool1;
    public JFXRadioButton manufacturingTool2;
    public JFXRadioButton manufacturingTool3;
    public JFXRadioButton manufacturingTool4;

    @FXML
    private ToggleGroup group;

    int crimeSize = GameEngine.getCrimes().size();
    private ArrayList<JFXRadioButton> buttons = new ArrayList<>();
    Random rand = new Random();
    int n;
    int s = crimeSize;


    @FXML
    public void initialize()
    {
        int s = crimeSize;
        buttons.add(manufacturingTool1);
        buttons.add(manufacturingTool2);
        buttons.add(manufacturingTool3);
        buttons.add(manufacturingTool4);
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
