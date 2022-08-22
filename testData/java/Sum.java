import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        // Scans the next token of the input as an int.
        int n1 = reader.nextInt();
        // Scans the next token of the input as an int.
        int n2 = reader.nextInt();
        reader.close();
        System.out.println(n1 + n2);
    }
}