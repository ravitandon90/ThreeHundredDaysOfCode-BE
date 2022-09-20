package longest_continuous_increasing_subsequence.Java;

import java.io.*;
import java.util.*;

public class Driver {
    private static List<String> method(String filePath) {
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                str = str.replace("]","");
                str = str.replace("[", "");
                str = str.replace(",", " ");
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
        String filePath = "proddata/data/longest_continuous_increasing_subsequence/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        int input1[] = new int[0];
        int output;
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                String input[] = al.get(i).split(" ");
                input1 = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
            } else {
                output = Integer.parseInt(al.get(i));
                int user_out = user_out(input1);

                b = b & output == user_out ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(Arrays.toString(input1));
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + output);
                    return b;
                }
            }
        }
        return b;
    }
    
    public static int user_out(int nums[])
    {
        longest_continuous_increasing_subsequence.Java.Solution sol = new longest_continuous_increasing_subsequence.Java.Solution();
        return sol.findLengthOfLCIS(nums);
    }
}
