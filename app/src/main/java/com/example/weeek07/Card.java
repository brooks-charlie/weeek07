package com.example.weeek07;

import java.io.Serializable;

/**
 * Created by Jayton on 6/6/2017.
 */

public class Card implements Serializable {
    private String prompt;
    private String answer;

    Card(String prompt, String answer) {
        this.prompt = prompt;
        this.answer = answer;
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
