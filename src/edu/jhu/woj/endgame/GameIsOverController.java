package edu.jhu.woj.endgame;

import edu.jhu.woj.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GameIsOverController {

    @FXML
    private TextField firstPlayer;
    @FXML
    private TextField secondPlayer;
    @FXML
    private TextField firstPlayerTokens;
    @FXML
    private TextField secondPlayerTokens;
    @FXML
    private Button yesButton;
    @FXML
    private Button quitButton;
    @FXML
    private TextField remainingSpins;

    @FXML
    private void initialize() {
        firstPlayer.setText(Main.playerA.getPlayerName());
        secondPlayer.setText(Main.playerB.getPlayerName());
        firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerScore()));
        secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerScore()));
        remainingSpins.setText(Integer.toString(Main.spinsCounter));
    }

    @FXML
    private void goStartNewGame() {
        System.out.println("Yes Button Pressed");
    }

    @FXML
    private void goQuitGame() {
        System.out.println("Quit Button Pressed");
    }
}
