import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Quelle est la longueur de votre mot ? ");
        int length = scan.nextInt();

        ComputeWord c = new ComputeWord(length);
    }
}
