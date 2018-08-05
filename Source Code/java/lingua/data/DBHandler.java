package lingua.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.ResultSet;

import lingua.model.User;

/**
 * Created by David on 9/18/2017.
 * Database Handler to handle all SQL lite functions
 */

public class DBHandler extends SQLiteOpenHelper {
    //TODO: FIX DATABASE TABLES
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "lingua_LocalDB";

    // Contacts table name
    private static final String TABLE_USERS = "users";
    private static final String TABLE_LEVELS = "user_levels";
    private static final String TABLE_FRIENDS = "user_friends";

    // Contacts Table Columns names
    private static final String USERID = "userID";
    private static final String EMAIL = "email";
    private static final String USERNAME = "name";
    private static final String PASSWORD = "password";
    private static final String NOTIFIED = "notified";

    private static final String SPANISHLVL = "spLvl";
    private static final String FRENCHLVL = "frLvl";
    private static final String GERMANLVL = "gerLvl";

    private static final String FRIENDUSERID = "friendUserID";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);



    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + USERID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " + EMAIL + " TEXT," + USERNAME + " TEXT,"
                    + PASSWORD + " TEXT," + NOTIFIED + " INTEGER);";

            db.execSQL(CREATE_USERS_TABLE);

            String CREATE_USERSTATS_TABLE = "CREATE TABLE " + TABLE_LEVELS + "(" + USERID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " + SPANISHLVL + " INTEGER,"
                    + FRENCHLVL + " INTEGER," + GERMANLVL + " INTEGER" + ");";

            db.execSQL(CREATE_USERSTATS_TABLE);

            String USER_FRIEND_TABLE = "CREATE TABLE " + TABLE_FRIENDS + "(" + USERID + " INTEGER, " + FRIENDUSERID + " INTEGER" + ");";

            db.execSQL(USER_FRIEND_TABLE);

        }
        catch(Exception e)
        {
            System.out.print(e.toString());
        }
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS +";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS +";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEVELS +";");
        // Create tables again
        onCreate(db);
    }

    //Background service notification

    public void notifiedUser(String username){


        SQLiteDatabase db = this.getWritableDatabase();



        ContentValues values = new ContentValues();

        values.put(NOTIFIED, 1);

        String [] args = {username};

        try {
            String whereCls = USERNAME + " = ?;";
            db.update(TABLE_USERS, values, whereCls, args);
            Log.d("dbHand", "USER NOTIFIED UPDATED");
        } catch (Exception e)
        {
            Log.d("errorSQL", e.toString());
        }



        db.close();

    }

    public boolean isUserNotified(String username){
        boolean isNotified = false;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor curs = db.rawQuery("SELECT * FROM " + TABLE_USERS +" WHERE " + USERNAME + " =?;", new String[]{username});



        try {
            if (curs.getCount() == 0) {
                Log.e("cursor", "No data");
            } else {

                for (int i = 0; i < curs.getCount(); i++) {
                    curs.moveToNext();
                    if(curs.getInt(curs.getColumnIndex(NOTIFIED)) == 1)
                    {
                      isNotified = true;
                    }

                }

            }
        }catch(Exception e){
            Log.e("cursor", "cursor error  " + e.toString());

        }
        curs.close();
        db.close();



        return isNotified;
    }



    public String[] getAllUsers(){
        SQLiteDatabase db = this.getWritableDatabase();


        Cursor curs = db.rawQuery("SELECT * FROM " + TABLE_USERS +";", null);

        String[] users = new String[curs.getCount()];

        try {
            if (curs.getCount() == 0) {
                Log.e("cursor", "No data");
            } else {

                for (int i = 0; i < curs.getCount(); i++) {
                    curs.moveToNext();
                    users[i] = curs.getString(curs.getColumnIndex(USERNAME));
                }

            }
        }catch(Exception e){
            Log.e("cursor", "cursor error  " + e.toString());

        }
        curs.close();
        db.close();

        return users;
    }



    //FRIEND CRUD

    public boolean checkFriendList(Integer userID, Integer friendID){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor curs = db.rawQuery("SELECT " + FRIENDUSERID +  " FROM " + TABLE_FRIENDS + " WHERE " + USERID + " = ?;", new String[] {userID.toString()});

        Boolean isFriendAlready = false;

        try {
            if (curs.getCount() == 0) {
                Log.e("cursor", "No data");
            } else {

                for (int i = 0; i < curs.getCount(); i++) {
                    curs.moveToNext();
                    Integer friendAddedId = curs.getInt(curs.getColumnIndex(FRIENDUSERID));

                    if(friendAddedId.equals(friendID))
                    {
                        isFriendAlready = true;
                        break;
                    }


                }




            }
        }catch(Exception e){
            Log.e("cursor", "cursor error  " + e.toString());

        }
        curs.close();
        db.close();

        return isFriendAlready;

    }

    //CREATE

    public void addFriend(Integer userID, Integer friendID){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USERID, userID);
        values.put(FRIENDUSERID, friendID);

        try{

            db.insert(TABLE_FRIENDS, null, values);



        }catch (Exception e)
        {
            Log.d("error", "ADD FRIEND ERROR");
        }


        db.close();

    }

    //DELETE

    public void deleteFriend(Integer userID, Integer friendID)
    {
        SQLiteDatabase db = this.getWritableDatabase();


        try{

            db.delete(TABLE_FRIENDS, USERID + "=? AND " + FRIENDUSERID + "=?;",new String[]{userID.toString(), friendID.toString()});


        }catch(Exception e){
            Log.d("error", "FRIEND REMOVAL ERROR");
        }


        db.close();

    }





    //READ
     public String[] getFriends(Integer userID){


         
        SQLiteDatabase db = this.getWritableDatabase();

         Cursor curs = db.rawQuery("SELECT " + FRIENDUSERID +  " FROM " + TABLE_FRIENDS + " WHERE " + USERID + " = ?;", new String[] {userID.toString()});



         String[] friendsList = new String[curs.getCount()];
         Integer[] friendIDs = new Integer[curs.getCount()];
         String[] usernames = new String[curs.getCount()];

        try {
            if (curs.getCount() == 0) {
                Log.e("cursor", "No data");
            } else {




                for (int i = 0; i < curs.getCount(); i++) {
                    curs.moveToNext();
                    friendIDs[i] = curs.getInt(curs.getColumnIndex(FRIENDUSERID));
                    Cursor userNameCurs = db.rawQuery("SELECT " + USERNAME + " FROM " + TABLE_USERS + " WHERE " + USERID + "= ?;", new String[] { friendIDs[i].toString()});


                    userNameCurs.moveToNext();
                    usernames[i] = userNameCurs.getString(userNameCurs.getColumnIndex(USERNAME));



                }




            }
        }catch(Exception e){
            Log.e("cursor", "cursor error  " + e.toString());

        }
        curs.close();
        db.close();

         for (int i = 0; i < friendsList.length; i++) {

             Integer[] levels;

             levels = getLangLevels(friendIDs[i]);

             friendsList[i] = usernames[i] + "\n -- Spanish Level: " + levels[0].toString() + "\n -- French Level: " + levels[1].toString() + "\n -- German Level: " +
                     levels[2].toString();


         }

        return friendsList;




    }

    public String[] getFriendsUsernames(Integer userID){



        SQLiteDatabase db = this.getWritableDatabase();

        Cursor curs = db.rawQuery("SELECT " + FRIENDUSERID +  " FROM " + TABLE_FRIENDS + " WHERE " + USERID + " = ?;", new String[] {userID.toString()});





        Integer[] friendIDs = new Integer[curs.getCount()];
        String[] usernames = new String[curs.getCount()];

        try {
            if (curs.getCount() == 0) {
                Log.e("cursor", "No data");
            } else {




                for (int i = 0; i < curs.getCount(); i++) {
                    curs.moveToNext();
                    friendIDs[i] = curs.getInt(curs.getColumnIndex(FRIENDUSERID));
                    Cursor userNameCurs = db.rawQuery("SELECT " + USERNAME + " FROM " + TABLE_USERS + " WHERE " + USERID + "= ?;", new String[] { friendIDs[i].toString()});


                        userNameCurs.moveToNext();
                        usernames[i] = userNameCurs.getString(userNameCurs.getColumnIndex(USERNAME));



                }




            }
        }catch(Exception e){
            Log.e("cursor", "cursor error  " + e.toString());

        }
        curs.close();
        db.close();

        return usernames;

    }




    public void updateLevels(User user, Integer userID){


        SQLiteDatabase db = this.getWritableDatabase();



        ContentValues values = new ContentValues();

        values.put(SPANISHLVL, user.getSpanishLvl());
        values.put(FRENCHLVL, user.getFrenchLvl());
        values.put(GERMANLVL, user.getGermanLvl());

        String [] args = {userID.toString()};

        try {
            String whereCls = USERID + " = ?;";
            db.update(TABLE_LEVELS, values, whereCls, args);
            Log.d("dbHand", "USER LEVELS UPDATED");
        } catch (Exception e)
        {
            Log.d("errorSQL", e.toString());
        }



        db.close();

    }

    //add a user to database
    public void addUser(User user)
    {

        SQLiteDatabase db = this.getWritableDatabase();



        ContentValues values = new ContentValues();

        values.put(USERNAME, user.getUsername());
        values.put(EMAIL, user.getEmail());
        values.put(PASSWORD, user.getPassword());
        values.put(NOTIFIED, 0);

        ContentValues valuesLevels = new ContentValues();

        valuesLevels.put(SPANISHLVL, user.getSpanishLvl());
        valuesLevels.put(FRENCHLVL, user.getFrenchLvl());
        valuesLevels.put(GERMANLVL, user.getGermanLvl());



        try {
            db.insert(TABLE_USERS, null, values);
            db.insert(TABLE_LEVELS, null, valuesLevels);
            Log.d("dbHand", "USER ADDED");
        } catch (Exception e)
        {
            Log.d("error", "USER CREATION ERROR");
        }



        db.close();



    }

    public Integer getUserID(String username)
    {
        Integer userID = 0;



        SQLiteDatabase db = this.getWritableDatabase();

        Cursor curs = db.rawQuery("SELECT " + USERID + " FROM " + TABLE_USERS +  " WHERE name = ?;", new String[] {username});

        try {
            if (!curs.moveToFirst() ) {
                Log.e("cursor", "No data");
            } else {
                curs.moveToFirst();
               userID = curs.getInt(curs.getColumnIndex(USERID));
            }
        }catch(Exception e){
            Log.e("cursor", "cursor error  " + e.toString());

        }
        curs.close();
        db.close();

        return userID;

    }

    //LEVELS
    public void resetLevel(String lang, Integer userID){



        SQLiteDatabase db = this.getWritableDatabase();



        ContentValues values = new ContentValues();

        if(lang.equals("SPANISH")) {
            values.put(SPANISHLVL, 1);
        }
        if(lang.equals("FRENCH")) {
            values.put(FRENCHLVL, 1);
        }
        if(lang.equals("GERMAN")) {
            values.put(GERMANLVL, 1);
        }

        String [] args = {userID.toString()};

        try {
            String whereCls = USERID + " = ?;";
            db.update(TABLE_LEVELS, values, whereCls, args);
            Log.d("dbHand", "USER LEVELS UPDATED");
        } catch (Exception e)
        {
            Log.d("errorSQL", e.toString());
        }



        db.close();



    }


    public Integer[] getLangLevels(Integer userID){

        Integer[] levels = {0,0,0};

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor curs = db.rawQuery("SELECT " + SPANISHLVL +", " + FRENCHLVL + ", " + GERMANLVL
                + " FROM " + TABLE_LEVELS + " WHERE " + USERID + " = ?;", new String[] {userID.toString()});


        try {
            if (curs.getCount() == 0) {
                Log.e("cursor", "No data");
            } else {
                curs.moveToNext();
                levels[0] = curs.getInt(curs.getColumnIndex(SPANISHLVL));
                levels[1] = curs.getInt(curs.getColumnIndex(FRENCHLVL));
                levels[2] = curs.getInt(curs.getColumnIndex(GERMANLVL));
            }
        }catch(Exception e){
            Log.e("cursor", "cursor error  " + e.toString());

        }
        curs.close();
        db.close();


        return levels;

    }

    public User setUserLangLevels(Integer userID, User user){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor curs = db.rawQuery("SELECT " + SPANISHLVL +", " + FRENCHLVL + ", " + GERMANLVL
                + " FROM "  + TABLE_LEVELS + " WHERE " + USERID + " = ?;", new String[] {userID.toString()});

        try {
            if (curs.getCount() == 0) {
                Log.e("cursor", "No data");
            } else {
                curs.moveToFirst();
                user.setSpanishLvl(curs.getInt(curs.getColumnIndex(SPANISHLVL)));

                Log.v("spanish", Integer.toString(curs.getInt(curs.getColumnIndex(SPANISHLVL))));

                user.setFrenchLvl(curs.getInt(curs.getColumnIndex(FRENCHLVL)));

                Log.v("french", Integer.toString(curs.getInt(curs.getColumnIndex(SPANISHLVL))));
                user.setGermanLvl(curs.getInt(curs.getColumnIndex(GERMANLVL)));

                Log.v("german", Integer.toString(curs.getInt(curs.getColumnIndex(SPANISHLVL))));
            }
        }catch(Exception e){
            Log.e("cursor", "cursor error  " + e.toString());

        }
        curs.close();
        db.close();

        return user;
    }


    //checks if user exists
    public Boolean checkUser(User user)
    {

        Boolean isUser = false;

        String username = user.getUsername();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor curs = db.rawQuery("SELECT * FROM 'users' WHERE name = ?;", new String[] {username});

        try {
            if (curs.getCount() == 0) {
                Log.e("cursor", "No data");
                isUser = false;
            } else {
                Log.e("cursor", "userExists");
                isUser = true;
            }
        }catch(Exception e){
            Log.e("cursor", "cursor error  " + e.toString());

        }
        curs.close();
        db.close();



        return isUser;


    }

    //checks if password is correct
    public Boolean checkPass(User user)
    {

        Boolean correctPass = false;

        SQLiteDatabase db = this.getWritableDatabase();
        String password = user.getPassword();
        Cursor curs = db.rawQuery("SELECT " + USERID + " FROM 'users' WHERE password = ?;", new String[] {password});

        try {
            if (curs.getCount() == 0) {
                Log.e("cursor", "wrongPass");
                correctPass = false;
            } else {
                Log.e("cursor", "correctPass");
                correctPass = true;
            }
        }catch(Exception e){
            Log.e("cursor", "cursor error  " + e.toString());

        }
        curs.close();
        db.close();


        return correctPass;


    }



}
