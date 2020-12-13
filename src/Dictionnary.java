import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
/////////////LECTURE DU FICHIER ET INITALISATION DES HASHMAPS/////////////////////
public class Dictionnary implements ReadDictionnary {

    private static String filename = "./file/mots_francais.txt";
    //Initialisation des maps et variables
    private HashMap<Character, Frequencies> mapWord; //Contient toutes les lettres de tout les mots
    private HashMap<Character, Frequencies> mapLetter; //Contient toutes les premieres lettres de tous les mots
    private HashMap<String, Frequencies> suitLetter; //Contient toutes les deux premières lettres suivant une lettre de tous les mots
    private int letterNumber;
    private int firstLetterNumber;
    private int suitLetterNumber;

    private Frequencies f;

    public Dictionnary() {
        mapWord = new HashMap<>();
        mapLetter = new HashMap<>();
        suitLetter = new HashMap<>();
        letterNumber = 0;
        firstLetterNumber = 0;
        suitLetterNumber = 0;
        read(filename);
    }



    @Override
    public void read(String filename) {

        String input;
        try {
            //Ouverture et lecture du fichier en UTF-8
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);

            while ((input = br.readLine()) != null) {

                EveryLetterFilter(input.toLowerCase());
                FirstLetterFilter(input.toLowerCase());
                suitLetterFilter(input.toLowerCase());

            }

            evalRelativeFrequencies(mapWord, letterNumber);
            evalRelativeFrequencies(mapLetter, firstLetterNumber);
            evalRelativeFrequencies(suitLetter, suitLetterNumber);


        } catch (Exception e) {
            System.out.println("Reading: " + e);
        }
    }
    //Parcours et insertion de chaque lettre du String dans le HashMap
    @Override
    public void EveryLetterFilter(String s) {

        for (int i = 0; i < s.length(); i++) {
            f = checkMap(mapWord, s.charAt(i));
            mapWord.put(s.charAt(i), f);
            letterNumber++;
        }
    }
    //Parcours et insertion de chaque première lettre dans le HashMap
    @Override
    public void FirstLetterFilter(String s) {
        f = checkMap(mapLetter, s.charAt(0));
        mapLetter.put(s.charAt(0), f);
        firstLetterNumber++;

    }


    //Parcours et insertion de chaque suite de lettre dans le HashMap
    @Override
    public void suitLetterFilter(String s) {

        String s1 = "";
        int secondLetter = 1;
        int thirdLetter = 2;
        for (int i = 0; i < s.length() - 2; i++) {
            String c1 = Character.toString(s.charAt(i));
            String c2 = Character.toString(s.charAt(i + secondLetter));
            String c3 = Character.toString(s.charAt(i + thirdLetter));
            s1 = c1 + ":" + c2 + c3;
            f = checkMap(suitLetter, s1);
            suitLetter.put(s1, f);
            suitLetterNumber++;
        }

    }
    //On verifie si la Map contient déjà la lettre
    //Si oui, on incrémente sa fréquence
    //Si non on l'ajoute à la Map et instancie une nouvelle Fréquence
    private <E> Frequencies checkMap(HashMap<E, Frequencies> m, E c) {
        if(m.containsKey(c))
        {
            f = m.get(c);
            int value = m.get(c).getFrequency() +1 ;
            f.setFrequency(value);
        }
        else
        {
            f = new Frequencies(1, 0);
        }
        return f;
    }
    //Calcul des fréquences relatives de chaque lettre d'une Map
    private <E> void evalRelativeFrequencies(HashMap<E,Frequencies> m, int number) {
        m.forEach((k, v) -> {
            f = m.get(k);
            f.setRelFrequency((float) v.getFrequency()/number);
            m.put(k, f);
        });

    }

    public HashMap<Character, Frequencies> getMapWord() {
        return mapWord;
    }

    public HashMap<Character, Frequencies> getMapLetter() {
        return mapLetter;
    }

    public HashMap<String, Frequencies> getSuitLetter() {
        return suitLetter;
    }

}
