import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<Character, Frequencies> m;
        HashMap<Character, Frequencies> m1;
        HashMap<String, Frequencies> m2;

        FrenchWords r = new FrenchWords();
        m = r.getMapWord();
        m1 = r.getMapLetter();
        m2 = r.getSuitLetter();

        System.out.println(m);
        System.out.println(m1);
        System.out.println(m2);




    }
}
