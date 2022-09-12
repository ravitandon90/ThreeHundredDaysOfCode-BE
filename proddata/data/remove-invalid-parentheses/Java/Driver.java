package remove_invalid_parentheses.Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Driver {

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
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");
        ;
    }

    static boolean drivercode() {
        String filePath = "src/remove_invalid_parentheses/testcases.txt";
        List<String> al = method(filePath);

        List<String> out;
        String input = null;

        boolean b = true;

        for (int i = 0; i< al.size();i++)
        {
            if(i%2==0)
            {
                input = al.get(i);
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
                    System.out.println("Expected output  " + out);
                    System.out.println("Your output  " + user_out);
                    return b;
                }
            }
        }
        return b;
    }

    public static List<String> user_out(String s) {
        Solution sol = new Solution();
        return sol.removeInvalidParentheses(s);
    }
}
