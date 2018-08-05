package lingua.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.dwagner6506.views.R;

import lingua.data.DBHandler;
import lingua.model.User;

public class RemoveFriend extends AppCompatActivity {

    private User currentUser;
    private String[] friends;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_friend);


        currentUser = getIntent().getExtras().getParcelable("currUser");
        DBHandler db = new DBHandler(this);
        db.getWritableDatabase();
        Integer userID = db.getUserID(currentUser.getUsername());


        friends =  db.getFriendsUsernames(userID);

        if(friends.length > 0) {
            Spinner friendSpin = (Spinner) findViewById(R.id.spinnerFriend);


                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friends);

                friendSpin.setAdapter(itemsAdapter);


        }


        Button btnRemove = (Button)findViewById(R.id.btnRemove);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RemoveFriend.this, Friends.class);

                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);


                DBHandler db = new DBHandler(RemoveFriend.this);
                db.getWritableDatabase();
                Integer userID = db.getUserID(currentUser.getUsername());

                Spinner friendSpin = (Spinner) findViewById(R.id.spinnerFriend);


                db.deleteFriend(userID, db.getUserID(friendSpin.getSelectedItem().toString()));


                intent.putExtras(b);
                startActivity(intent);
            }
        });

        Button btnMenu = (Button)findViewById(R.id.backToMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RemoveFriend.this, Friends.class);

                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }
        });


    }

}
