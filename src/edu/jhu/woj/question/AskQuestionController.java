package edu.jhu.woj.question;

/**
 * Created by Graciela on 7/12/2017.
 */
import java.io.IOException;
import java.util.*;
import edu.jhu.woj.Main;
import edu.jhu.woj.model.Question;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class AskQuestionController {

    private final int TIME = 10;
    private Integer seconds = TIME;

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
    private TextField question;
    @FXML
    private TextField countDown;
    @FXML
    private Button showAnswer;
    private Timeline time;

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
        Question q = Main.qb.getNextUnansweredQuestionForCategory(1, choice);
        question.setText(q.getQuestionText());
        countDown.setText(seconds.toString());
        doCountDown();
    }

    private void doCountDown() {
        time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event) {
                seconds --;
                countDown.setText(seconds.toString());
                if (seconds <=0) {
                    time.stop();
                    try {
                        Main.showAnswerScene();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    @FXML
    private void goGiveAnswer() throws IOException{
        time.stop();
        Main.showAnswerScene();
    }
}
