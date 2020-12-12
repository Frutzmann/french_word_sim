import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class FrenchWords implements WordGenerator{

    private static String filename = "./file/mots_francais.txt";

    private HashMap<Character, Integer> mapWord;
    private HashMap<Character, Integer> mapLetter;
    private HashMap<String, Integer> suitLetter;
    private int letterNumber;
    private int firstLetterNumber;
    private int suitLetterNumber;

    public HashMap<Character, Integer> getMapWord() {
        return mapWord;
    }

    public HashMap<Character, Integer> getMapLetter() {
        return mapLetter;
    }

    public HashMap<String, Integer> getSuitLetter() {
        return suitLetter;
    }

    public int getLetterNumber() {
        return letterNumber;
    }


    public FrenchWords() {
        mapWord = new HashMap<>();
        mapLetter = new HashMap<>();
        suitLetter = new HashMap<>();
        letterNumber = 0;
        read();
    }

    @Override
    public void read() {

        String input;
        try {
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);

            while ((input = br.readLine()) != null) {

                EveryLetterFilter(input.toLowerCase());
                FirstLetterFilter(input.toLowerCase());
                suitLetterFilter(input.toLowerCase());
                System.out.println(input);
            }

        } catch (Exception e) {
            System.out.println("Reading: " + e);
        }


    }
    @Override
    public void EveryLetterFilter(String s) {
        for (int i = 0; i < s.length(); i++) {
            mapWord.compute(s.charAt(i), (key, val) -> (val == null) ? 1: val+1);
            letterNumber++;
        }
    }

    @Override
    public void FirstLetterFilter(String s) {
        mapLetter.compute(s.charAt(0), (key, val) -> (val == null) ? 1: val +1);
        firstLetterNumber++;

    }


    @Override
    public void suitLetterFilter(String s) {
        String s1 = "";
        int secondLetter = 1;
        int thirdLetter = 2;
        for(int i = 0; i < s.length()-2; i++)
        {
            String c1 = Character.toString(s.charAt(i));
            String c2 = Character.toString(s.charAt(i+secondLetter));
            String c3 = Character.toString(s.charAt(i+thirdLetter));
            s1 = c1 + c2 + c3;
            suitLetter.compute(s1, (key, val) -> (val == null) ? 1 : val+1);
            suitLetterNumber++;
        }
    }
}
