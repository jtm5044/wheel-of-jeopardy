package edu.jhu.woj.newround;

import java.io.IOException;

import edu.jhu.woj.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StartNewRoundController {

    @FXML
    private TextField firstPlayer;
    @FXML
    private TextField secondPlayer;
    @FXML
    private TextField firstPlayerTokens;
    @FXML
    private TextField secondPlayerTokens;
    @FXML
    private TextField remainingSpins;
    @FXML
    private Button startNewRound;

    @FXML
    private void initialize() {
        firstPlayer.setText(Main.playerA.getPlayerName());
        secondPlayer.setText(Main.playerB.getPlayerName());
        firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerScore()));
        secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerScore()));
        remainingSpins.setText(Integer.toString(Main.spinsCounter));
    }

    @FXML
    private void goStartAgain() throws IOException{
        System.out.println("roundCounter at StartNewRoundController: " + Main.roundCounter);
        Main.showMainGameScene();
        /*
        if (Main.roundCounter > 0) {
            Main.showMainGameScene();
        }
        else {
            Main.showGameIsOver();
        }
        */
    }
}
