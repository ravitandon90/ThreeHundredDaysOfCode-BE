package decode_ways.Java;

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
        String filePath = "proddata/data/decode_ways/testcases.txt";
        List<String> al = method(filePath);
        al.remove(0);
        String input1 = null;
        int output;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                input1 = al.get(i);
            } else {
                output = Integer.parseInt(al.get(i));
                int user_output = numdecode(input1);
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

    public static int numdecode(String s)
    {
        decode_ways.Java.Solution sol = new decode_ways.Java.Solution();
        return sol.numDecodings(s);
    }
}
