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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

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
    private Label wheelSectorLabel;
    @FXML
    private Label category;
    @FXML
    private Label correctAnswerPrompt;
    @FXML
    private TextField answer;
    @FXML
    private RadioButton rightAnswer;
    @FXML
    private RadioButton wrongAnswer;
    @FXML
    private Button goBackButton;
    private Question q;

    @FXML
    private void initialize() {
        goBackButton.setVisible(false);
        firstPlayer.setText(Main.playerA.getPlayerName());
        secondPlayer.setText(Main.playerB.getPlayerName());
        firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerTurnFreeTokens()));
        secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerTurnFreeTokens()));
        firstPlayerScore.setText(Integer.toString(Main.playerA.getPlayerScore()));
        secondPlayerScore.setText(Integer.toString(Main.playerB.getPlayerScore()));
        remainingSpins.setText(Integer.toString(Main.spinsCounter));
        String choice = Main.wheel.getCurrentlySelectedWheelSector();
        wheelSectorLabel.setText("Wheel Sector");
        category.setText(choice);
        q = Main.qb.getNextUnansweredQuestionForCategory(Main.currentRound, choice);
        answer.setText(q.getAnswerText());
        if(Main.timeExpired) {
            Task<Void> task = new Task<Void>() {

                @Override protected Void call() throws Exception {
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                timeExpired();
                            }
                        });
                    return null;
                }
            };
            task.run();
        }
    }

    private void timeExpired()
    {
        Main.timeExpired = false;

        q.setState(Question.QuestionState.QUESTION_STATE_ANSWERED_INCORRECT);

        wheelSectorLabel.setText("TIME EXPIRED");
        category.setText("The correct answer was...");

        correctAnswerPrompt.setVisible(false);
        rightAnswer.setVisible(false);
        wrongAnswer.setVisible(false);

        goBackButton.setVisible(true);
        if(Main.getCurrentTurnPlayer().getPlayerTurnFreeTokens() > 0) {

            boolean useToken = showUseFreeTokenDialog("TIME EXPIRED", ", time has expired.");
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
    }

    @FXML void goBackPressed()
    {
        correctAnswerPrompt.setVisible(true);
        rightAnswer.setVisible(true);
        wrongAnswer.setVisible(true);

        goBackButton.setVisible(false);
        try {
            Main.showMainGameScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            boolean useToken = showUseFreeTokenDialog("Incorrect Answer", ", you've answered incorrectly");
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

    private boolean showUseFreeTokenDialog(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(Main.getCurrentTurnPlayer().getPlayerName() + text);
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
