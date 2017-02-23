package com.application.spokn;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity {

    final int RC_SIGN_IN = 0;
    ImageView lImage;
    LinearLayout fadeLayout;
    Button loginBtn;
    EditText email,password;
    Context context;
    GoogleApiClient mGoogleApiClient;
    SignInButton signInButton;


    static String emailId,passwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();

        /*Updating Policy to access MySQL*/
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        /*For Google Sign-In*/
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                //.enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        fade();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            try {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount acct = result.getSignInAccount();
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                Global.setFName(personGivenName);
                Global.setLName(personFamilyName);
            }catch(Exception e){
                e.printStackTrace();
            }



            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    /*Function to login the user*/
    public void login(){
        /*Assigning Email and Password values to the Variables*/


        DBConnect dbc = new DBConnect();

        if(email.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
            Toast.makeText(context,"User ID or Password not Input",Toast.LENGTH_SHORT).show();
        }
        else {
            Global.setFName(email.getText().toString());
            Global.setFName(password.getText().toString());
            if (dbc.loginUser()) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                Toast.makeText(context, "Invalid User", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*Function to fade the Logo*/
    public void fade(){
        /*For Fading SPOKN Logo*/
        /*lImage = (ImageView) findViewById(R.id.logoImg);
        lImage.animate().alpha(0.0f).setDuration(2500); //.translationY(-350)*/

        /*For Fading Background*/
        fadeLayout = (LinearLayout) findViewById(R.id.fadeLayout);
        fadeLayout.animate().alpha(0.0f).setDuration(2500);
    }
}
