package sum_of_all_subset_xor_totals.Java;

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
        ;
    }

    static boolean drivercode() {
        String filePath = "src/sum_of_all_subset_xor_totals/testcases.txt";
        List<String> al = method(filePath);

        int out = 0;
        int[] input = new int[0];

        boolean b = true;

        for (int i = 0; i< al.size();i++)
        {
            if(i%2==0)
            {
                String in[] = al.get(i).split(" ");
                input = Arrays.stream(in).mapToInt(Integer::parseInt).toArray();
            }
            else
            {
                out = Integer.parseInt(al.get(i));
                int user_out = user_out(input);

                b = b & user_out == (out);

                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(Arrays.toString(input));
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static int user_out(int nums[])
    {
        Solution sol = new Solution();
        return sol.subsetXORSum(nums);
    }
}
