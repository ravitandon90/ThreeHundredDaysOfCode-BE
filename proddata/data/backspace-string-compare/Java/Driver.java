package backspace_string_comparison.Java;

import java.io.*;
import java.util.*;

public class Driver {

    private static List<String> method(String filePath) {
        StringBuilder builder = new StringBuilder();
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                al.add(str.replace("\"",""));
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
        String filePath = "src/backspace_string_comparison/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        String input1 = null;
        String input2 = null;
        String output;
        boolean b = true;
        for (int i = 0; i<2*testcases;i++)
        {
            if(i%2==0)
            {
                String in[] = al.get(i).split(", ");
                input1 = in[0];
                input2 = in[1];

            }
            else
            {
                output = al.get(i);
                boolean out = output.equals("true") ? true : false;
                boolean user_out = backspaceCompare(input1,input2);

                b = b & out==user_out ? true : false;
                if(b==false)
                {
                    System.out.println("Expected output  " + out);
                    System.out.println("Your output  " + user_out);
                    return b;
                }
            }
        }
        return b;
    }

    public static boolean backspaceCompare(String s, String t) {
         Solution solution = new Solution();
         return solution.backspaceCompare(s,t);
    }
}