package lingua.model.languages;

import android.util.Log;

/**
 *Created by David on 9/21/2017.
 * LANGUAGE CLASS FOR FRENCH
 */

public class French {

    private int frenchLvl;
    public int getFrenchLvl(){return this.frenchLvl;}
    public void setFrenchLvl(int newLvl){this.frenchLvl = newLvl;}

    public static int MAX_LVL = 15;

    private   String lvl1FrenchWords[] = {"bonjour","au revoir", "soleil", "journée", "homme"};
    private  String lvl1EnglishWords[] = {"hello","goodbye", "sun", "day", "man"};

    private  String lvl2FrenchWords[] = {"temps","an", "météo", "monde", "chose"};
    private  String lvl2EnglishWords[] = {"time","year", "weather", "world", "thing"};

    private  String lvl3FrenchWords[] = {"ordinateur","bureau", "maison", "église", "aliments"};
    private  String lvl3EnglishWords[] = {"computer","desk", "home", "church", "food"};

    private  String lvl4FrenchWords[] = {"emploi","père", "mère", "sommeil", "décès"};
    private  String lvl4EnglishWords[] = {"job","father", "mother", "sleep", "death"};

    private  String lvl5FrenchWords[] = {"programmation","croyance", "roue", "vie", "nouveau"};
    private  String lvl5EnglishWords[] = {"programming","belief", "wheel", "life", "new"};

    private  String lvl6FrenchWords[] = {"espada", "flecha", "ciudad", "aldeanos", "alcalde"};
    private  String lvl6EnglishWords[] = {"sword","arrow", "town", "villagers", "mayor"};

    private  String lvl7FrenchWords[] = {"pays", "tueur", "pensée", "direction", "voiture"};
    private  String lvl7EnglishWords[] = {"country", "killer", "thought", "direction", "car"};

    private  String lvl8FrenchWords[] = {"rôle", "projet", "groupe", "beauté", "puissant"};
    private  String lvl8EnglishWords[] = {"role", "project", "group","beauty", "powerful"};

    private  String lvl9FrenchWords[] = {"absolument", "conflit", "artiste", "métrage", "cadeau"};
    private  String lvl9EnglishWords[] = {"absolutely", "conflict", "artist", "footage", "gift"};

    private  String lvl10FrenchWords[] = {"chanson", "tôt", "argent", "dehors", "dedans"};
    private  String lvl10EnglishWords[] = {"song", "early","money", "outside", "inside"};

    private  String lvl11FrenchWords[] = { "coeur", "citation", "quelque chose", "rien", "tout"};
    private  String lvl11EnglishWords[] = {"heart", "quote", "something", "nothing", "everything"};

    private  String lvl12FrenchWords[] = { "aile", "course", "musique", "fer", "ligue"};
    private  String lvl12EnglishWords[] = {"wing", "race", "music", "iron", "league"};

    private  String lvl13FrenchWords[] = {"indice", "renonciation", "aujourd'hui", "sain", "immunitaire"};
    private  String lvl13EnglishWords[] = {"hint", "waiver", "today", "healthy", "immune"};

    private  String lvl14FrenchWords[] = {"joueur", "station", "championnat", "fier", "conseiller"};
    private  String lvl14EnglishWords[] = {"gamer", "station", "championship", "proud", "advisor"};

    private  String lvl15FrenchWords[] = {"pointe", "fou", "prison", "droits", "crime"};
    private  String lvl15EnglishWords[] = {"spike","crazy", "prison", "rights", "crime"};





    public static String wrongFrenchWords[] = {"plage", "taxi", "paroi", "maison", "empiler", "table", "orange", "four", "vache",
            "oiseau", "plante", "lac", "paradis", "bras", "pied", "poitrine", "rivière", "neige", "brouillard", "bébé", "technologie", "\n" +
            "grand-mère", "oeufs", "rien", "armure", "faveur", "peut", "épinards", "programme", "encouragement", "respiration", "dents", "ronde", "départ", "bus",
            "sanitaire", "employé", "travail", "français", "personne", "boîte", "rêves",
            "tendance", "marque", "pain", "mess", "avec", "chaussure", "loisirs", "rebondir", "poivre", "appeler", "devoir", "guerre", "incroyable"};


    private String lvl2Words[];

    private String lvl3Words[];

    private String lvl4Words[];

    private String lvl5Words[];

    public String[] getFrenchWords(int lvl)
    {
        String[] frenchWords ={};

        switch(lvl) {
            case 1 :
                frenchWords = lvl1FrenchWords;
                break;

            case 2 :
                frenchWords = lvl2FrenchWords;
                break;

            case 3:
                frenchWords = lvl3FrenchWords;
                break;

            case 4:
                frenchWords = lvl4FrenchWords;
                break;

            case 5:
                frenchWords = lvl5FrenchWords;
                break;
            case 6 :
                frenchWords = lvl6FrenchWords;
                break;

            case 7 :
                frenchWords = lvl7FrenchWords;
                break;

            case 8:
                frenchWords = lvl8FrenchWords;
                break;

            case 9:
                frenchWords = lvl9FrenchWords;
                break;

            case 10:
                frenchWords = lvl10FrenchWords;
                break;
            case 11 :
                frenchWords = lvl11FrenchWords;
                break;

            case 12 :
                frenchWords = lvl12FrenchWords;
                break;

            case 13:
                frenchWords = lvl13FrenchWords;
                break;

            case 14:
                frenchWords = lvl14FrenchWords;
                break;

            case 15:
                frenchWords = lvl15FrenchWords;
                break;


        }


        return frenchWords;
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

    public French()
    {
        frenchLvl = 1;
    }
}
