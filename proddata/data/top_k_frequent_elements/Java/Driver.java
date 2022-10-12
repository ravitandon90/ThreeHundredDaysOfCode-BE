package top_k_frequent_elements.Java;

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
                        .replace(","," ")
                        .replace("]","")
                        .replace("  ","||")
                        .replace("[","");
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
        String filePath = "proddata/data/top_k_frequent_elements/testcases.txt";
        List<String> al = method(filePath);
        int testcase = Integer.parseInt(al.remove(0));
        int out[];
        int input1[] = new int[0];
        int k = 0;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                input1 = Arrays.stream(st.nextToken().split(" ")).mapToInt(Integer::parseInt).toArray();
                k = Integer.parseInt(st.nextToken());
            } else {
                out = Arrays.stream(al.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
                int[] user_out = user(input1, k);

                b = b & Arrays.equals(out,user_out);

                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("nums = " + Arrays.toString(input1));
                    System.out.println("k = " + k);
                    System.out.println("Your output " + Arrays.toString(user_out));
                    System.out.println("Expected output " + Arrays.toString(out));
                    return b;
                }
            }
        }
        return b;
    }

    public static int[] user(int nums[], int k) {
        return new top_k_frequent_elements.Java.Solution().topKFrequent(nums, k);
    }
}
