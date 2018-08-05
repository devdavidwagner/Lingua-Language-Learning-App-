package lingua.services;

import android.app.*;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.content.*;
import android.net.Uri;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.WindowManager;


import com.google.firebase.messaging.RemoteMessage;

import lingua.controllers.AddFriend;
import lingua.controllers.Dialog_BG;
import lingua.controllers.Friends;
import lingua.controllers.IntroScreen;
import lingua.controllers.MainLanguageScreen;
import lingua.data.DBHandler;

/**
 * Created by David on 1/2/2018.
 */

import com.example.dwagner6506.views.R;

public class BackgroundService extends Service   {

    private boolean isRunning;
    private Context context;
    private Thread backgroundThread;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        this.context = getApplicationContext();
        this.isRunning = false;
        this.backgroundThread = new Thread(myTask);



    }

    private void createHandler() {
        Thread thread = new Thread() {
            public void run() {
                Looper.prepare();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do Work
                        Log.v("background-service","BACKGROUND SERVICE IS RUNNING");


                        Intent dialogIntent = new Intent(context, Dialog_BG.class);
                        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(dialogIntent);


                    }
                }, 2000);

                Looper.loop();
            }
        };

        thread.start();
    }

    private Runnable myTask = new Runnable() {
        public void run() {
            // Do something here

            createHandler();

            stopSelf();
        }
    };


    @Override
    public void onDestroy() {
        this.isRunning = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!this.isRunning) {
            this.isRunning = true;
            this.backgroundThread.start();
        }
        return START_STICKY;
    }
}
