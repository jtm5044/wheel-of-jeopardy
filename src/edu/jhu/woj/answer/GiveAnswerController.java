package edu.jhu.woj.answer;

/**
 * Created by Graciela on 7/12/2017.
 */
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import edu.jhu.woj.Main;
import edu.jhu.woj.model.Question;
import edu.jhu.woj.model.Wheel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        q = Main.qb.getNextUnansweredQuestionForCategory(Main.currentRound, choice);
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
        Main.getCurrentTurnPlayer().setPlayerScore(Main.getCurrentTurnPlayer().getPlayerScore() - q.getDollarAmount());
        firstPlayerScore.setText(Integer.toString(Main.playerA.getPlayerScore()));
        secondPlayerScore.setText(Integer.toString(Main.playerB.getPlayerScore()));
        q.setState(Question.QuestionState.QUESTION_STATE_ANSWERED_INCORRECT);

        if(Main.getCurrentTurnPlayer().getPlayerTurnFreeTokens() > 0) {
            boolean useToken = showUseFreeTokenDialog();
            if(!useToken)
            {
                Main.startNextTurn();

            }else
            {
                Main.getCurrentTurnPlayer().setPlayerTurnFreeTokens(Main.getCurrentTurnPlayer().getPlayerTurnFreeTokens() - 1);
                firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerTurnFreeTokens()));
                secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerTurnFreeTokens()));
            }
        }else {
            Main.startNextTurn();
        }

        try {
            Main.showMainGameScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean showUseFreeTokenDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LOSE TURN");
        alert.setHeaderText(Main.getCurrentTurnPlayer().getPlayerName() + ", you've lost your turn");
        alert.setContentText("Would you like to use a free token? Free tokens remaining: " + Main.getCurrentTurnPlayer().getPlayerTurnFreeTokens());

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes){
            return true;
        } else {
            return false;
        }
    }
}
