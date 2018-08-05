package lingua.controllers;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dwagner6506.views.R;

import lingua.data.DBHandler;
import lingua.model.User;
import lingua.model.languages.French;
import lingua.model.languages.German;
import lingua.model.languages.Spanish;

/**
 * SIGN UP SCREEN
 * email,username,pass
 *
 * if successful
 * * persists USER to mainLang screen
 */
public class SignUp extends AppCompatActivity{



    // UI references.

    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mUsernameView;
    private TextView mErrorView;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        recreate();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_sign_up);
        }else
        {
            setContentView(R.layout.activity_sign_up_land);
        }
        // Set up the Login form.
        mUsernameView = (EditText)findViewById(R.id.username);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mErrorView  = (TextView) findViewById(R.id.error);



        Button btn = (Button)findViewById(R.id.addUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser(v);
            }
        });





    }


    public void signUpUser(View v){
        Spanish sp = new Spanish();
        French fr = new French();
        German de = new German();
        User newUser = new User(sp,fr,de, "");

        String email = mEmailView.getText().toString();
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();
        String errorMsg = "";
        Boolean error = false;

        if(email.length() < 3 || !email.contains("@") || !email.contains("."))
        {
            errorMsg += "Email is not valid!\n";
            error = true;
        }
        if(username.length() < 3)
        {
            errorMsg += "Username must be at least 3 characters \n";
            error = true;
        }
        if(password.length() <6)
        {
            errorMsg += "Password must be at least 6 characters\n";
            error = true;
        }




        Log.d("e", errorMsg);


        if(!error) {
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(password);

            //TODO: add user to db

            DBHandler db = new DBHandler(this);
            db.getWritableDatabase();

            if(db.checkUser(newUser))
            {
                errorMsg = "Username already exists!";
                mErrorView.setText(errorMsg);
                mPasswordView.setText("");
                mUsernameView.setText("");
                mEmailView.setText("");
            }
            else {

                db.addUser(newUser);
                Intent intent = new Intent(SignUp.this, MainLanguageScreen.class);

                Bundle b = new Bundle();
                b.putParcelable("currUser",newUser);



                intent.putExtras(b);
                startActivity(intent);
            }

        }
        else
        {
            mErrorView.setText(errorMsg);
            mPasswordView.setText("");
            mUsernameView.setText("");
            mEmailView.setText("");
        }

    }




}

