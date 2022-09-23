package fizz_buzz.Java;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Driver {
    private static List<String> method(String filePath) {
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                str.trim();
                str = str.replace("[","");
                str = str.replace("\"","");
                str = str.replace(","," ");
                str = str.replace("]","");

                al.add(str);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return al;
    }

    public static void main(String[] args) {
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");
    }

    static boolean drivercode() {
        String filePath = "proddata/data/fizz_buzz/testcases.txt";
        List<String> al = method(filePath);

        List<String> out;
        int input = 0;
        int testcases = Integer.parseInt(al.remove(0));
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                input = Integer.parseInt(al.get(i));
            } else {
                out = Arrays.stream(al.get(i).split(" ")).collect(Collectors.toList());
                List<String> user_out = user(input);
                b = b & out.equals(user_out) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input);
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static List<String> user(int n) {
        fizz_buzz.Java.Solution sol = new fizz_buzz.Java.Solution();
        return sol.fizzBuzz(n);
    }
}
