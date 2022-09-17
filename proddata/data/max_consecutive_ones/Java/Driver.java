package max_consecutive_ones.Java;

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
        String filePath = "src/max_consecutive_ones/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        int input1[] = new int[0];
        int output;
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                String input[] = al.get(i).split(" ");
                input1 = new int[input.length];
                int j = 0;
                for (String p:input) {
                    input1[j] = Integer.parseInt(p);
                    j++;
                }
            } else {
                output = Integer.parseInt(al.get(i));
                int user_out = user_out(input1);

                b = b & output == user_out ? true : false;
                if (b == false) {
                    System.out.println(Arrays.toString(input1));
                    System.out.println("Expected output  " + output);
                    System.out.println("Your output  " + user_out);
                    return b;
                }
            }
        }
        return b;
    }
    public static int user_out(int nums[])
    {
        Solution sol = new Solution();
        return sol.findMaxConsecutiveOnes(nums);
    }

}
