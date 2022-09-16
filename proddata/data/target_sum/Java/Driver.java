package target_sum.Java;

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
        String filePath = "src/target_sum/testcases.txt";
        List<String> al = method(filePath);

        int input1[] = new int[0];
        int input2 = 0;
        int out;

        boolean b = true;

        for (int i = 0; i< al.size();i++)
        {
            if(i%2==0)
            {
                StringTokenizer st = new StringTokenizer(al.get(i));
                int n = st.countTokens();
                input1 = new int[n-1];
                for(int j= 0;j<input1.length;j++)
                {
                    input1[j] = Integer.parseInt(st.nextToken());
                }
                input2 = Integer.parseInt(st.nextToken());
            }
            else
            {
                out = Integer.parseInt(al.get(i));
                b = b  & user_out(input1, input2) == out;
                if (b == false) {
                    System.out.println("Expected output  " + out);
                    System.out.println("Your output  " + user_out(input1, input2));
                    return b;
                }
            }
            
        }
        return b;
    }

    public static int user_out(int[] a, int b)
    {
        Solution sol = new Solution();
        return sol.findTargetSumWays(a, b);
    }

}
