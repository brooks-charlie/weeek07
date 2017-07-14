package com.example.weeek07;

import java.io.Serializable;


/**
*  Card.java - Stores all the card's variables and logic.
*  @author Jayton
*  @version 1.0
* */
public class Card implements Serializable {
    private String prompt;
    private String answer;
    private int score;

    //Default constructor. Sets score to zero.
    Card() {
        score = 0;
    }

    // Non-Default constructor
    /*
    * creates a card with parameters.
    * initailzs a card with a prompt and answer string parameters
    * @param prompt A variable of type String.
    * @param answer A variable of type String.
    * */
    Card(String prompt, String answer) {
        this.prompt = prompt;
        this.answer = answer;
        score = 0;
    }
    /**
    * Keeps track of users mastery of card.
    * @return A boolean Data type
    * */
    public boolean isMastered() {
        if (score >= 5) {
            return true;
        } else {
            return false;
        }
    }
    /**
    *  Subtracts for the cards score
    *  this help to known when user has mastered the card
    * */
    public void gotWrong() {
        score = score - 1;
    }

    public void gotRight() {
        score = score + 1;
    }
    /**
    *  Retrieve the value of  answer.
    *  @return A String data type.
    * */
    public int getScore() {
        return score;
    }
    /**
    *  Retrieve the value of  answer.
    * @return A String data type.
    * */
    public String getPrompt() {
        return prompt;
    }
    /**
     * Sets the value of Prompt or the flash card's question
     *@param prompt A variable of type string
     * */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    /**
    *  Retrieve the value of  answer.
    * @return A String data type.
    * */
    public String getAnswer() {
        return answer;
    }

   /**
    *sets the answer's variable or the
    *@param answer A variable of type string
    **/
    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
