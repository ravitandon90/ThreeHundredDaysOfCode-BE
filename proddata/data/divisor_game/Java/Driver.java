package divisor_game.Java;

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
        String filePath = "proddata/data/divisor_game/testcases.txt";
        List<String> al = method(filePath);
        int testcase = Integer.parseInt(al.remove(0));
        int input = 0;
        boolean out;

        boolean b = true;

        for (int i = 0; i< 2*testcase;i++)
        {
            if(i%2==0)
            {
                input = Integer.parseInt(al.get(i));
            }
            else
            {
                out = al.get(i).equals("true")?true:false;
                boolean user_out = game(input);

                b = b & user_out == (out);

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

    public static boolean game(int n)
    {
        divisor_game.Java.Solution sol = new divisor_game.Java.Solution();
        return sol.divisorGame(n);
    }
}
