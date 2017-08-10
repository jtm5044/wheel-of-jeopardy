package edu.jhu.woj.endgame;

import edu.jhu.woj.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GameIsOverController {

    @FXML
    private TextField firstPlayer;
    @FXML
    private TextField secondPlayer;
    @FXML
    private TextField firstPlayerRound1Score;
    @FXML
    private TextField firstPlayerRound2Score;
    @FXML
    private TextField firstPlayerFinalScore;
    @FXML
    private TextField secondPlayerRound1Score;
    @FXML
    private TextField secondPlayerRound2Score;
    @FXML
    private TextField secondPlayerFinalScore;
    @FXML
    private TextField winnerText;
    @FXML
    private Button yesButton;
    @FXML
    private Button quitButton;

    @FXML
    private void initialize() {

        int playerARound1Score = Main.playerA.getPlayerFinalScore();
        int playerBRound1Score = Main.playerB.getPlayerFinalScore();

        int playerARound2Score = Main.playerA.getPlayerScore();
        int playerBRound2Score = Main.playerB.getPlayerScore();

        int playerAFinalScore = playerARound1Score + playerARound2Score;
        int playerBFinalScore = playerBRound1Score + playerBRound2Score;

        String winner = "";
        if(playerAFinalScore > playerBFinalScore)
        {
            winner = Main.playerA.getPlayerName();
        }else if (playerBFinalScore > playerAFinalScore)
        {
            winner = Main.playerB.getPlayerName();
        }else
        {
            winner = "TIE!";
        }

        Main.playerA.setPlayerFinalScore(playerARound1Score + playerARound2Score);
        Main.playerB.setPlayerFinalScore(playerBRound1Score + playerBRound2Score);

        firstPlayer.setText(Main.playerA.getPlayerName());
        secondPlayer.setText(Main.playerB.getPlayerName());
        firstPlayerRound1Score.setText(Integer.toString(playerARound1Score));
        firstPlayerRound2Score.setText(Integer.toString(playerARound2Score));
        firstPlayerFinalScore.setText(Integer.toString(playerAFinalScore));
        secondPlayerRound1Score.setText(Integer.toString(playerBRound1Score));
        secondPlayerRound2Score.setText(Integer.toString(playerBRound2Score));
        secondPlayerFinalScore.setText(Integer.toString(playerBFinalScore));
        winnerText.setText(winner);
    }

    @FXML
    private void goStartNewGame() {
        Main.stopSound();
        Main.playSound(Main.SOUND_BUTTON_CLICK);
        Main.playSound(Main.SOUND_GAME_START);
        Main.startNewGame();
        try {
            Main.showMainGameScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goQuitGame() {
        Main.playSound(Main.SOUND_BUTTON_CLICK);
        System.exit(0);
    }
}
