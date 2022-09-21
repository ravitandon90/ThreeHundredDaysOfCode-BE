package generate_parentheses.Java;

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
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");
    }

    static boolean drivercode() {
        String filePath = "proddata/data/generate_parentheses/testcases.txt";
        List<String> al = method(filePath);

        List<String> out = new ArrayList<>();
        int input = 0;

        boolean b = true;

        for (int i = 0; i< al.size();i++)
        {
            if(i%2==0)
            {
                input = Integer.parseInt(al.get(i));
            }
            else
            {
                String output[] = al.get(i).split(" ");
                out = Arrays.asList(output);
                List<String> user_out = user_out(input);
                Collections.sort(out);
                Collections.sort(user_out);
                b = b & user_out.equals(out);

                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input);
                    System.out.println("Expected output  " + out);
                    System.out.println("Your output  " + user_out);
                    return b;
                }
            }
        }
        return b;
    }

    public static List<String> user_out(int n) {
        generate_parentheses.Java.Solution sol = new generate_parentheses.Java.Solution();
        return sol.generateParenthesis(n);
    }
}