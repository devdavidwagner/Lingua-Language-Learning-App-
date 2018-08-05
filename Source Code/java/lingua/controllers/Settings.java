package lingua.controllers;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dwagner6506.views.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import lingua.data.DBHandler;
import lingua.model.User;

public class Settings extends AppCompatActivity {

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        currentUser = getIntent().getExtras().getParcelable("currUser");

        Button logOut = (Button) findViewById(R.id.btnLogOut) ;
        Button email  = (Button) findViewById(R.id.btnEmail);
        Button playstore = (Button) findViewById(R.id.btnPlayStore);
        Button resetLevels  = (Button) findViewById(R.id.btnResetLvl);
        Button download = (Button) findViewById(R.id.btnDownload);
        Button menu = (Button) findViewById(R.id.btnMenu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, MainLanguageScreen.class);

                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

        resetLevels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioGroup rG = (RadioGroup) findViewById(R.id.radioGroup);
                int index = rG.indexOfChild(findViewById(rG.getCheckedRadioButtonId()));

                DBHandler db = new DBHandler(Settings.this);
                db.getWritableDatabase();
                switch(index){
                    case 0:
                        db.resetLevel("SPANISH", db.getUserID(currentUser.getUsername()));
                        break;
                    case 1:
                        db.resetLevel("FRENCH", db.getUserID(currentUser.getUsername()));
                        break;
                    case 2:
                        db.resetLevel("GERMAN", db.getUserID(currentUser.getUsername()));
                        break;



                }
                Intent intent = new Intent(Settings.this, MainLanguageScreen.class);
                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);



            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DownloadFileFromURL().execute("http://edl.ecml.at/Portals/33/pdf/learn-languages-en.pdf");

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:"
                        + "dwagner6506@conestogac.ca"
                        + "?subject=" + "Feedback" + "&body=" + "Dear, Lingua");
                emailIntent.setData(data);
                startActivity(emailIntent);

            }
        });

        playstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                }
                catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }

            }
        });


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Settings.this, IntroScreen.class);

                startActivity(intent);
            }
        });



    }

    //ASYNCHRONOUS TASK WHEN FILE BEGINS DOWNLOAD
    private class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * */

        private ProgressBar progressBar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download");


            //Ask for permissions
            ActivityCompat.requestPermissions(Settings.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
            progressBar = (ProgressBar)findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

            Toast.makeText(Settings.this, "Downloading pdf...", Toast.LENGTH_LONG).show();
        }



        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;

            try {

                //set up root string
                String root = Environment.getExternalStorageDirectory().toString();

                URL url = new URL(f_url[0]);

                //connect to url
                URLConnection connection = url.openConnection();
                connection.connect();


                //get input data
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                //output to folder with name
                OutputStream output = new FileOutputStream(root+"/guideToLearning.pdf");
                byte data[] = new byte[1024];



                long total = 0;

                //writing data to folder
                while ((count = input.read(data)) != -1) {
                    total += count;
                    output.write(data, 0, count);

                }

                //close connections
                output.flush();

                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.toString());
            }

            return null;
        }



        /**
         * After completing background task
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            System.out.println("Downloaded");
            progressBar.setVisibility(View.INVISIBLE);

            AlertDialog.Builder builder1;
            builder1 = new AlertDialog.Builder(Settings.this);
            builder1.setMessage("File downloaded (pdf)! \n Would you like to view it?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes.",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {


                            File pdfFile = new File(Environment.getExternalStorageDirectory(),"guideToLearning.pdf");//File path
                            if (pdfFile.exists()) //Checking for the file is exist or not
                            {
                                //VIEWING THE PDF
                                Uri path = Uri.fromFile(pdfFile);
                                Intent objIntent = new Intent(Intent.ACTION_VIEW);
                                objIntent.setDataAndType(path, "application/pdf");
                                objIntent.setFlags(Intent. FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(objIntent);//Staring the pdf viewer
                            } else {

                                Toast.makeText(Settings.this, "The file does not exist! ", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

            builder1.setNegativeButton(
                    "No.",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Settings.this, Settings.class);

                            Bundle b = new Bundle();
                            b.putParcelable("currUser", currentUser);

                            intent.putExtras(b);
                            dialog.dismiss();
                            startActivity(intent);

                        }
                    });

            AlertDialog alert = builder1.create();
            alert.show();

        }

    }
}
