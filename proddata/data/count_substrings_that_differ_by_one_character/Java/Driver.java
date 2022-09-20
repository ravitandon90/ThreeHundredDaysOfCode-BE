package count_substrings_that_differ_by_one_character.Java;

import java.io.*;
import java.util.*;

public class Driver
{
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
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");;
    }

    static boolean drivercode() {
        String filePath = "src/count_substrings_that_differ_by_one_character/testcases.txt";
        List<String> al = method(filePath);
        al.remove(0);
        String input1=null;
        String input2=null;
        int output;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                String s1 = al.get(i);
                s1.trim();
                String s[] = s1.split(" ");
                input1 = s[0];
                input2 = s[1];

            } else {
                output = Integer.parseInt(al.get(i));
                int user_output = count(input1,input2);
                b = b & output==user_output ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input1);
                    System.out.println(input2);
                    System.out.println("Your answer " + user_output);
                    System.out.println("Expected answer " + output);
                    return b;
                }
            }
        }
        return b;
    }
    public static int count(String s, String t)
    {
        Solution sol = new Solution();
        return sol.countSubstrings(s, t);
    }
}