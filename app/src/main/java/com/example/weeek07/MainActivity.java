package com.example.weeek07;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private static final String TAG_NAME = "DECK LOAD";
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG_NAME, "onCreate Start!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Progress Dialog
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Logging In...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        progressDialog.show();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addConnectionCallbacks(this)
                .build();

    }

    public void createDeck(View view){
        Intent i = new Intent(getApplicationContext(), CreateDeckActivity.class);
        startActivity(i);
    }


    public void loadDecksClicked(View view) {
        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
        i.putExtra("classDestination", QuizActivity.class);
        startActivity(i);
    }
    public void signOutClicked(View v){
        //Firebase sign out
        FirebaseAuth.getInstance().signOut();
        //Google Sign-out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        Intent i = new Intent(getApplicationContext(), GoogleSignInActivity.class);
        startActivity(i);
    }

    public void manageDecksClicked(View v){
        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
        i.putExtra("classDestination", ManageDeck.class);
        startActivity(i);
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        progressDialog.dismiss();
        Log.d("Authentication", "Connected!");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
