package basic_calculator_ii.Java;

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
        String filePath = "src/basic_calculator_ii/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        String input1 = null;
        int output;
        boolean b = true;
        for (int i = 0; i<2*testcases;i++)
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
                    System.out.println("Expected output  " + output);
                    System.out.println("Your output  " + user_out);
                    return b;
                }
            }
        }
        return b;
    }

    public static int calculate(String s) {
        Solution solution = new Solution();
        return solution.calculate(s);
    }
}
