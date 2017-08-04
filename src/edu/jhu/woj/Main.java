package edu.jhu.woj;

/**
 * Created by Graciela on 7/12/2017.
 */
import java.io.*;
import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;

import edu.jhu.woj.model.Player;
import edu.jhu.woj.model.QuestionBoard;
import edu.jhu.woj.model.Wheel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public final static int MAX_ROUNDS_PER_GAME = 2;
    public final static int MAX_SPINS_PER_ROUND = 50;
    public static int roundCounter = MAX_ROUNDS_PER_GAME;
    public static int spinsCounter = MAX_SPINS_PER_ROUND;
    public final static String DEFAULT_USR1 = "Player A";
    public final static String DEFAULT_USR2 = "Player B";
    public static Player playerA;
    public static Player playerB;
    public static QuestionBoard qb;
    public static Wheel wheel;
    private Stage primaryStage;
    private static BorderPane mainLayout;
    private static BufferedReader inFile;
    private static Player currentTurnPlayer;
    public static int currentRound = 1;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Wheel of Jeopardy");

        showMainView();
        showPlayerNamesView();
    }

    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showPlayerNamesView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/PlayerNames.fxml"));
        BorderPane playerNames = loader.load();
        mainLayout.setCenter(playerNames);
    }

    public static void showMainGameScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("spin/SpinWheel.fxml"));
        BorderPane wheelSpinner = loader.load();
        mainLayout.setCenter(wheelSpinner);
    }

    public static void showQuestionScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("question/AskQuestion.fxml"));
        BorderPane jeopardyQuestion = loader.load();
        mainLayout.setCenter(jeopardyQuestion);
    }

    public static void showAnswerScene() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("answer/GiveAnswer.fxml"));
        BorderPane jeopardyAnswer = loader.load();
        mainLayout.setCenter(jeopardyAnswer);
    }

    public static void showStartNewRound() throws IOException {
        System.out.println("spinCounter BEFORE resetting it: " + spinsCounter);
        spinsCounter = MAX_SPINS_PER_ROUND;
        System.out.println("spinCounter AFTER resetting it: " + spinsCounter);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("newround/StartNewRound.fxml"));
        BorderPane newRounder = loader.load();
        mainLayout.setCenter(newRounder);
    }

    public static void showGameIsOver() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("endgame/gameIsOver.fxml"));
        BorderPane endGame = loader.load();
        mainLayout.setCenter(endGame);
    }

    /**
     * Reads the next line from the input file.
     * @param inFile A buffered stream from a file that contains a postfix
     *              expression per line.
     * @return The next postfix expression from the input file.
     */
    private static String readFromFile(BufferedReader inFile)
    {
        String line=""; // Initialize line variable

        try
        {
            line = inFile.readLine(); // Read line from input file
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
            System.exit(2);
        }
        return line; // Return line from input file
    }

    public static void main(String[] args) {

        if (args.length != 1)
        {
            System.out.println("Usage: java Main [1]\n"
                    + "\t- Filename 1: Jeopardy Questions JSON Filename\n");
            System.exit(1);
        }

        try {
            inFile = new BufferedReader(new FileReader(args[0]));
        }
        catch (IOException e) {
            System.err.println(e.toString());
            return;
        }

        String fileContents = "";
        String line = readFromFile(inFile);
        while (line != null) {
            fileContents = fileContents +line;
            line = readFromFile(inFile);
        }

        try {
            inFile.close();
        }
        catch (IOException e) {
            System.err.println(e.toString());
        }

        Gson gson = new Gson();
        qb = gson.fromJson(fileContents, QuestionBoard.class);

        /*
        System.out.println("******* ROUND 1 DATA *****");
        String[] cats1 = qb.getCategories(1);
        for (int i=0; i<cats1.length; i++) {
            System.out.println(">>>> " + cats1[i]);
            Question[] questions = qb.getQuestionsForCategory(1, i);
            for (int j=0; j<questions.length; j++) {
                System.out.println((j+1) + ". " + questions[j].getQuestionText());
                System.out.println(questions[j].getAnswerText());
                System.out.println();
            }
        }

        System.out.println("******* ROUND 2 DATA *****");
        String[] cats2 = qb.getCategories(2);
        for (int i=0; i<cats2.length; i++) {
            System.out.println(">>>> " + cats2[i]);
            Question[] questions = qb.getQuestionsForCategory(2, i);
            for (int j=0; j<questions.length; j++) {
                System.out.println((j+1) + ". " + questions[j].getQuestionText());
                System.out.println(questions[j].getAnswerText());
                System.out.println();
            }
        }
        */

        List<String> jeopardyCategories = Arrays.asList(qb.getCategories(Main.currentRound));
        wheel = new Wheel(jeopardyCategories);
        playerA = new Player(DEFAULT_USR1);
        playerB = new Player(DEFAULT_USR2);
        currentTurnPlayer = playerA;
        launch(args);
    }

    public static Player getCurrentTurnPlayer()
    {
        return currentTurnPlayer;
    }

    public static void startNextTurn()
    {
        currentTurnPlayer = (currentTurnPlayer.equals(playerA)) ? playerB : playerA;
    }

    public static void startNewRound()
    {
        Main.playerA.setPlayerScore(0);
        Main.playerB.setPlayerScore(0);
        Main.currentRound++;
        List<String> jeopardyCategories = Arrays.asList(qb.getCategories(Main.currentRound));
        wheel = new Wheel(jeopardyCategories);

    }

    public static void startNewGame()
    {
        roundCounter = MAX_ROUNDS_PER_GAME;
        spinsCounter = MAX_SPINS_PER_ROUND;
        Main.currentRound = 1;
        qb.resetQuestionBoard();
        List<String> jeopardyCategories = Arrays.asList(qb.getCategories(Main.currentRound));
        wheel = new Wheel(jeopardyCategories);
        playerA = new Player(playerA.getPlayerName());
        playerB = new Player(playerB.getPlayerName());
        currentTurnPlayer = playerA;
    }
}
