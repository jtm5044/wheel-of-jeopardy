package edu.jhu.woj.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jeremy on 6/22/17.
 */
public class QuestionBoard {

    /**
     * A hash map of first round categories with their corresponding questions.
     * The String key is the category text e.g. "Numbers"
     * The ArrayList<Question> holds questions in increasing point value order
     */
    private LinkedHashMap<String, ArrayList<Question>> firstRoundCategories = new LinkedHashMap<String, ArrayList<Question>>();

    /**
     * A hash map of second round categories with their corresponding questions.
     * The String key is the category text e.g. "Numbers"
     * The ArrayList<Question> holds questions in increasing point value order
     */
    private LinkedHashMap<String, ArrayList<Question>> secondRoundCategories = new LinkedHashMap<String, ArrayList<Question>>();

    /**
     * Constructor to initialize the question board
     * @param firstRoundCategories A hash map of first round categories with their corresponding questions.
     * @param secondRoundCategories A hash map of second round categories with their corresponding questions
     */
    public QuestionBoard(LinkedHashMap<String, ArrayList<Question>> firstRoundCategories,
                         LinkedHashMap<String, ArrayList<Question>> secondRoundCategories)
    {
        this.firstRoundCategories = firstRoundCategories;
        this.secondRoundCategories = secondRoundCategories;
    }

    public String[] getCategories(int round)
    {
        if(round != 1 && round != 2) { throw new IllegalArgumentException("Round must be either 1 or 2."); }

        String[] categories;
        if(round == 1)
        {
            categories = new String[firstRoundCategories.keySet().size()];
            return firstRoundCategories.keySet().toArray(categories);
        }else if(round == 2)
        {
            categories = new String[secondRoundCategories.keySet().size()];
            return secondRoundCategories.keySet().toArray(categories);
        }else
        {
            throw new RuntimeException("Unexpected error. Could not get categories.");
        }
    }

    public Question getNextUnansweredQuestionForCategory(int round, int categoryIndex)
    {
        Question[] qs = getQuestionsForCategory(round, categoryIndex);
        for(Question q : qs)
        {
            if(q.getState() == Question.QuestionState.QUESTION_STATE_UNANSWERED)
            {
                return q;
            }
        }
        return null;
    }

    public Question getNextUnansweredQuestionForCategory(int round, String category)
    {
        Question[] qs = getQuestionsForCategory(round, category);
        for(Question q : qs)
        {
            if(q.getState().equals(Question.QuestionState.QUESTION_STATE_UNANSWERED))
            {
                return q;
            }
        }
        return null;
    }

    public Question[] getQuestionsForCategory(int round, int categoryIndex)
    {
        if(round != 1 && round != 2) { throw new IllegalArgumentException("Round must be either 1 or 2."); }
        int index = 0;
        if(round == 1)
        {
            for(String key: firstRoundCategories.keySet()){
                if(index == categoryIndex)
                {
                    return getQuestionsForCategory(round, key);
                }
                index++;
            }

        }else if(round == 2)
        {
            for(String key: secondRoundCategories.keySet()){
                if(index == categoryIndex)
                {
                    return getQuestionsForCategory(round, key);
                }
                index++;
            }
        }
        throw new IllegalArgumentException("No category for index " + categoryIndex);
    }

    public Question[] getQuestionsForCategory(int round, String category)
    {
        if(round != 1 && round != 2) { throw new IllegalArgumentException("Round must be either 1 or 2."); }
        Question[] questions;
        if (round == 1) {
            if(firstRoundCategories.containsKey(category)) {
                questions = new Question[firstRoundCategories.get(category).size()];
                return firstRoundCategories.get(category).toArray(questions);
            }else
            {
                throw new IllegalArgumentException("Category not found.");
            }
        } else if (round == 2) {
            if(secondRoundCategories.containsKey(category)) {
                questions = new Question[secondRoundCategories.get(category).size()];
                return secondRoundCategories.get(category).toArray(questions);
            }else
            {
                throw new IllegalArgumentException("Category not found.");
            }
        } else {
            throw new RuntimeException("Unexpected error. Could not get questions.");
        }
    }

    public void resetQuestionBoard()
    {
        for (Map.Entry<String, ArrayList<Question>> entry : firstRoundCategories.entrySet()) {
            String key = entry.getKey();
            ArrayList<Question> value = entry.getValue();
            for(Question q : value)
            {
                q.setState(Question.QuestionState.QUESTION_STATE_UNANSWERED);
            }
        }
        for (Map.Entry<String, ArrayList<Question>> entry : secondRoundCategories.entrySet()) {
            String key = entry.getKey();
            ArrayList<Question> value = entry.getValue();
            for(Question q : value)
            {
                q.setState(Question.QuestionState.QUESTION_STATE_UNANSWERED);
            }
        }
    }
}
