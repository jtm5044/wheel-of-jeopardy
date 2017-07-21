package edu.jhu.woj.answer;

/**
 * Created by Graciela on 7/12/2017.
 */
import java.io.IOException;
import java.util.Random;

import edu.jhu.woj.Main;
import edu.jhu.woj.model.Question;
import edu.jhu.woj.model.Wheel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class GiveAnswerController {

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
    private Label category;
    @FXML
    private TextField answer;
    @FXML
    private RadioButton rightAnswer;
    @FXML
    private RadioButton wrongAnswer;
    @FXML
    private Button spinButton;
    private Question q;

    @FXML
    private void initialize() {
        firstPlayer.setText(Main.playerA.getPlayerName());
        secondPlayer.setText(Main.playerB.getPlayerName());
        firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerTurnFreeTokens()));
        secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerTurnFreeTokens()));
        firstPlayerScore.setText(Integer.toString(Main.playerA.getPlayerScore()));
        secondPlayerScore.setText(Integer.toString(Main.playerB.getPlayerScore()));
        remainingSpins.setText(Integer.toString(Main.spinsCounter));
        String choice = Main.wheel.getCurrentlySelectedWheelSector();
        category.setText(choice);
        q = Main.qb.getNextUnansweredQuestionForCategory(1, choice);
        answer.setText(q.getAnswerText());
    }

    @FXML void recordRightAnswer() {
        System.out.println("You reported your answer is RIGHT");
        q.setState(Question.QuestionState.QUESTION_STATE_ANSWERED_CORRECT);
        Main.getCurrentTurnPlayer().setPlayerScore(Main.getCurrentTurnPlayer().getPlayerScore() + q.getDollarAmount());
        firstPlayerScore.setText(Integer.toString(Main.playerA.getPlayerScore()));
        secondPlayerScore.setText(Integer.toString(Main.playerB.getPlayerScore()));
        try {
            Main.showMainGameScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void recordWrongAnswer() {
        System.out.println("You reported your answer is WRONG");
        q.setState(Question.QuestionState.QUESTION_STATE_ANSWERED_INCORRECT);
        Main.getCurrentTurnPlayer().setPlayerScore(Main.getCurrentTurnPlayer().getPlayerScore() - q.getDollarAmount());
        firstPlayerScore.setText(Integer.toString(Main.playerA.getPlayerScore()));
        secondPlayerScore.setText(Integer.toString(Main.playerB.getPlayerScore()));
        Main.startNextTurn();
        try {
            Main.showMainGameScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void spinWheel() throws IOException {
        if (Main.roundCounter > 0) {
            if (Main.spinsCounter > 0) {
                Random rand = new Random();
                int randomSector = rand.nextInt(11) + 0;
                Main.wheel.setCurrentlySelectedWheelIndex(randomSector);
                String choice = Main.wheel.getCurrentlySelectedWheelSector();
                System.out.println(randomSector + "\t\t" + choice + "\t\t" + "spinsCounter: " + Main.spinsCounter + "\t\t" + "roundsCounter: " + Main.roundCounter);
                Main.spinsCounter--;
                remainingSpins.setText(Integer.toString(Main.spinsCounter));
                switch (choice) {
                    case Wheel.WHEEL_SECTOR_LOSE_TURN:
                        System.out.println("Do action for " + Wheel.WHEEL_SECTOR_LOSE_TURN);
                        break;
                    case Wheel.WHEEL_SECTOR_FREE_TURN:
                        System.out.println("Do action for " + Wheel.WHEEL_SECTOR_FREE_TURN);
                        break;
                    case Wheel.WHEEL_SECTOR_BANKRUPT:
                        System.out.println("Do action for " + Wheel.WHEEL_SECTOR_BANKRUPT);
                        break;
                    case Wheel.WHEEL_SECTOR_PLAYERS_CHOICE:
                        System.out.println("Do action for " + Wheel.WHEEL_SECTOR_PLAYERS_CHOICE);
                        break;
                    case Wheel.WHEEL_SECTOR_OPP_CHOICE:
                        System.out.println("Do action for " + Wheel.WHEEL_SECTOR_OPP_CHOICE);
                        break;
                    case Wheel.WHEEL_SECTOR_SPIN_AGAIN:
                        System.out.println("Do action for " + Wheel.WHEEL_SECTOR_SPIN_AGAIN);
                        break;
                    default:
                        System.out.println("Jeopardy!!");
                        Main.showQuestionScene();
                        break;
                }
            }
            else {
                Main.roundCounter--;
                System.out.println("roundCounter at GiveAnswerController: " + Main.roundCounter);
                if (Main.roundCounter > 0) {
                    Main.showStartNewRound();
                }
                else {
                    Main.showGameIsOver();
                }
            }
        }
    }
}
