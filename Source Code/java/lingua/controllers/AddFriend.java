package lingua.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.dwagner6506.views.R;

import lingua.data.DBHandler;
import lingua.model.User;
import lingua.services.FirebaseIDService;

import static java.sql.Types.NULL;

public class AddFriend extends AppCompatActivity {



    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        currentUser = getIntent().getExtras().getParcelable("currUser");

        DBHandler db = new DBHandler(this);
        db.getWritableDatabase();

        Button addFriend = (Button) findViewById(R.id.addFriend);
        Button back = (Button) findViewById(R.id.backToMenu);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddFriend.this, Friends.class);

                Bundle b = new Bundle();
                b.putParcelable("currUser", currentUser);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddFriend.this, Friends.class);

                Spinner friendsSpin = (Spinner) findViewById(R.id.friendsSpinner);

                String friendUserNameSelected;

                friendUserNameSelected = friendsSpin.getSelectedItem().toString();

                DBHandler db = new DBHandler(AddFriend.this);
                db.getWritableDatabase();

                Integer friendId = db.getUserID(friendUserNameSelected);
                Integer userId = db.getUserID(currentUser.getUsername());

            if(userId != friendId) {
                if(!db.checkFriendList(userId, friendId)) {
                    db.addFriend(userId, friendId);


                    Bundle b = new Bundle();
                    b.putParcelable("currUser", currentUser);

                    intent.putExtras(b);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog.Builder builder1;
                    builder1 = new AlertDialog.Builder(AddFriend.this);
                    builder1.setMessage("You already added that user!");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Understood.",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                    Intent intent = new Intent(AddFriend.this, Friends.class);

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
            else
            {
                AlertDialog.Builder builder1;
                builder1 = new AlertDialog.Builder(AddFriend.this);
                builder1.setMessage("You can't friend yourself!");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Understood.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                Intent intent = new Intent(AddFriend.this, Friends.class);

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
        });



        String[] allUsers =  db.getAllUsers();




            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_item, allUsers);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner sItems = (Spinner) findViewById(R.id.friendsSpinner);
            sItems.setAdapter(adapter);



    }

}
