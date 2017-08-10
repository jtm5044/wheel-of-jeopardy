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
    private TextField firstPlayerScore;
    @FXML
    private TextField secondPlayerScore;
    @FXML
    private TextField remainingSpins;
    @FXML
    private Button startNewRound;

    @FXML
    private void initialize() {

        Main.playerA.setPlayerFinalScore(Main.playerA.getPlayerScore());
        Main.playerB.setPlayerFinalScore(Main.playerB.getPlayerScore());

        firstPlayer.setText(Main.playerA.getPlayerName());
        secondPlayer.setText(Main.playerB.getPlayerName());
        firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerTurnFreeTokens()));
        secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerTurnFreeTokens()));
        firstPlayerScore.setText(Integer.toString(Main.playerA.getPlayerFinalScore()));
        secondPlayerScore.setText(Integer.toString(Main.playerB.getPlayerFinalScore()));
        remainingSpins.setText(Integer.toString(Main.spinsCounter));
    }

    @FXML
    private void goStartAgain() throws IOException{
        Main.playSound(Main.SOUND_BUTTON_CLICK);
        System.out.println("roundCounter at StartNewRoundController: " + Main.roundCounter);
        Main.startNewRound();
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
