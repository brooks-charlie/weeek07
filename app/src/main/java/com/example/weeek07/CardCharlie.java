package com.example.weeek07;

/**
 * Created by charliebrooks on 6/7/17.
 */

public class CardCharlie {
    private String prompt;
    private String answer;
    private String description;
    private int score;

    public CardCharlie(String prompt, String answer){
        this.prompt=prompt;
        this.answer=answer;
    }

    public int getScore() {
        return score;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getAnswer() {
        return answer;
    }

    public String getDescription() {
        return description;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public int calcScore(int cardScore){
        cardScore=1 * 2;
        return cardScore;
    }
}
