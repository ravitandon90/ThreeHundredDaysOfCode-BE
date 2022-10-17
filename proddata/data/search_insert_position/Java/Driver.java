package search_insert_position.Java;

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
                        .replace("]","")
                        .replace("  ","||");

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
        String filePath = "proddata/data/search_insert_position/testcases.txt";
        List<String> al = method(filePath);
        int testcases = Integer.parseInt(al.remove(0));
        int out;
        int input1[] = new int[0];
        int input2 = 0;
        boolean b = true;
        for (int i = 0;i < al.size();i++)
        {
            if(i % 2==0)
            {
                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                input1 = Arrays.stream(st.nextToken().split(" ")).mapToInt(Integer::parseInt).toArray();
                input2 = Integer.parseInt(st.nextToken());
            }
            else
            {
                out = Integer.parseInt(al.get(i));
                int user_out = user(input1,input2);
                b = b & out == user_out ? true : false;

                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("logs = " + Arrays.toString(input1));
                    System.out.println("target = " + input2);
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static int user(int[] nums, int target) {
        return new search_insert_position.Java.Solution().searchInsert(nums, target);
    }
}