package lingua.model.languages;

import android.util.Log;

/**
 *Created by David on 9/21/2017.
 * LANGUAGE CLASS FOR SPANISH
 */

public class Spanish {

    private int spanishLvl;

    public static int MAX_LVL = 15;


    public int getSpanishLvl(){return this.spanishLvl;}
    public void setSpanishLvl(int newLvl){this.spanishLvl = newLvl;}

    private   String lvl1SpanishWords[] = {"hola","adiós", "sol", "dia", "hombre"};
    private  String lvl1EnglishWords[] = {"hello","goodbye", "sun", "day", "man"};

    private  String lvl2SpanishWords[] = {"vez","año", "tiempo", "mundo", "cosa"};
    private  String lvl2EnglishWords[] = {"time","year", "weather", "world", "thing"};

    private  String lvl3SpanishWords[] = {"computadora","escritorio", "casa", "iglesia", "comida"};
    private  String lvl3EnglishWords[] = {"computer","desk", "home", "church", "food"};

    private  String lvl4SpanishWords[] = {"trabajo","padre", "madre", "sueño", "muerte"};
    private  String lvl4EnglishWords[] = {"job","father", "mother", "sleep", "death"};

    private  String lvl5SpanishWords[] = {"programación","creencia", "rueda", "vida", "nuevo"};
    private  String lvl5EnglishWords[] = {"programming","belief", "wheel", "life", "new"};

    private  String lvl6SpanishWords[] = {"espada", "flecha", "ciudad", "aldeanos", "alcalde"};
    private  String lvl6EnglishWords[] = {"sword","arrow", "town", "villagers", "mayor"};

    private  String lvl7SpanishWords[] = {"país", "asesino", "pensamiento", "dirección", "automóvil"};
    private  String lvl7EnglishWords[] = {"country", "killer", "thought", "direction", "car"};

    private  String lvl8SpanishWords[] = {"rol", "proyecto", "grupo", "belleza", "poderoso"};
    private  String lvl8EnglishWords[] = {"role", "project", "group","beauty", "powerful"};

    private  String lvl9SpanishWords[] = {"absolutamente", "conflicto", "artista", "metraje", "regalo"};
    private  String lvl9EnglishWords[] = {"absolutely", "conflict", "artist", "footage", "gift"};

    private  String lvl10SpanishWords[] = {"canción", "temprano", "dinero", "fuera", "dentro"};
    private  String lvl10EnglishWords[] = {"song", "early","money", "outside", "inside"};

    private  String lvl11SpanishWords[] = {"corazón", "cita", "algo", "nada", "todo"};
    private  String lvl11EnglishWords[] = {"heart", "quote", "something", "nothing", "everything"};

    private  String lvl12SpanishWords[] = {"ala", "raza", "música", "hierro", "liga"};
    private  String lvl12EnglishWords[] = {"wing", "race", "music", "iron", "league"};

    private  String lvl13SpanishWords[] = {"pista", "renuncia", "hoy", "saludable", "inmune"};
    private  String lvl13EnglishWords[] = {"hint", "waiver", "today", "healthy", "immune"};

    private  String lvl14SpanishWords[] = {"jugador", "estación", "campeonato", "orgulloso", "consejero"};
    private  String lvl14EnglishWords[] = {"gamer", "station", "championship", "proud", "advisor"};

    private  String lvl15SpanishWords[] = {"pico", "loco", "prisión", "derechos", "crimen"};
    private  String lvl15EnglishWords[] = {"spike","crazy", "prison", "rights", "crime"};





    public static String wrongSpanishWords[] = {"playa", "taxi", "pared", "casa", "pila", "mesa", "naranja", "horno", "vaca",
            "ave", "planta", "lago", "cielo", "brazo", "pie", "pecho", "rio", "nieve", "niebla", "bebe", "tecnología", "abuela",
    "huevos", "nada", "armor", "favor", "pueda", "espinaca", "programa", "aliento", "respiración", "dientes",  "redondo", "empezar", "autobus",
    "sanitario", "empleado", "trabajo", "francés" , "persona", "caja", "sueños",
            "tendencia", "marca", "pan", "desorden", "con", "zapato", "ocio", "rebote", "pimienta", "llamar", "deber", "guerra", "increíble"};


    private String lvl2Words[];

    private String lvl3Words[];

    private String lvl4Words[];

    private String lvl5Words[];

    public String[] getSpanishWords(int lvl)
    {
        String[] spanishWords ={};

        switch(lvl) {
            case 1 :
                spanishWords = lvl1SpanishWords;
                break;

            case 2 :
                spanishWords = lvl2SpanishWords;
                break;

            case 3:
                spanishWords = lvl3SpanishWords;
                break;

            case 4:
                spanishWords = lvl4SpanishWords;
                break;

            case 5:
                spanishWords = lvl5SpanishWords;
                break;

            case 6 :
                spanishWords = lvl6SpanishWords;
                break;

            case 7 :
                spanishWords = lvl7SpanishWords;
                break;

            case 8:
                spanishWords = lvl8SpanishWords;
                break;

            case 9:
                spanishWords = lvl9SpanishWords;
                break;

            case 10:
                spanishWords = lvl10SpanishWords;
                break;
            case 11:
                spanishWords = lvl11SpanishWords;
                break;

            case 12:
                spanishWords = lvl12SpanishWords;
                break;

            case 13:
                spanishWords = lvl13SpanishWords;
                break;

            case 14:
                spanishWords = lvl14SpanishWords;
                break;

            case 15:
                spanishWords = lvl15SpanishWords;
                break;

        }

        Log.v("wordasd", spanishWords[1]);
        return spanishWords;
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



    public Spanish() {

        spanishLvl = 1;


    }

}
