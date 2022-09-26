package maximum_score_from_removing_stones.Java;

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
                str = str.replace("\"","").replace(", "," ");
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
        String filePath = "proddata/data/maximum_nesting_depth_of_the_parentheses/testcases.txt";
        List<String> al = method(filePath);
        int testcases = Integer.parseInt(al.remove(0));
        int out;
        String input[] = new String[0];
        int input1 = 0;
        int input2 = 0;
        int input3 = 0;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                input = al.get(i).split(" ");
                input1 = Integer.parseInt(input[0]);
                input2 = Integer.parseInt(input[1]);
                input3 = Integer.parseInt(input[2]);
            } else {
                out = Integer.parseInt(al.get(i));
                int user_out = user(input1,input2,input3);
                b = b & out==(user_out) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("a "+input1);
                    System.out.println("b "+input2);
                    System.out.println("c "+input3);
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static int user(int a, int b, int c) {
        return new maximum_score_from_removing_stones.Java.Solution().maximumScore(a,b,c);
    }
}
