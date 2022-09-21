package basic_calculator_ii.Java;

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
        String filePath = "proddata/data/basic_calculator_ii/testcases.txt";
        List<String> al = method(filePath);

        String input1 = null;
        int output;
        boolean b = true;
        for (int i = 0; i<al.size();i++)
        {
            if(i%2==0)
            {
                input1 = al.get(i);
            }
            else
            {
                output = Integer.parseInt(al.get(i));
                int user_out = calculate(input1);

                b = b & output==user_out ? true : false;
                if(b==false)
                {
                    System.out.println("Test case");
                    System.out.println(input1);
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + output);
                    return b;
                }
            }
        }
        return b;
    }

    public static int calculate(String s) {
        basic_calculator_ii.Java.Solution solution = new basic_calculator_ii.Java.Solution();
        return solution.calculate(s);
    }
}
