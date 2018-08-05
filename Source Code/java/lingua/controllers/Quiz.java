package lingua.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;


import com.example.dwagner6506.views.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lingua.data.DBHandler;
import lingua.model.User;
import lingua.model.languages.*;

public class Quiz extends AppCompatActivity {

    User currentUser;
    TextView timer;
    String buttonAns[];
    String correctAns;
    int englishCount = 0;
    int spanishCount = 0;
    int frenchCount = 0;
    int germanCount = 0;


    private final String SPANISH = "spanish";
    private final String GERMAN = "german";
    private final String FRENCH = "french";

    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    AlertDialog.Builder builder1;
    CountDownTimer CD;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        recreate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.content_quiz);
        }else
        {
            setContentView(R.layout.content_quiz_land);
        }
        currentUser = getIntent().getExtras().getParcelable("currUser");
        builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("TIMES UP!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Change Language",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        Intent intent = new Intent(Quiz.this, MainLanguageScreen.class);

                        Bundle b = new Bundle();
                        b.putParcelable("currUser", currentUser);

                        intent.putExtras(b);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

        builder1.setNeutralButton(
                "Retry level",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        timeUp = false;
                        resetBoard(currentUser.getCurrentLang());

                        dialog.dismiss();
                    }
                });


        startTimer();
        buttonA = (Button) findViewById(R.id.btnA);
        buttonB = (Button) findViewById(R.id.btnB);
        buttonC = (Button) findViewById(R.id.btnC);
        buttonD = (Button) findViewById(R.id.btnD);


        TextView userInfo = (TextView) findViewById(R.id.user);

        ImageView flag = (ImageView) findViewById(R.id.flag);

        timer = (TextView) findViewById(R.id.timer);






        generateQA(currentUser.getCurrentLang());



        if(currentUser.getCurrentLang().equals(SPANISH))
            flag.setImageResource(R.drawable.spanish);
            userInfo.setText(currentUser.getCurrentLang() + " LVL: " + currentUser.getSpanishLvl());
        if(currentUser.getCurrentLang().equals(FRENCH))
            flag.setImageResource(R.drawable.french);
            userInfo.setText(currentUser.getCurrentLang() + " LVL: " + currentUser.getFrenchLvl());
        if(currentUser.getCurrentLang().equals(GERMAN))
            flag.setImageResource(R.drawable.german);
            userInfo.setText(currentUser.getCurrentLang() + " LVL: " + currentUser.getGermanLvl());


        ImageButton check = (ImageButton) findViewById(R.id.check);
        ImageButton retry = (ImageButton) findViewById(R.id.retry);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(currentUser.getCurrentLang());
                disableAnswerButtons(false);

            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard(currentUser.getCurrentLang());
                disableAnswerButtons(false);
            }
        });


        Button btn = (Button)findViewById(R.id.backToMenu);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {backToMenu();
            }
        });




    }

    private void backToMenu(){

        spanishCount = 0;
        frenchCount = 0;
        germanCount = 0;
        englishCount = 0;

        CD.cancel();
        Intent intent = new Intent(Quiz.this, MainLanguageScreen.class);

        Bundle b = new Bundle();
        b.putParcelable("currUser", currentUser);

        intent.putExtras(b);
        startActivity(intent);

    }

    private void startTimer(){


         CD = new CountDownTimer(15000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish() {
                timeUp = true;
                timer.setText("XX");

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

        }.start();
    }


    class  ButtonGenerator {
        List<Integer> number = Arrays.asList(0, 1, 2, 3);
        int n = 0;

        ButtonGenerator() {
            Collections.shuffle(number);
        }

        int next() {
            return number.get(n++);
        }
    }

    class  WrongWordGenerator {
        List<Integer> number = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,
        22,23,24,25,26,27,28,29,30,31,31,31,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,55);
        int n = 0;

        WrongWordGenerator() {
            Collections.shuffle(number);
        }
        int next() {
            return number.get(n++);
        }

    }

    Boolean timeUp = false;
    Spanish spanish = new Spanish();
    French french = new French();
    German german = new German();
    private void generateQA(String lang){

        DBHandler db = new DBHandler(this);
        db.getWritableDatabase();


        Button buttonA = (Button) findViewById(R.id.btnA);
        Button buttonB = (Button) findViewById(R.id.btnB);
        Button buttonC = (Button) findViewById(R.id.btnC);
        Button buttonD = (Button) findViewById(R.id.btnD);

        Button answerBtns[] = {buttonA, buttonB, buttonC,buttonD};

        Integer userLvl = 0;

        if(lang.equals(SPANISH))
         userLvl = currentUser.getSpanishLvl();
        if(lang.equals(FRENCH))
            userLvl = currentUser.getFrenchLvl();
        if(lang.equals(GERMAN))
            userLvl = currentUser.getGermanLvl();

        if(lang.equals(SPANISH) && !timeUp) {

            if(englishCount < spanish.getEnglishWords(userLvl).length) {  

                correctAns = spanish.getSpanishWords(userLvl)[spanishCount];

                String phrase1 = "What is the spanish word for: ";

                String fullQ = phrase1 + spanish.getEnglishWords(userLvl)[englishCount].toUpperCase() + "?";

                Spannable spannable = new SpannableString(fullQ);

                spannable.setSpan(new ForegroundColorSpan(Color.RED), phrase1.length(), fullQ.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                TextView question = (TextView) findViewById(R.id.question);
                question.setText(spannable, TextView.BufferType.SPANNABLE);

                boolean correctWordPlaced = false;

                ButtonGenerator gen = new ButtonGenerator();
                WrongWordGenerator genWord = new WrongWordGenerator();

                for (int x = 0; x < answerBtns.length; x++) {
                    if (!correctWordPlaced) {
                        answerBtns[gen.next()].setText(spanish.getSpanishWords(userLvl)[spanishCount]);
                        correctWordPlaced = true;

                    } else {
                        answerBtns[gen.next()].setText(spanish.wrongSpanishWords[genWord.next()]);
                    }
                }

                for (int i = 0; i < answerBtns.length; i++) {
                    answerBtns[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Button b = (Button) v;

                            checkSelection(b.getText().toString());


                        }
                    });
                }



            }
            else
            {
                CD.cancel();
                int spanishLvl = currentUser.getSpanishLvl();
                spanishLvl++;
                currentUser.setSpanishLvl(spanishLvl);
                Integer userID = 0;

                userID = db.getUserID(currentUser.getUsername());
                db.updateLevels(currentUser, userID);
                englishCount = 0;
                spanishCount = 0;

                Intent intent = new Intent(Quiz.this, MainLanguageScreen.class);

                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }

        }
        else if(lang.equals(FRENCH) && !timeUp) {

            if(englishCount < french.getEnglishWords(userLvl).length) {

                correctAns = french.getFrenchWords(userLvl)[frenchCount];

                String phrase1 = "What is the french word for: ";

                String fullQ = phrase1 + french.getEnglishWords(userLvl)[englishCount].toUpperCase() + "?";

                Spannable spannable = new SpannableString(fullQ);

                spannable.setSpan(new ForegroundColorSpan(Color.RED), phrase1.length(), fullQ.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                TextView question = (TextView) findViewById(R.id.question);
                question.setText(spannable, TextView.BufferType.SPANNABLE);

                boolean correctWordPlaced = false;

                ButtonGenerator gen = new ButtonGenerator();
                WrongWordGenerator genWord = new WrongWordGenerator();

                for (int x = 0; x < answerBtns.length; x++) {
                    if (!correctWordPlaced) {
                        answerBtns[gen.next()].setText(french.getFrenchWords(userLvl)[frenchCount]);
                        correctWordPlaced = true;

                    } else {
                        answerBtns[gen.next()].setText(french.wrongFrenchWords[genWord.next()]);
                    }
                }

                for (int i = 0; i < answerBtns.length; i++) {
                    answerBtns[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Button b = (Button) v;

                            checkSelection(b.getText().toString());


                        }
                    });
                }



            }
            else
            {
                CD.cancel();
                int frenchLvl = currentUser.getFrenchLvl();
                frenchLvl++;
                currentUser.setFrenchLvl(frenchLvl);

                englishCount = 0;
                frenchCount = 0;
                Integer userID = 0;

                userID = db.getUserID(currentUser.getUsername());
                db.updateLevels(currentUser, userID);
                Intent intent = new Intent(Quiz.this, MainLanguageScreen.class);

                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }

        }
        else if(lang.equals(GERMAN) && !timeUp) {

            if(englishCount < german.getEnglishWords(userLvl).length) {

                correctAns = german.getGermanWords(userLvl)[germanCount];

                String phrase1 = "What is the german word for: ";

                String fullQ = phrase1 + german.getEnglishWords(userLvl)[englishCount].toUpperCase() + "?";

                Spannable spannable = new SpannableString(fullQ);

                spannable.setSpan(new ForegroundColorSpan(Color.RED), phrase1.length(), fullQ.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                TextView question = (TextView) findViewById(R.id.question);
                question.setText(spannable, TextView.BufferType.SPANNABLE);

                boolean correctWordPlaced = false;

                ButtonGenerator gen = new ButtonGenerator();
                WrongWordGenerator genWord = new WrongWordGenerator();

                for (int x = 0; x < answerBtns.length; x++) {
                    if (!correctWordPlaced) {
                        answerBtns[gen.next()].setText(german.getGermanWords(userLvl)[germanCount]);
                        correctWordPlaced = true;

                    } else {
                        answerBtns[gen.next()].setText(german.wrongGermanWords[genWord.next()]);
                    }
                }

                for (int i = 0; i < answerBtns.length; i++) {
                    answerBtns[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Button b = (Button) v;

                            checkSelection(b.getText().toString());


                        }
                    });
                }



            }
            else
            {
                CD.cancel();
                int germanLvl = currentUser.getGermanLvl();
                germanLvl++;
                currentUser.setGermanLvl(germanLvl);

                englishCount = 0;
                germanCount = 0;


                Integer userID = 0;

                userID = db.getUserID(currentUser.getUsername());
                db.updateLevels(currentUser, userID);

                Intent intent = new Intent(Quiz.this, MainLanguageScreen.class);

                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }

        }


    }

    private void disableAnswerButtons(Boolean isDisabled)
    {
        Button buttonA = (Button) findViewById(R.id.btnA);
        Button buttonB = (Button) findViewById(R.id.btnB);
        Button buttonC = (Button) findViewById(R.id.btnC);
        Button buttonD = (Button) findViewById(R.id.btnD);

        Button answerBtns[] = {buttonA, buttonB, buttonC,buttonD};

        if(isDisabled) {
            for (int i = 0; i < answerBtns.length; i++) {

                answerBtns[i].setVisibility(View.INVISIBLE);
            }
        }
        else
        {
            for (int i = 0; i < answerBtns.length; i++) {

                answerBtns[i].setVisibility(View.VISIBLE);
            }
        }

    }

    private void checkSelection(String buttonAnswer){
        ImageButton check = (ImageButton) findViewById(R.id.check);
        ImageButton retry = (ImageButton) findViewById(R.id.retry);
            if(buttonAnswer.equals(correctAns))
            {
                check.setVisibility(View.VISIBLE);


            }
            else
            {
                retry.setVisibility(View.VISIBLE);
                TextView question = (TextView) findViewById(R.id.question);
                question.setText("WRONG ANSWER. TRY AGAIN.");
            }
        disableAnswerButtons(true);

    }

    private void resetBoard(String lang){
        ImageButton retry = (ImageButton) findViewById(R.id.retry);

        if(lang.equals(SPANISH))
        spanishCount = 0;

        if(lang.equals(GERMAN))
        germanCount = 0;

        if(lang.equals(FRENCH))
        frenchCount = 0;




        englishCount = 0;
        generateQA(lang);

        CD.cancel();
        startTimer();
        if(retry.getVisibility() == View.VISIBLE)
        retry.setVisibility(View.INVISIBLE);

    }

    private void nextQuestion(String lang){
        ImageButton check = (ImageButton) findViewById(R.id.check);

            if(lang.equals(SPANISH))
                spanishCount++;
            if(lang.equals(FRENCH))
                frenchCount++;
            if(lang.equals(GERMAN))
                germanCount++;


            englishCount++;
        generateQA(lang);


        check.setVisibility(View.INVISIBLE);

    }
}

