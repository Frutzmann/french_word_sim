import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<Character, Integer> m;
        HashMap<Character, Integer> m1;
        HashMap<String, Integer> m2;

        FrenchWords r = new FrenchWords();
        m = r.getMapWord();
        m1 = r.getMapLetter();
        m2 = r.getSuitLetter();

        System.out.println(m);
        System.out.println(m1);
        System.out.println(m2);



    }
}
