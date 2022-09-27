package continuous_subarray_sum.Java;

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
                str = str.replace("nums = ","");
                str = str.replace("k = ","");
                str = str.replace(", ","||");
                str = str.replace(","," ");
                str = str.replace("\"","");
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
        String filePath = "proddata/data/continuous_subarray_sum/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        boolean out;
        int[] input1=new int[0];
        int input2 = 0;

        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                input1 = Arrays.stream(st.nextToken().split(" ")).mapToInt(Integer::parseInt).toArray();
                input2 = Integer.parseInt(st.nextToken());
            } else {
                out = al.get(i).equals("true") ? true : false;
                boolean user_out= user(input1,input2);
                b = b & out==user_out  ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(Arrays.toString(input1));
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static boolean user(int[] nums,int k) {
        continuous_subarray_sum.Java.Solution sol = new continuous_subarray_sum.Java.Solution();
        return sol.checkSubarraySum(nums,k);
    }

}

