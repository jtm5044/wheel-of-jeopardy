package edu.jhu.woj.view;

/**
 * Created by Graciela on 7/12/2017.
 */
import java.io.IOException;

import edu.jhu.woj.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PlayerNamesController {

    private final String PROMPT_TEXT = "Enter Player Name";
    private Main main;

    // Player user names
    @FXML
    private TextField firstUserTextField;
    @FXML
    private TextField secondUserTextField;

    /*
    @FXML
    private void goMainGame() throws IOException{
        main.showMainGameScene();
    }
    */

    @FXML
    private void readUserNames() throws IOException{

        /*Set first and second user name Strings to the customized user
         * values entered. Otherwise, set to default values if user did
         * not customize user names*/
        if (firstUserTextField.getText().equals(PROMPT_TEXT)
                || firstUserTextField.getText().equals(""))
        {
            Main.playerA.setPlayerName(Main.DEFAULT_USR1);
        }
        else
        {
            Main.playerA.setPlayerName(firstUserTextField.getText());
        }
        if (secondUserTextField.getText().equals(PROMPT_TEXT)
                || secondUserTextField.getText().equals(""))
        {
            Main.playerB.setPlayerName(Main.DEFAULT_USR2);
        }
        else
        {
            Main.playerB.setPlayerName(secondUserTextField.getText());
        }

        System.out.println(Main.playerA.getPlayerName() + "\t" + Main.playerB.getPlayerName());
        main.showMainGameScene();
    }

}
