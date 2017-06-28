package edu.jhu.woj.model;

/**
 * Created by jeremy on 6/27/17.
 */
public class Question {

    /**
     * Enum used for the question state: unanswered, answered correctly, answered incorrectly
     */
    public enum QuestionState {
        QUESTION_STATE_UNANSWERED,
        QUESTION_STATE_ANSWERED_CORRECT,
        QUESTION_STATE_ANSWERED_INCORRECT;
    }

    /**
     * The state of the question
     */
    private QuestionState questionState = QuestionState.QUESTION_STATE_UNANSWERED;

    /**
     * The dollar amount of the question
     * For example "200", which will be displayed as "$200"
     */
    private int dollarAmount = -1;

    /**
     * Whether or not the question is a "daily double" jeopardy question
     */
    private boolean dailyDouble = false;

    /**
     * Optional path to a media file that is displayed to the user as a question
     */
    private String pathToMedia = "";

    /**
     * The text that is displayed to the user. In the Jeopardy game show this is phrased as an answer.
     * For example "This number, one of the first 20, uses only one vowel (4 times!)."
     */
    private String questionText = "";

    /**
     * The correct response to the question. In the Jeopardy game show this is phrased as a question.
     * For example "What is Seventeen?"
     */
    private String answerText = "";


    /**
     * Constructs a question.
     * @param questionState The state of the question: unanswered, answered correctly, answered incorrectly
     * @param dollarAmount The dollar amount of the question
     * @param dailyDouble Whether or not the question is a "daily double" jeopardy question
     * @param pathToMedia Optional path to a media file that is displayed to the user as a question. Empty string indicates no media file
     * @param questionText The text that is displayed to the user. In the Jeopardy game show this is phrased as an answer.
     * @param answerText The correct response to the question. In the Jeopardy game show this is phrased as a question.
     */
    public Question(QuestionState questionState,
                    int dollarAmount,
                    boolean dailyDouble,
                    String pathToMedia,
                    String questionText,
                    String answerText)
    {
        this.questionState = questionState;
        this.dollarAmount = dollarAmount;
        this.dailyDouble = dailyDouble;
        this.pathToMedia = pathToMedia;
        this.questionText = questionText;
        this.answerText = answerText;

    }

    /**
     * Returns the state of this question
     * @return {@link QuestionState}
     */
    public QuestionState getQuestionState() {
        return questionState;
    }

    /**
     * Sets the state of this question
     * @param questionState {@link QuestionState}
     */
    public void setQuestionState(QuestionState questionState) {
        this.questionState = questionState;
    }

    /**
     * Returns the dollar amount of the question
     * @return the dollar amount
     */
    public int getDollarAmount() {
        return dollarAmount;
    }

    /**
     * Sets the dollar amount of the question
     * @param dollarAmount
     */
    public void setDollarAmount(int dollarAmount) {
        this.dollarAmount = dollarAmount;
    }

    /**
     * Returns whether or not the question is a daily double question
     * @return true if the question is a daily double question, false otherwise
     */
    public boolean isDailyDouble() {
        return dailyDouble;
    }

    /**
     * Sets this question to be a daily double question
     * @param dailyDouble true if daily double question, false otherwise
     */
    public void setDailyDouble(boolean dailyDouble) {
        this.dailyDouble = dailyDouble;
    }

    /**
     * Returns the path to the media file. This field is optional.
     * @return the path to the media file, an empty string otherwise.
     */
    public String getPathToMedia() {
        return pathToMedia;
    }

    /**
     * Sets the path to the media file.
     * @param pathToMedia
     */
    public void setPathToMedia(String pathToMedia) {
        this.pathToMedia = pathToMedia;
    }

    /**
     * Returns the question text. In the Jeopardy game show this is phrased as an answer.
     * For example "This number, one of the first 20, uses only one vowel (4 times!)."
     * @return
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Sets the question text. In the Jeopardy game show this is phrased as an answer.
     * For example "This number, one of the first 20, uses only one vowel (4 times!)."
     * @param questionText
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Returns the answer text. In the Jeopardy game show this is phrased as a question.
     * For example "What is Seventeen?"
     * @return
     */
    public String getAnswerText() {
        return answerText;
    }

    /**
     * Sets the answer text. In the Jeopardy game show this is phrased as a question.
     * For example "What is Seventeen?"
     * @param answerText
     */
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
