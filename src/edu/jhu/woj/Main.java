package edu.jhu.woj;

/**
 * Created by Graciela on 7/12/2017.
 */
import java.io.IOException;

import edu.jhu.woj.model.PlayerModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public final static String DEFAULT_USR1 = "Player A";
    public final static String DEFAULT_USR2 = "Player B";
    public static PlayerModel playerA;
    public static PlayerModel playerB;
    private Stage primaryStage;
    private static BorderPane mainLayout;

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

    public static void main(String[] args) {
        playerA = new PlayerModel(DEFAULT_USR1);
        playerB = new PlayerModel(DEFAULT_USR2);

        launch(args);
    }
}
