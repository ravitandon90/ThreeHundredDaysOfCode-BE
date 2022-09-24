package third_maximum_number.Java;

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
        String filePath = "proddata/data/third_maximum_number/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        int out =0 ;
        int[] input1=new int[0];

        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                input1 = Arrays.stream(al.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
            } else {
                out = Integer.parseInt(al.get(i));
                int user_out=user(input1);
                b = b & out==(user_out)  ? true : false;
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

    public static int user(int[] nums) {
        third_maximum_number.Java.Solution sol = new third_maximum_number.Java.Solution();
        return sol.thirdMax(nums);
    }

}

