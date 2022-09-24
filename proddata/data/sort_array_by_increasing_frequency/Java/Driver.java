package sort_array_by_increasing_frequency.Java;

import java.io.*;
import java.util.*;

public class Driver {
    private static List<String> method(String filePath) {
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                str.trim();
                str = str.replace("]","");
                str = str.replace("[","");
                str = str.replace(","," ");
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
        String filePath = "proddata/data/sort_array_by_increasing_frequency/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        int[] out = new int[0] ;
        int[] input1 = new int[0];
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                input1 = Arrays.stream(al.get(i).split(" ")).mapToInt(Integer ::parseInt).toArray();
            } else {

                out = Arrays.stream(al.get(i).split(" ")).mapToInt(Integer ::parseInt).toArray();
                int[] user_out= user(input1);
                b = b & Arrays.equals(out,user_out)  ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(Arrays.toString(input1));
                    System.out.println("Your output  " + Arrays.toString(user_out));
                    System.out.println("Expected output  " + Arrays.toString(out));
                    return b;
                }
            }
        }
        return b;
    }

    public static int[] user(int[] nums) {
        sort_array_by_increasing_frequency.Java.Solution sol = new sort_array_by_increasing_frequency.Java.Solution();
        return sol.frequencySort(nums);
    }

}

