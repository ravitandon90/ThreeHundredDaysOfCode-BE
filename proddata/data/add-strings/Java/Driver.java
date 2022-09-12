package add_string.Java;

import java.io.*;
import java.util.*;

public class Driver
{
    private static List<String> method(String filePath) {
        StringBuilder builder = new StringBuilder();
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
        String filePath = "src/add_string/test cases";
        List<String> al = method(filePath);
        String input1=null;
        String input2=null;
        String output;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                String s1 = al.get(i);
                String s[] = s1.split(" ");
                input1 = s[0];
                input2 = s[1];

            } else {
                String s = al.get(i);
                s.trim();
                output = user_code(input1,input2);
                b = b & output.equals(s) ? true : false;
                if (b == false) {
                    System.out.println("Expected answer " + s);
                    System.out.println("Your answer " + output);
                    break;
                }
            }
        }
        return b;
    }

    static String user_code(String s1,String s2)
    {
        Solution solution = new Solution();
        return solution.Solution(s1, s2);
    }
}
