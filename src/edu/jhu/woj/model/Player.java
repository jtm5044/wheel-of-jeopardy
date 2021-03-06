package edu.jhu.woj.model;

/**
 * Created by Graciela on 7/14/2017.
 */
public class Player {

    private String playerName;
    private int playerScore, playerFinalScore, playerTurnFreeTokens;

    public Player(String name) {
        this.playerName = name;
        this.playerScore = 0;
        this.playerFinalScore = 0;
        this.playerTurnFreeTokens = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        playerName = name;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int score) {
        playerScore = score;
    }

    public int getPlayerFinalScore() {
        return playerFinalScore;
    }

    public void setPlayerFinalScore(int score) {
        playerFinalScore = score;
    }

    public int getPlayerTurnFreeTokens() {
        return playerTurnFreeTokens;
    }

    public void setPlayerTurnFreeTokens(int tokens) {
        playerTurnFreeTokens = tokens;
    }

    public void goBankrupt() {
        int roundScore = this.getPlayerScore();
        // Only set to zero if the player's round score is positive.
        if(roundScore > 0)
        {
            this.setPlayerScore(0);
        }
    }
}

