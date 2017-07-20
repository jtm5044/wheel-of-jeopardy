package edu.jhu.woj.spin;

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
import javafx.scene.control.TextField;

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
    private TextField remainingSpins;
    @FXML
    private Label category1;
    @FXML
    private Label category2;
    @FXML
    private Label category3;
    @FXML
    private Label category4;
    @FXML
    private Label category5;
    @FXML
    private Label category6;
    @FXML
    private Button c1q1;
    @FXML
    private Button c1q2;
    @FXML
    private Button c1q3;
    @FXML
    private Button c1q4;
    @FXML
    private Button c1q5;
    @FXML
    private Button c2q1;
    @FXML
    private Button c2q2;
    @FXML
    private Button c2q3;
    @FXML
    private Button c2q4;
    @FXML
    private Button c2q5;
    @FXML
    private Button c3q1;
    @FXML
    private Button c3q2;
    @FXML
    private Button c3q3;
    @FXML
    private Button c3q4;
    @FXML
    private Button c3q5;
    @FXML
    private Button c4q1;
    @FXML
    private Button c4q2;
    @FXML
    private Button c4q3;
    @FXML
    private Button c4q4;
    @FXML
    private Button c4q5;
    @FXML
    private Button c5q1;
    @FXML
    private Button c5q2;
    @FXML
    private Button c5q3;
    @FXML
    private Button c5q4;
    @FXML
    private Button c5q5;
    @FXML
    private Button c6q1;
    @FXML
    private Button c6q2;
    @FXML
    private Button c6q3;
    @FXML
    private Button c6q4;
    @FXML
    private Button c6q5;

    @FXML
    private void initialize() {
        firstPlayer.setText(Main.playerA.getPlayerName());
        secondPlayer.setText(Main.playerB.getPlayerName());
        firstPlayerTokens.setText(Integer.toString(Main.playerA.getPlayerScore()));
        secondPlayerTokens.setText(Integer.toString(Main.playerB.getPlayerScore()));
        remainingSpins.setText(Integer.toString(Main.spinsCounter));

        String[] categories = Main.qb.getCategories(1);
        category1.setText(categories[0]);
        category2.setText(categories[1]);
        category3.setText(categories[2]);
        category4.setText(categories[3]);
        category5.setText(categories[4]);
        category6.setText(categories[5]);

        Question[] questions = Main.qb.getQuestionsForCategory(1, 0);
        c1q1.setText(Integer.toString(questions[0].getDollarAmount()));
        c1q2.setText(Integer.toString(questions[1].getDollarAmount()));
        c1q3.setText(Integer.toString(questions[2].getDollarAmount()));
        c1q4.setText(Integer.toString(questions[3].getDollarAmount()));
        c1q5.setText(Integer.toString(questions[4].getDollarAmount()));

        questions = Main.qb.getQuestionsForCategory(1, 1);
        c2q1.setText(Integer.toString(questions[0].getDollarAmount()));
        c2q2.setText(Integer.toString(questions[1].getDollarAmount()));
        c2q3.setText(Integer.toString(questions[2].getDollarAmount()));
        c2q4.setText(Integer.toString(questions[3].getDollarAmount()));
        c2q5.setText(Integer.toString(questions[4].getDollarAmount()));

        questions = Main.qb.getQuestionsForCategory(1, 2);
        c3q1.setText(Integer.toString(questions[0].getDollarAmount()));
        c3q2.setText(Integer.toString(questions[1].getDollarAmount()));
        c3q3.setText(Integer.toString(questions[2].getDollarAmount()));
        c3q4.setText(Integer.toString(questions[3].getDollarAmount()));
        c3q5.setText(Integer.toString(questions[4].getDollarAmount()));

        questions = Main.qb.getQuestionsForCategory(1, 3);
        c4q1.setText(Integer.toString(questions[0].getDollarAmount()));
        c4q2.setText(Integer.toString(questions[1].getDollarAmount()));
        c4q3.setText(Integer.toString(questions[2].getDollarAmount()));
        c4q4.setText(Integer.toString(questions[3].getDollarAmount()));
        c4q5.setText(Integer.toString(questions[4].getDollarAmount()));

        questions = Main.qb.getQuestionsForCategory(1, 4);
        c5q1.setText(Integer.toString(questions[0].getDollarAmount()));
        c5q2.setText(Integer.toString(questions[1].getDollarAmount()));
        c5q3.setText(Integer.toString(questions[2].getDollarAmount()));
        c5q4.setText(Integer.toString(questions[3].getDollarAmount()));
        c5q5.setText(Integer.toString(questions[4].getDollarAmount()));

        questions = Main.qb.getQuestionsForCategory(1, 5);
        c6q1.setText(Integer.toString(questions[0].getDollarAmount()));
        c6q2.setText(Integer.toString(questions[1].getDollarAmount()));
        c6q3.setText(Integer.toString(questions[2].getDollarAmount()));
        c6q4.setText(Integer.toString(questions[3].getDollarAmount()));
        c6q5.setText(Integer.toString(questions[4].getDollarAmount()));
    }

    @FXML
    private void spinWheel() throws IOException {
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
                System.out.println("roundCounter at SpinWheelController: " + Main.roundCounter);
                if (Main.roundCounter >0) {
                    Main.showStartNewRound();
                }
                else {
                    Main.showGameIsOver();
                }
            }
        }
    }
}