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
 * LOGIN SCREEN
 * username & pass
 * persists USER to mainLang screen
 */
public class Login extends AppCompatActivity{



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
            setContentView(R.layout.activity_login);
        }else
        {
            setContentView(R.layout.activity_login_land);
        }
        // Set up the Login form.

        mUsernameView = (EditText) findViewById(R.id.username);


        mPasswordView = (EditText) findViewById(R.id.password);

        mErrorView  = (TextView) findViewById(R.id.error);



        Button btn = (Button)findViewById(R.id.addUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInUser(v);
            }
        });

    }
    Boolean dataError = false;
    public void logInUser(View v){

        Spanish sp = new Spanish();
        French fr = new French();
        German de = new German();
        User regUser = new User(sp,fr,de, "");

        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();
        String errorMsg = "";
        Boolean error = false;
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


            regUser.setUsername(username);
            regUser.setPassword(password);


          DBHandler db = new DBHandler(this);


            if(!db.checkUser(regUser))
            {
                errorMsg = "User does not Exist!";
                dataError = true;

            }
            else if(!db.checkPass(regUser))
            {
                errorMsg = "Wrong password!";
                dataError = true;
            }

                if(!dataError) {
                    Intent intent = new Intent(Login.this, MainLanguageScreen.class);

                    Bundle b = new Bundle();
                    b.putParcelable("currUser", regUser);

                    intent.putExtras(b);
                    startActivity(intent);
                }
                else
                {
                    dataError = false;
                    mErrorView.setText(errorMsg);
                    mPasswordView.setText("");
                    mUsernameView.setText("");
                }


        }
        else
        {
            mErrorView.setText(errorMsg);
            mPasswordView.setText("");
            mUsernameView.setText("");
        }

    }


}

