package com.example.weeek07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    FirebaseUser fbUser;
    User user;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    DatabaseReference userDBReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        fbUser = mAuth.getCurrentUser();
        Log.d("Create deck on create", fbUser.getUid());
        userDBReference = FirebaseDatabase.getInstance().getReference().child("users").child(fbUser.getUid());

        ValueEventListener userLister = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                // LOG THE DECKS WITH MAP ITTERATOR
                //// TODO: 6/30/2017  
               
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        userDBReference.addValueEventListener(userLister);


    }
}
