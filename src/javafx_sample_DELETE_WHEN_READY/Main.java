package javafx_sample_DELETE_WHEN_READY;

import com.google.gson.Gson;
import edu.jhu.woj.model.Question;
import edu.jhu.woj.model.QuestionBoard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
//        ArrayList<String> qb = new ArrayList<String>();
//        qb.add("Hello");
//        qb.add("World");
//        qb.add("Jeopardy");
//        qb.add("Wheel");
//        qb.add("Question");
//        qb.add("Board");
//        Wheel w = new Wheel(qb);
//
//        String[] ws = w.getWheel();
//        ws[1] = "TESTEROO";
//
//        for(int i = 0; i < ws.length; i++)
//        {
//            System.out.println(ws[i]);
//        }
//        System.out.println("-------------------------------------");
//        ws = w.getWheel();
//        for(int i = 0; i < ws.length; i++)
//        {
//            System.out.println(ws[i]);
//        }
//
//        w.setCurrentlySelectedWheelIndex(10);
//        w.setCurrentlySelectedWheelSector("Jeopardy");
//
//        System.out.println(w.toString());

        String FILENAME = System.getProperty("user.dir") + File.separator + "conf" + File.separator + "default.qb";
        BufferedReader br = null;
        FileReader fr = null;

        String fileContents = "";
        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {
                fileContents+=sCurrentLine;
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (Exception ex) {

                ex.printStackTrace();

            }
        }
        Gson gson = new Gson();
        QuestionBoard qb3 = gson.fromJson(fileContents, QuestionBoard.class );

//        Question r1c1q1 = new Question(Question.QuestionState.QUESTION_STATE_UNANSWERED,
//                200,false, "", "r1c1q1", "world");
//
//        Question r1c1q2 = new Question(Question.QuestionState.QUESTION_STATE_UNANSWERED,
//                400,false, "", "r1c1q2", "world2");
//        Question r1c2q1 = new Question(Question.QuestionState.QUESTION_STATE_UNANSWERED,
//                200,false, "", "r1c2q1", "world");
//
//        Question r1c2q2 = new Question(Question.QuestionState.QUESTION_STATE_UNANSWERED,
//                400,false, "", "r1c2q2", "world2");
//
//        Question r2c1q1 = new Question(Question.QuestionState.QUESTION_STATE_UNANSWERED,
//                400,false, "", "r2c1q1", "world");
//
//        Question r2c1q2 = new Question(Question.QuestionState.QUESTION_STATE_UNANSWERED,
//                800,false, "", "r2c1q2", "world2");
//        Question r2c2q1 = new Question(Question.QuestionState.QUESTION_STATE_UNANSWERED,
//                400,false, "", "r2c2q1", "world");
//
//        Question r2c2q2 = new Question(Question.QuestionState.QUESTION_STATE_UNANSWERED,
//                800,false, "", "r2c2q2", "world2");
//
//        ArrayList<Question> r1c1qs = new ArrayList<Question>();
//        r1c1qs.add(r1c1q1);
//        r1c1qs.add(r1c1q2);
//
//        ArrayList<Question> r1c2qs = new ArrayList<Question>();
//        r1c2qs.add(r1c2q1);
//        r1c2qs.add(r1c2q2);
//
//        ArrayList<Question> r2c1qs = new ArrayList<Question>();
//        r2c1qs.add(r2c1q1);
//        r2c1qs.add(r2c1q2);
//
//        ArrayList<Question> r2c2qs = new ArrayList<Question>();
//        r2c2qs.add(r2c2q1);
//        r2c2qs.add(r2c2q2);
//
//        LinkedHashMap<String, ArrayList<Question>> firstRoundCategories = new LinkedHashMap<String, ArrayList<Question>>();
//        firstRoundCategories.put("Round 1, Cat 1", r1c1qs);
//        firstRoundCategories.put("Round 1, Cat 2", r1c2qs);
//
//        LinkedHashMap<String, ArrayList<Question>> secondRoundCategories = new LinkedHashMap<String, ArrayList<Question>>();
//        secondRoundCategories.put("Round 2, Cat 1", r2c1qs);
//        secondRoundCategories.put("Round 2, Cat 2", r2c2qs);
//
//        QuestionBoard qb2 = new QuestionBoard(firstRoundCategories, secondRoundCategories);
//
//        System.out.println(gson.toJson(qb2));
//
//        String jsonString = "{\"firstRoundCategories\":{\"Round 1, Cat 1\":[{\"questionState\":\"QUESTION_STATE_UNANSWERED\",\"dollarAmount\":200,\"dailyDouble\":false,\"pathToMedia\":\"\",\"questionText\":\"r1c1q1\",\"answerText\":\"world\"},{\"questionState\":\"QUESTION_STATE_UNANSWERED\",\"dollarAmount\":400,\"dailyDouble\":false,\"pathToMedia\":\"\",\"questionText\":\"r1c1q2\",\"answerText\":\"world2\"}],\"Round 1, Cat 2\":[{\"questionState\":\"QUESTION_STATE_UNANSWERED\",\"dollarAmount\":200,\"dailyDouble\":false,\"pathToMedia\":\"\",\"questionText\":\"r1c2q1\",\"answerText\":\"world\"},{\"questionState\":\"QUESTION_STATE_UNANSWERED\",\"dollarAmount\":400,\"dailyDouble\":false,\"pathToMedia\":\"\",\"questionText\":\"r1c2q2\",\"answerText\":\"world2\"}]},\"secondRoundCategories\":{\"Round 2, Cat 1\":[{\"questionState\":\"QUESTION_STATE_UNANSWERED\",\"dollarAmount\":400,\"dailyDouble\":false,\"pathToMedia\":\"\",\"questionText\":\"r2c1q1\",\"answerText\":\"world\"},{\"questionState\":\"QUESTION_STATE_UNANSWERED\",\"dollarAmount\":800,\"dailyDouble\":false,\"pathToMedia\":\"\",\"questionText\":\"r2c1q2\",\"answerText\":\"world2\"}],\"Round 2, Cat 2\":[{\"questionState\":\"QUESTION_STATE_UNANSWERED\",\"dollarAmount\":400,\"dailyDouble\":false,\"pathToMedia\":\"\",\"questionText\":\"r2c2q1\",\"answerText\":\"world\"},{\"questionState\":\"QUESTION_STATE_UNANSWERED\",\"dollarAmount\":800,\"dailyDouble\":false,\"pathToMedia\":\"\",\"questionText\":\"r2c2q2\",\"answerText\":\"world2\"}]}}\n";
//        QuestionBoard qb3 = gson.fromJson(jsonString,QuestionBoard.class);

        System.out.println("******* ROUND 1 CATEGORIES *****");
        String[] cats1 = qb3.getCategories(1);
        for(int i = 0; i < cats1.length; i++)
        {
            System.out.println(cats1[i]);
        }
        System.out.println("******* ROUND 2 CATEGORIES *****");
        String[] cats2 = qb3.getCategories(2);
        for(int i = 0; i < cats2.length; i++)
        {
            System.out.println(cats2[i]);
        }

        Question[] q1 = qb3.getQuestionsForCategory(1, 4);
        for(int i = 0; i < q1.length; i++)
        {
            System.out.println(q1[i].getQuestionText() + ", " + q1[i].getAnswerText());
        }

            launch(args);

    }
}
