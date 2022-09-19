package word_break.Java;

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
        String filePath = "src/word_break/testcases.txt";
        List<String> al = method(filePath);

        String input1 = null;
        List<String> input2 = new ArrayList<>();
        boolean out;

        boolean b = true;

        for (int i = 0; i< al.size();i++)
        {
            if(i%2==0)
            {
                String in[] = al.get(i).split(" ");
                input1 = in[0];
                input2 = new ArrayList<>();
                for(int j = 1;j<in.length;j++)
                {
                    input2.add(in[j]);
                }
            }
            else
            {
                out = al.get(i).equals("true") ? true : false;
                b = b  & word(input1, input2) == out;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input1);
                    System.out.println(input2);
                    System.out.println("Your output  " + word(input1, input2));
                    System.out.println("Expected output  " + out);
                    return b;
                }
            }
            
        }
        return b;
    }

    public static boolean word(String a , List<String> b)
    {
        Solution sol = new Solution();
        return sol.wordBreak(a, b);
    }

}
