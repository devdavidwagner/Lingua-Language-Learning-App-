package lingua.model.languages;

import android.util.Log;

/**
 * Created by David on 9/21/2017.
 *
 *
 * LANGUAGE CLASS FOR GERMAN
 */


public class German {
    private int germanLvl;
    public int getGermanLvl(){return this.germanLvl;};
    public void setGermanLvl(int newLvl){this.germanLvl = newLvl;};

    public static int MAX_LVL = 15;
    private   String lvl1GermanWords[] = {"Hallo", "auf Wiedersehen", "Sonne", "Tag", "Mann"};
    private  String lvl1EnglishWords[] = {"hello","goodbye", "sun", "day", "man"};

    private  String lvl2GermanWords[] = {"Zeit", "Jahr", "Wetter", "Welt", "Ding"};
    private  String lvl2EnglishWords[] = {"time","year", "weather", "world", "thing"};

    private  String lvl3GermanWords[] = {"Computer", "Schreibtisch", "Zuhause", "Kirche", "Essen"};
    private  String lvl3EnglishWords[] = {"computer","desk", "home", "church", "food"};

    private  String lvl4GermanWords[] = {"Beruf", "Vater", "Mutter", "Schlaf", "Tod"};
    private  String lvl4EnglishWords[] = {"job","father", "mother", "sleep", "death"};

    private  String lvl5GermanWords[] = {"Programmierung", "Glaube", "Rad", "Leben", "neu"};
    private  String lvl5EnglishWords[] = {"programming","belief", "wheel", "life", "new"};

    private  String lvl6GermanWords[] = {"Schwert", "Pfeil", "Stadt", "Dorfbewohner", "Bürgermeister"};
    private  String lvl6EnglishWords[] = {"sword","arrow", "town", "villagers", "mayor"};

    private  String lvl7GermanWords[] = {"Land", "Killer", "Gedanke", "Richtung", "Auto"};
    private  String lvl7EnglishWords[] = {"country", "killer", "thought", "direction", "car"};

    private  String lvl8GermanWords[] = {"Rolle", "Projekt", "Gruppe", "Schönheit", "kraftvoll"};
    private  String lvl8EnglishWords[] = {"role", "project", "group","beauty", "powerful"};

    private  String lvl9GermanWords[] = {"absolut", "konflikt", "künstler", "footage", "geschenk"};
    private  String lvl9EnglishWords[] = {"absolutely", "conflict", "artist", "footage", "gift"};

    private  String lvl10GermanWords[] = {"Lied", "früh", "Geld", "draußen", "innen"};
    private  String lvl10EnglishWords[] = {"song", "early","money", "outside", "inside"};

    private  String lvl11GermanWords[] = {"Herz", "Zitat", "etwas", "nichts", "alles"};
    private  String lvl11EnglishWords[] = {"heart", "quote", "something", "nothing", "everything"};

    private  String lvl12GermanWords[] = {"Flügel", "Rasse", "Musik", "Eisen", "Liga"};
    private  String lvl12EnglishWords[] = {"wing", "race", "music", "iron", "league"};

    private  String lvl13GermanWords[] = {"Hinweis", "Verzicht", "heute", "gesund", "immun"};
    private  String lvl13EnglishWords[] = {"hint", "waiver", "today", "healthy", "immune"};

    private  String lvl14GermanWords[] = {"gamer", "station", "meisterschaft", "stolz", "berater"};
    private  String lvl14EnglishWords[] = {"gamer", "station", "championship", "proud", "advisor"};

    private  String lvl15GermanWords[] = {"Spike", "verrückt", "Gefängnis", "Rechte", "Verbrechen"};
    private  String lvl15EnglishWords[] = {"spike","crazy", "prison", "rights", "crime"};




    public static String wrongGermanWords[] = {"strand", "taxi", "wand", "haus", "stapel", "tisch", "orange", "ofen", "kuh",
            "Vogel", "Pflanze", "See", "Himmel", "Arm", "Fuß", "Brust", "Fluss", "Schnee", "Nebel", "Baby", "Technologie", "Großmutter" ,
            "Eier", "nichts", "armour", "favor", "kann", "spinat", "programm", "atem", "atmung", "zähne", "rund", "start", "autobus" ,
            "Sanitär", "Mitarbeiter", "Arbeit", "Französisch", "Person", "Kasten", "Träume",
            "Trend", "Marke", "Brot", "Chaos", "mit", "Schuh", "Freizeit", "Prellen", "Pfeffer", "Ruf", "Pflicht", "Kriegsführung", "Unglaublich "};



    public String[] getGermanWords(int lvl)
    {
        String[] germanWords ={};

        switch(lvl) {
            case 1 :
                germanWords = lvl1GermanWords;
                break;

            case 2 :
                germanWords = lvl2GermanWords;
                break;

            case 3:
                germanWords = lvl3GermanWords;
                break;

            case 4:
                germanWords = lvl4GermanWords;
                break;

            case 5:
                germanWords = lvl5GermanWords;
                break;

            case 6 :
                germanWords = lvl6GermanWords;
                break;

            case 7 :
                germanWords = lvl7GermanWords;
                break;

            case 8:
                germanWords = lvl8GermanWords;
                break;

            case 9:
                germanWords = lvl9GermanWords;
                break;

            case 10:
                germanWords = lvl10GermanWords;
                break;

            case 11 :
                germanWords = lvl11GermanWords;
                break;

            case 12 :
                germanWords = lvl12GermanWords;
                break;

            case 13:
                germanWords = lvl13GermanWords;
                break;

            case 14:
                germanWords = lvl14GermanWords;
                break;

            case 15:
                germanWords = lvl15GermanWords;
                break;

        }

        return germanWords;
    }

    public String[] getEnglishWords(int lvl)
    {
        String[] englishWords ={};

        switch(lvl) {
            case 1 :
                englishWords = lvl1EnglishWords;
                break;

            case 2 :
                englishWords = lvl2EnglishWords;
                break;

            case 3:
                englishWords = lvl3EnglishWords;
                break;

            case 4:
                englishWords = lvl4EnglishWords;
                break;

            case 5:
                englishWords = lvl5EnglishWords;
                break;

            case 6 :
                englishWords = lvl6EnglishWords;
                break;

            case 7 :
                englishWords = lvl7EnglishWords;
                break;

            case 8:
                englishWords = lvl8EnglishWords;
                break;

            case 9:
                englishWords = lvl9EnglishWords;
                break;

            case 10:
                englishWords = lvl10EnglishWords;
                break;

            case 11 :
                englishWords = lvl11EnglishWords;
                break;

            case 12 :
                englishWords = lvl12EnglishWords;
                break;

            case 13:
                englishWords = lvl13EnglishWords;
                break;

            case 14:
                englishWords = lvl14EnglishWords;
                break;

            case 15:
                englishWords = lvl15EnglishWords;
                break;

        }


        return englishWords;
    }
    public German()
    {
        germanLvl = 1;
    }
}
