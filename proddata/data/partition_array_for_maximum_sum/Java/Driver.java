package partition_array_for_maximum_sum.Java;

import java.io.*;
import java.util.*;

public class Driver {
    private static List<String> method(String filePath) {
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                str = str.replace("\"","")
                        .replace("[","")
                        .replace(","," ")
                        .replace("]","");

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
        String filePath = "proddata/data/partition_array_for_maximum_sum/testcases.txt";
        List<String> al = method(filePath);
        int out;
        int input1[] = new int[0];
        int input2 = 0;
        boolean b = true;
        int i = 0;
        int count = 0;
        while (true) {

            if(i==0)
            {
                input1 = Arrays.stream(al.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
                i++;
            }
            if(i==1){
                input2 = Integer.parseInt(al.get(i));
                i++;
            }
            if(i==2)
            {
                out = Integer.parseInt(al.get(i));
                int user_out = user(input1,input2);
                b = b & out == user_out ? true : false;

                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("arr = " + Arrays.toString(input1));
                    System.out.println("k = " + input2);
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
                al.remove(0);
                al.remove(0);
                al.remove(0);
                i=0;
            }
            count++;
            if(count==3) break;
        }
        return b;
    }

    public static int user(int[] arr, int k) {
        return new partition_array_for_maximum_sum.Java.Solution().maxSumAfterPartitioning(arr, k);
    }
}