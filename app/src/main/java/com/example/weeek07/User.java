package com.example.weeek07;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jayton on 6/22/2017.
 */

public class User implements Serializable{

    public String userName;
    public String email;
    public Map<String, Deck> deckList;
    public User(){}

    public User(String name, String email) {
        this.email = email;
        this.userName = name;
    }
}
