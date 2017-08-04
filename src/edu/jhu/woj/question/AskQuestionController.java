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
import javafx.scene.control.*;
import javafx.util.Duration;

public class AskQuestionController {

    private final int TIME = 10;
    private double seconds = TIME;

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
    private Label playerNameText;
    @FXML
    private Label category;
    @FXML
    private TextArea questionTextArea;
    @FXML
    private TextField countDown;
    @FXML
    private ProgressBar timerProgress;
    @FXML
    private Button showAnswer;
    private Timeline time;
    private double timeProgressValue = 0.0;

    @FXML
    private void initialize() {
        firstPlayer.setText(Main.playerA.getPlayerName());
        secondPlayer.setText(Main.playerB.getPlayerName());
        firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerTurnFreeTokens()));
        secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerTurnFreeTokens()));
        firstPlayerScore.setText(Integer.toString(Main.playerA.getPlayerScore()));
        secondPlayerScore.setText(Integer.toString(Main.playerB.getPlayerScore()));
        playerNameText.setText(Main.getCurrentTurnPlayer().getPlayerName() + ", Answer Now!");
        remainingSpins.setText(Integer.toString(Main.spinsCounter));

        String choice = Main.wheel.getCurrentlySelectedWheelSector();
        category.setText(choice);
        Question q = Main.qb.getNextUnansweredQuestionForCategory(Main.currentRound, choice);
        //questionTextArea.setFont();
        questionTextArea.setText(q.getQuestionText());
        countDown.setText(Double.toString(seconds));
        //timerProgress = new ProgressBar(1);
        timeProgressValue = (double)(TIME-seconds)/(double)TIME;
        //System.out.println("Time progress:" + timeProgressValue);
        timerProgress.setProgress(timeProgressValue);
        doCountDown();
    }

    private void doCountDown() {
        time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event) {
                seconds -= 0.005;
                countDown.setText(Double.toString(seconds));
                timeProgressValue = (double)(TIME-seconds)/(double)TIME;
                //System.out.println("Time progress:" + timeProgressValue);
                timerProgress.setProgress(timeProgressValue);
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
