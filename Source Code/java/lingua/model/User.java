package lingua.model;

import android.os.Parcel;
import android.os.Parcelable;

import lingua.model.languages.French;
import lingua.model.languages.German;
import lingua.model.languages.Spanish;

/**
 * Created by David on 9/18/2017.
 *
 * USER CLASS
 * CONTAINS LANGUAGE COMPONENT THAT CONTAINS LANGUAGE CLASSES
 */

public class User implements Parcelable {

    private String username;

    private String email;
    private String password;
    private int userID;


    public Spanish getSpanish() {
        return spanish;
    }

    public void setSpanish(Spanish spanish) {
        this.spanish = spanish;
    }

    public German getGerman() {
        return german;
    }

    public void setGerman(German german) {
        this.german = german;
    }

    public French getFrench() {
        return french;
    }

    public void setFrench(French french) {
        this.french = french;
    }

    private Spanish spanish;
    private German german;
    private French french;
    private String currentLang;
    public String getCurrentLang() {
        return currentLang;
    }

    public void setCurrentLang(String currentLang) {
        this.currentLang = currentLang;
    }

    public boolean notified;


    public int getSpanishLvl() {
        return spanishLvl;
    }

    public void setSpanishLvl(int spanishLvl) {
        this.spanishLvl = spanishLvl;
    }

    public int getFrenchLvl() {
        return frenchLvl;
    }

    public void setFrenchLvl(int frenchLvl) {
        this.frenchLvl = frenchLvl;
    }

    public int getGermanLvl() {
        return germanLvl;
    }

    public void setGermanLvl(int germanLvl) {
        this.germanLvl = germanLvl;
    }

    private int spanishLvl;
    private int frenchLvl;
    private int germanLvl;


    public User(Spanish spanish,  French french, German german, String currentLang){

        this.spanish = spanish;
        this.french = french;
        this.german = german;

        spanishLvl = spanish.getSpanishLvl();
        frenchLvl = french.getFrenchLvl();
        germanLvl = german.getGermanLvl();

        this.currentLang = currentLang;

        this.notified = false;




    }

    // getting ID
    public int getID(){
        return this.userID;
    }

    // setting id
    public void setID(int id){
        this.userID = id;
    }

    // getting username
    public String getUsername(){

        return username;

    }


    //email get/set


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    //setting username
    public void setUsername(String username){

        this.username = username;

    }


    //TO DO: ENCRYPT?
    // getting password
    public String getPassword(){

        return password;

    }

    //setting password
    public void setPassword(String password){

        this.password = password;

    }







    protected User(Parcel in) {
        email = in.readString();
        username = in.readString();
        password = in.readString();

        spanishLvl = in.readInt();
        frenchLvl = in.readInt();
        germanLvl = in.readInt();
        currentLang = in.readString();

    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeInt(spanishLvl);
        dest.writeInt(frenchLvl);
        dest.writeInt(germanLvl);
        dest.writeString(currentLang);
    }





    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
