package lingua.controllers;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.*;
import android.content.*;
import android.app.*;

import lingua.data.DBHandler;
import lingua.model.User;

import lingua.model.languages.*;
import lingua.services.ReminderReciever;

import com.example.dwagner6506.views.R;

/**MAIN LANGUAGE SCREEN
 * FIRST SCREEN USERS SEE WHEN LOGGED IN
 * SHOWS LANGUAGE LVLS AND LETS USER CHOOSE ONE TO LEARN
 */

public class MainLanguageScreen extends AppCompatActivity {
    private final String SPANISH = "spanish";
    private final String GERMAN = "german";
    private final String FRENCH = "french";

    private User currentUser ;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        recreate();
    }

    private  Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set background service to remind user in future to use app!
        this.context = this;
        Intent alarm = new Intent(this.context, ReminderReciever.class);
        boolean alarmRunning = (PendingIntent.getBroadcast(this.context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);

        currentUser = getIntent().getExtras().getParcelable("currUser");
        DBHandler db = new DBHandler(this);
        db.getWritableDatabase();
        if(!alarmRunning && !db.isUserNotified(currentUser.getUsername())) {
            db.notifiedUser(currentUser.getUsername());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.context, 0, alarm, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(), 60000, pendingIntent);
        }



        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_choose_language);
        }else
        {
            setContentView(R.layout.activity_choose_language_land);
        }

        ImageButton imgBtnFR = (ImageButton) findViewById(R.id.french);



        ImageButton imgBtnDE = (ImageButton) findViewById(R.id.german);



        ImageButton imgBtnEP = (ImageButton) findViewById(R.id.spanish);




        Button btnSet = (Button)findViewById(R.id.settings);




        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLanguageScreen.this, Settings.class);
                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

        Button btnFrnd = (Button)findViewById(R.id.friends);

        btnFrnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLanguageScreen.this, Friends.class);
                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }
        });



        String userGreeting;
        String spanishLvlMsg;
        String frenchLvlMsg;
        String germanLvlMsg;


        TextView helloUser = (TextView) findViewById(R.id.user);
        TextView spanishLvl = (TextView) findViewById(R.id.spanishLvl);
        TextView frenchLvl = (TextView) findViewById(R.id.frenchLvl);
        TextView germanLvl = (TextView) findViewById(R.id.germanLvl);

        ImageView spanishButton = (ImageView) findViewById(R.id.spanish);
        ImageView frenchButton = (ImageView) findViewById(R.id.french);
        ImageView germanButton = (ImageView) findViewById(R.id.german);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            currentUser = bundle.getParcelable("currUser");








            if(currentUser != null) {


                Integer userID;
                db.getWritableDatabase();
                userID = db.getUserID(currentUser.getUsername());
                currentUser = db.setUserLangLevels(userID, currentUser);


                userGreeting = currentUser.getUsername();

                if(currentUser.getSpanishLvl() < Spanish.MAX_LVL + 1) {

                    spanishLvlMsg = "LEVEL: " + currentUser.getSpanishLvl() + " / " + Integer.toString(Spanish.MAX_LVL);
                }
                else
                {
                    spanishLvlMsg = "PASSED!";
                    spanishButton.setEnabled(false);
                }

                if(currentUser.getFrenchLvl() < French.MAX_LVL + 1) {

                    frenchLvlMsg = "LEVEL: " + currentUser.getFrenchLvl() + " / " + Integer.toString(French.MAX_LVL);
                }else
                {
                    frenchLvlMsg = "PASSED!";
                    frenchButton.setEnabled(false);

                }

                if(currentUser.getGermanLvl() < German.MAX_LVL) {
                    germanLvlMsg = "LEVEL: " + currentUser.getGermanLvl() + " / " + Integer.toString(German.MAX_LVL);
                }
                else
                {
                    germanLvlMsg = "PASSED!";
                    germanButton.setEnabled(false);
                }


                spanishLvl.setText(spanishLvlMsg);
                frenchLvl.setText(frenchLvlMsg);
                germanLvl.setText(germanLvlMsg);

                helloUser.setText(userGreeting);
            }




        imgBtnFR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.setCurrentLang(FRENCH);

                Intent intent = new Intent(MainLanguageScreen.this, Quiz.class);
                 Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);

            }
        });

        imgBtnEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.setCurrentLang(SPANISH);
                Intent intent = new Intent(MainLanguageScreen.this, Quiz.class);
                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

        imgBtnDE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentUser.setCurrentLang(GERMAN);
                Intent intent = new Intent(MainLanguageScreen.this, Quiz.class);
                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }
}
