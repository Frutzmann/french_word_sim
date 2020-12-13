import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class FrenchWords implements ReadDictionnary {

    private static String filename = "./file/mots_francais.txt";

    private HashMap<Character, Frequencies> mapWord;
    private HashMap<Character, Frequencies> mapLetter;
    private HashMap<String, Frequencies> suitLetter;
    private int letterNumber;
    private int firstLetterNumber;
    private int suitLetterNumber;

    private Frequencies f;

    public FrenchWords() {
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

    @Override
    public void EveryLetterFilter(String s) {

        for (int i = 0; i < s.length(); i++) {
            f = checkMap(mapWord, s.charAt(i));
            mapWord.put(s.charAt(i), f);
            letterNumber++;
        }
    }

    @Override
    public void FirstLetterFilter(String s) {
        f = checkMap(mapLetter, s.charAt(0));
        mapLetter.put(s.charAt(0), f);
        firstLetterNumber++;

    }



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

    public int getLetterNumber() {
        return letterNumber;
    }
}
