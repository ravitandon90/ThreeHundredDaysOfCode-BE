package unique_paths.Java;

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
        String filePath = "proddata/data/unique_paths/testcases.txt";
        List<String> al = method(filePath);

        int input1 = 0;
        int input2 = 0;
        int out;

        boolean b = true;

        for (int i = 0; i< al.size();i++)
        {
            if(i%2==0)
            {
                String in[] = al.get(i).split(" ");
                input1 = Integer.parseInt(in[0]);
                input2 = Integer.parseInt(in[1]);
            }
            else
            {
                out = Integer.parseInt(al.get(i));
                b = b  & paths(input1, input2) == out;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input1);
                    System.out.println(input2);
                    System.out.println("Expected output  " + out);
                    System.out.println("Your output  " + paths(input1, input2));
                    return b;
                }
            }
            
        }
        return b;
    }

    public static int paths(int a , int b)
    {
        unique_paths.Java.Solution sol = new unique_paths.Java.Solution();
        return sol.uniquePaths(a, b);
    }

}
