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
import android.widget.TextView;


import com.example.dwagner6506.views.R;


import lingua.data.DBHandler;
import lingua.model.User;

public class Friends extends AppCompatActivity {


    private User currentUser;
    private String[] friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        currentUser = getIntent().getExtras().getParcelable("currUser");

        DBHandler db = new DBHandler(this);
        db.getWritableDatabase();
        Integer userID = db.getUserID(currentUser.getUsername());

        TextView usernameTitle = (TextView) findViewById(R.id.usernameFriend);

        usernameTitle.setText("Friends of " + currentUser.getUsername());

        friends =  db.getFriends(userID);

        if(friends.length > 0) {
            ListView commentList = (ListView) findViewById(R.id.friendsLst);

            ArrayAdapter<String> itemsAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friends);

            commentList.setAdapter(itemsAdapter);

        }

        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        Button btnRemove = (Button)findViewById(R.id.btnRemove);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Friends.this, AddFriend.class);
                Bundle b = new Bundle();
                currentUser = getIntent().getExtras().getParcelable("currUser");
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this, RemoveFriend.class);

                Bundle b = new Bundle();
                currentUser = getIntent().getExtras().getParcelable("currUser");
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

        Button back = (Button) findViewById(R.id.btnMenu);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Friends.this, MainLanguageScreen.class);

                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }
        });





    }

}
