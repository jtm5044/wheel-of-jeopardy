package edu.jhu.woj.model;

/**
 * Created by Graciela on 7/12/2017.
 */
public class PlayerModel {

    private String playerName;
    private int playerScore, playerFinalScore, playerTurnFreeTokens;

    public PlayerModel(String name) {
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
        int finalScore = this.getPlayerFinalScore();
        this.setPlayerScore(0);
        this.setPlayerFinalScore(finalScore-roundScore);
    }
}
