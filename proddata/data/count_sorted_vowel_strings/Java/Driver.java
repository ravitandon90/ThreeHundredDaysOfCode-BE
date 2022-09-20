package count_sorted_vowel_strings.Java;

import java.io.*;
import java.util.*;

public class Driver {

    private static List<String> method(String filePath) {
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
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
        String filePath = "proddata/data/count_sorted_vowel_strings/testcases.txt";
        List<String> al = method(filePath);
        al.remove(0);
        int input1 = 0;
        int output;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                input1 = Integer.parseInt(al.get(i));
            } else {
                output = Integer.parseInt(al.get(i));
                int user_output = count(input1);
                b = b & output == user_output ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input1);
                    System.out.println("Your answer " + user_output);
                    System.out.println("Expected answer " + output);
                    return b;
                }
            }
        }
        return b;
    }

    public static int count(int n) {
        count_sorted_vowel_strings.Java.Solution sol = new count_sorted_vowel_strings.Java.Solution();
        return sol.countVowelStrings(n);
    }
}
