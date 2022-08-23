import java.util.Scanner;
import java.util.*;

public class Sum {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        // Scans the next token of the input as an int.
        int n1 = reader.nextInt();
        // Scans the next token of the input as an int.
        int n2 = reader.nextInt();
        reader.close();
        List<Integer> numbers = null;
        numbers.add(1);
        System.out.println(numbers.size());
        System.out.println(n1 + n2);
    }
}