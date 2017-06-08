package com.example.weeek07;

import java.io.Serializable;

/**
 * Created by Jayton on 6/6/2017.
 */

public class Card implements Serializable {
    private String prompt;
    private String answer;
    private int score;

    //Default constructor. Sets score to zero.
    Card() {
        score = 0;
    }

    // Non-Default constructor
    Card(String prompt, String answer) {
        this.prompt = prompt;
        this.answer = answer;
        score = 0;
    }

    public boolean isMastered() {
        if (score >= 5) {
            return true;
        } else {
            return false;
        }
    }

    public void gotWrong() {
        score = score - 1;
    }
    public void gotRight() {
        score = score + 1;
    }
    public int getScore() {
        return score;
    }
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
