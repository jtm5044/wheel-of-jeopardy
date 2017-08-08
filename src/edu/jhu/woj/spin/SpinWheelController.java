package edu.jhu.woj.spin;

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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

public class SpinWheelController {

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
    private Label currentTurnPlayerLabel;
    @FXML
    private TextField remainingSpins;
    @FXML
    private Button category1;
    @FXML
    private Button category2;
    @FXML
    private Button category3;
    @FXML
    private Button category4;
    @FXML
    private Button category5;
    @FXML
    private Button category6;
    @FXML
    private Label c1q1;
    @FXML
    private Label c1q2;
    @FXML
    private Label c1q3;
    @FXML
    private Label c1q4;
    @FXML
    private Label c1q5;
    @FXML
    private Label c2q1;
    @FXML
    private Label c2q2;
    @FXML
    private Label c2q3;
    @FXML
    private Label c2q4;
    @FXML
    private Label c2q5;
    @FXML
    private Label c3q1;
    @FXML
    private Label c3q2;
    @FXML
    private Label c3q3;
    @FXML
    private Label c3q4;
    @FXML
    private Label c3q5;
    @FXML
    private Label c4q1;
    @FXML
    private Label c4q2;
    @FXML
    private Label c4q3;
    @FXML
    private Label c4q4;
    @FXML
    private Label c4q5;
    @FXML
    private Label c5q1;
    @FXML
    private Label c5q2;
    @FXML
    private Label c5q3;
    @FXML
    private Label c5q4;
    @FXML
    private Label c5q5;
    @FXML
    private Label c6q1;
    @FXML
    private Label c6q2;
    @FXML
    private Label c6q3;
    @FXML
    private Label c6q4;
    @FXML
    private Label c6q5;

    @FXML
    private Label wheelOutcomeText;

    @FXML
    private Button spinWheelButton;

    @FXML
    private void initialize() {
        firstPlayer.setText(Main.playerA.getPlayerName());
        secondPlayer.setText(Main.playerB.getPlayerName());
        firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerTurnFreeTokens()));
        secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerTurnFreeTokens()));
        firstPlayerScore.setText(Integer.toString(Main.playerA.getPlayerScore()));
        secondPlayerScore.setText(Integer.toString(Main.playerB.getPlayerScore()));
        remainingSpins.setText(Integer.toString(Main.spinsCounter));
        currentTurnPlayerLabel.setText(Main.getCurrentTurnPlayer().getPlayerName() + "'s Turn...  Press Spin!");
        spinWheelButton.setDisable(false);
        String[] categories = Main.qb.getCategories(Main.currentRound);
        category1.setText(categories[0]);
        category2.setText(categories[1]);
        category3.setText(categories[2]);
        category4.setText(categories[3]);
        category5.setText(categories[4]);
        category6.setText(categories[5]);

        Question[] questions = Main.qb.getQuestionsForCategory(Main.currentRound, 0);
        c1q1.setText(questions[0].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[0].getDollarAmount()) : "--");
        c1q2.setText(questions[1].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[1].getDollarAmount()) : "--");
        c1q3.setText(questions[2].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[2].getDollarAmount()) : "--");
        c1q4.setText(questions[3].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[3].getDollarAmount()) : "--");
        c1q5.setText(questions[4].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[4].getDollarAmount()) : "--");

        questions = Main.qb.getQuestionsForCategory(Main.currentRound, 1);
        c2q1.setText(questions[0].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[0].getDollarAmount()) : "--");
        c2q2.setText(questions[1].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[1].getDollarAmount()) : "--");
        c2q3.setText(questions[2].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[2].getDollarAmount()) : "--");
        c2q4.setText(questions[3].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[3].getDollarAmount()) : "--");
        c2q5.setText(questions[4].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[4].getDollarAmount()) : "--");

        questions = Main.qb.getQuestionsForCategory(Main.currentRound, 2);
        c3q1.setText(questions[0].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[0].getDollarAmount()) : "--");
        c3q2.setText(questions[1].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[1].getDollarAmount()) : "--");
        c3q3.setText(questions[2].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[2].getDollarAmount()) : "--");
        c3q4.setText(questions[3].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[3].getDollarAmount()) : "--");
        c3q5.setText(questions[4].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[4].getDollarAmount()) : "--");

        questions = Main.qb.getQuestionsForCategory(Main.currentRound, 3);
        c4q1.setText(questions[0].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[0].getDollarAmount()) : "--");
        c4q2.setText(questions[1].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[1].getDollarAmount()) : "--");
        c4q3.setText(questions[2].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[2].getDollarAmount()) : "--");
        c4q4.setText(questions[3].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[3].getDollarAmount()) : "--");
        c4q5.setText(questions[4].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[4].getDollarAmount()) : "--");

        questions = Main.qb.getQuestionsForCategory(Main.currentRound, 4);
        c5q1.setText(questions[0].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[0].getDollarAmount()) : "--");
        c5q2.setText(questions[1].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[1].getDollarAmount()) : "--");
        c5q3.setText(questions[2].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[2].getDollarAmount()) : "--");
        c5q4.setText(questions[3].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[3].getDollarAmount()) : "--");
        c5q5.setText(questions[4].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[4].getDollarAmount()) : "--");

        questions = Main.qb.getQuestionsForCategory(Main.currentRound, 5);
        c6q1.setText(questions[0].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[0].getDollarAmount()) : "--");
        c6q2.setText(questions[1].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[1].getDollarAmount()) : "--");
        c6q3.setText(questions[2].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[2].getDollarAmount()) : "--");
        c6q4.setText(questions[3].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[3].getDollarAmount()) : "--");
        c6q5.setText(questions[4].getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED ? Integer.toString(questions[4].getDollarAmount()) : "--");

        if(Main.spinsCounter == 0)
        {
            outOfSpins();
        }
    }

    @FXML
    private void spinWheel() throws IOException {
        if (Main.roundCounter > 0) {
             if (Main.spinsCounter > 0) {
                spinWheelButton.setDisable(true);
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
                        wheelOutcomeText.setText("SORRY, LOSE TURN!");
                        if(Main.getCurrentTurnPlayer().getPlayerTurnFreeTokens() > 0) {
                            boolean useToken = showUseFreeTokenDialog();
                            if(!useToken)
                            {
                                Main.startNextTurn();
                                currentTurnPlayerLabel.setText(Main.getCurrentTurnPlayer().getPlayerName() + "'s Turn");
                            }else
                            {
                                Main.getCurrentTurnPlayer().setPlayerTurnFreeTokens(Main.getCurrentTurnPlayer().getPlayerTurnFreeTokens() - 1);
                                firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerTurnFreeTokens()));
                                secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerTurnFreeTokens()));
                                wheelOutcomeText.setText("FREE TOKEN REDEEMED. SPIN AGAIN.");
                            }
                        }else {
                            Main.startNextTurn();
                            currentTurnPlayerLabel.setText(Main.getCurrentTurnPlayer().getPlayerName() + "'s Turn");
                        }
                        spinWheelButton.setDisable(false);
                        break;
                    case Wheel.WHEEL_SECTOR_FREE_TURN:
                        System.out.println("Do action for " + Wheel.WHEEL_SECTOR_FREE_TURN);
                        wheelOutcomeText.setText("EARNED 1 FREE TURN TOKEN!, SPIN AGAIN!");
                        Main.getCurrentTurnPlayer().setPlayerTurnFreeTokens(Main.getCurrentTurnPlayer().getPlayerTurnFreeTokens() + 1);
                        firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerTurnFreeTokens()));
                        secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerTurnFreeTokens()));
                        spinWheelButton.setDisable(false);
                        break;
                    case Wheel.WHEEL_SECTOR_BANKRUPT:
                        System.out.println("Do action for " + Wheel.WHEEL_SECTOR_BANKRUPT);
                        wheelOutcomeText.setText("SORRY, BANKRUPT!");
                        Main.getCurrentTurnPlayer().goBankrupt();
                        firstPlayerScore.setText(Integer.toString(Main.playerA.getPlayerScore()));
                        secondPlayerScore.setText(Integer.toString(Main.playerB.getPlayerScore()));
                        Main.startNextTurn();
                        currentTurnPlayerLabel.setText(Main.getCurrentTurnPlayer().getPlayerName() + "'s Turn");
                        spinWheelButton.setDisable(false);
                        break;
                    case Wheel.WHEEL_SECTOR_PLAYERS_CHOICE:
                        System.out.println("Do action for " + Wheel.WHEEL_SECTOR_PLAYERS_CHOICE);
                        wheelOutcomeText.setText("PLAYER'S CHOICE: " + Main.getCurrentTurnPlayer().getPlayerName() + ", CHOOSE A CATEGORY!");
                        disableCategoryButtons(false);
                        break;
                    case Wheel.WHEEL_SECTOR_OPP_CHOICE:
                        System.out.println("Do action for " + Wheel.WHEEL_SECTOR_OPP_CHOICE);
                        if(Main.getCurrentTurnPlayer().equals(Main.playerA))
                        {
                            wheelOutcomeText.setText("OPPONENT'S CHOICE: " + Main.playerB.getPlayerName() + ", CHOOSE A CATEGORY!");
                        }else
                        {
                            wheelOutcomeText.setText("OPPONENT'S CHOICE: " + Main.playerA.getPlayerName() + ", CHOOSE A CATEGORY!");
                        }
                        disableCategoryButtons(false);
                        break;
                    case Wheel.WHEEL_SECTOR_SPIN_AGAIN:
                        System.out.println("Do action for " + Wheel.WHEEL_SECTOR_SPIN_AGAIN);
                        wheelOutcomeText.setText("SPIN AGAIN!");
                        spinWheelButton.setDisable(false);
                        break;
                    default:
                        System.out.println("Jeopardy!, Category: " + choice);
                        wheelOutcomeText.setText("CATEGORY: " + choice + ", GET READY ...");
                        showQuestion(choice);
                        break;
                }
            }
            else {
                 outOfSpins();
            }
        }
    }

    private void outOfSpins()
    {
        spinWheelButton.setDisable(true);
        wheelOutcomeText.setText("OUT OF SPINS, ROUND IS OVER...");
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }

        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
            int seconds = 3;
            public void handle(ActionEvent event) {
                wheelOutcomeText.setText("OUT OF SPINS, ROUND IS OVER... " + (seconds-1));
                seconds--;
                if(seconds <= 0)
                {
                    try {
                        time.stop();
                        spinWheelButton.setDisable(false);
                        Main.roundCounter--;
                        System.out.println("roundCounter at SpinWheelController: " + Main.roundCounter);
                        if (Main.roundCounter > 0) {
                            Main.showStartNewRound();
                        } else {
                            Main.showGameIsOver();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
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

    @FXML
    private void cat1ButtonPressed()
    {
        categoryButtonPressed(category1.getText());
    }
    @FXML
    private void cat2ButtonPressed()
    {
        categoryButtonPressed(category2.getText());
    }@FXML
    private void cat3ButtonPressed()
    {
        categoryButtonPressed(category3.getText());
    }@FXML
    private void cat4ButtonPressed()
    {
        categoryButtonPressed(category4.getText());
    }@FXML
    private void cat5ButtonPressed()
    {
        categoryButtonPressed(category5.getText());
    }@FXML
    private void cat6ButtonPressed()
    {
        categoryButtonPressed(category6.getText());
    }

    private void categoryButtonPressed(String choice)
    {
        disableCategoryButtons(true);
        wheelOutcomeText.setText("CATEGORY: " + choice + ", GET READY ... ");
        Main.wheel.setCurrentlySelectedWheelSector(choice);
        showQuestion(choice);
    }

    private void disableCategoryButtons(boolean disable)
    {
        category1.setDisable(disable);
        category2.setDisable(disable);
        category3.setDisable(disable);
        category4.setDisable(disable);
        category5.setDisable(disable);
        category6.setDisable(disable);
    }

    private void showQuestion(String choice)
    {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }

        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
            int seconds = 3;
            public void handle(ActionEvent event) {
                wheelOutcomeText.setText("CATEGORY: " + choice + ", GET READY ... " + (seconds-1));
                seconds--;
                if(seconds <= 0)
                {
                    try {
                        spinWheelButton.setDisable(false);
                        time.stop();
                        Main.showQuestionScene();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();

    }
}