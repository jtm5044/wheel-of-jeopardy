package edu.jhu.woj.question;

/**
 * Created by Graciela on 7/12/2017.
 */
import java.io.IOException;

import edu.jhu.woj.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AskQuestionController {

    private Main main;

    @FXML
    private TextField firstPlayer;
    @FXML
    private TextField secondPlayer;
    @FXML
    private TextField firstPlayerTokens;
    @FXML
    private TextField secondPlayerTokens;

    @FXML
    private void initialize() {
        firstPlayer.setText(Main.playerA.getPlayerName());
        secondPlayer.setText(Main.playerB.getPlayerName());
        firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerScore()));
        secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerScore()));
    }

    @FXML
    private void goGiveAnswer() throws IOException{
        main.showAnswerScene();
    }

}
