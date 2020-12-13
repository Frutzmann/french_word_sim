import java.util.HashMap;
import java.util.Map;
/////////////CREATION DU MOT////////////
//Inspiré de : https://stackoverflow.com/questions/6737283/weighted-randomness-in-java

public class ComputeWord {
    StringBuilder word;
    private int stringLength;
    double random;

    Dictionnary f = new Dictionnary();

    public ComputeWord(int stringLength) {
        this.stringLength = stringLength;
        word = new StringBuilder();
        random = 0;
        generate();

    }


    public <E> void generate() {
        //Initalisation du mot avec la première lettre
        computeLetter(f.getMapLetter(), Math.random());
        while (stringLength != 0) {
            random = Math.random();
            //Si la longueur de la chaine le permet, sélection d'une suite de lettres, sinon sélection d'une seule lettre
            if (stringLength <= 2)
                computeLetter(f.getMapWord(), random);
            else {
                computeSuit(f.getSuitLetter(), word.charAt(word.length()-1));
            }
        }
        System.out.println(word);
    }

    public <E> void computeLetter(HashMap<E, Frequencies> m, double random) {
        //Parcours du HashMap
        for (Map.Entry<E, Frequencies> entry : m.entrySet()) {
            E k = entry.getKey();
            Frequencies v = entry.getValue();
            random -= v.getRelFrequency();
            //Si le random se rapproche de la valeur de la fréquence relative
            if (random <= v.getRelFrequency()) {
                stringLength -= k.toString().length();
                System.out.println(k.toString());
                //Ajout de la lettre au mot
                word.append(k.toString());
                return;
            }
        }
        checkLastLetters(word);
        checkConsecutiveLetters(word);
    }

    private <E> void computeSuit(HashMap<E, Frequencies> m, char c) {
        HashMap<String, Frequencies> tempMap = new HashMap<>();
        double totalWeight = 0.0;
        for (Map.Entry<E, Frequencies> entry : m.entrySet()) {
            String []split = entry.getKey().toString().split(":");
            Frequencies v = entry.getValue();
            //On recherche les suites de lettre dont la première lettre commence par la dernière lettre du mot
            if(split[0].equals(String.valueOf(c)))
            {
                //Si les deux premières lettres sont identiques
                if(split[1].charAt(0) != c || word.length() > 1)
                {
                    totalWeight += v.getRelFrequency();
                    tempMap.put(split[1], v);
                }

            }
        }
        random = Math.random()*totalWeight;
        computeLetter(tempMap, random);
    }
    //Verification des deux dernières lettres
    private void checkLastLetters(StringBuilder word) {
        if(word.length() >= 2)
        {
            //Si les deux dernières lettres sont identiques
            if(word.charAt(word.length()-1) == word.charAt(word.length()-2))
            {
                word.deleteCharAt(word.length()-1);
                computeLetter(f.getMapWord(), Math.random());
            }
        }
    }
    //Verification du nombre de lettres identiques consécutives
    private void checkConsecutiveLetters(StringBuilder word) {
        String s;
        for(int i = 0; i < word.length()-3; i++)
        {
            if((word.charAt(i) == word.charAt(i+1)) && (word.charAt(i+1) == word.charAt(i+2)))
            {
                s = word.substring(i+3, word.length());
                word.delete(i+2, word.length());
                computeLetter(f.getMapWord(), Math.random());
                word.append(s);
            }
        }
    }
}
