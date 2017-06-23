package javafx_sample_DELETE_WHEN_READY;

import edu.jhu.woj.model.Wheel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ArrayList<String> qb = new ArrayList<String>();
        qb.add("Hello");
        qb.add("World");
        qb.add("Jeopardy");
        qb.add("Wheel");
        qb.add("Question");
        qb.add("Board");
        Wheel w = new Wheel(qb);

        String[] ws = w.getWheel();
        ws[1] = "TESTEROO";

        for(int i = 0; i < ws.length; i++)
        {
            System.out.println(ws[i]);
        }
        System.out.println("-------------------------------------");
        ws = w.getWheel();
        for(int i = 0; i < ws.length; i++)
        {
            System.out.println(ws[i]);
        }

        w.setCurrentlySelectedWheelIndex(10);
        w.setCurrentlySelectedWheelSector("Jeopardy");

        System.out.println(w.toString());
        launch(args);

    }
}
