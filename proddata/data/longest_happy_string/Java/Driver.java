package longest_happy_string.Java;

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
                str = str.replace("[","");
                str = str.replace("\"","");
                str = str.replace(", "," ");
                str = str.replace("]","");
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
        String filePath = "proddata/data/longest_happy_string/testcases.txt";
        List<String> al = method(filePath);
        int testcases = Integer.parseInt(al.remove(0));
        String out;
        int input[] = new int[0];
        int input1=0,input2=0,input3=0;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                input = Arrays.stream(al.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
                input1 = input[0];
                input2 = input[1];
                input3 = input[2];
            } else {
                out = al.get(i);
                String user_out = user(input1,input2,input3);
                b = b & out.equals(user_out) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("a = "+input1);
                    System.out.println("b = "+input2);
                    System.out.println("c = "+input3);
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static String user(int a, int b, int c) {
        longest_happy_string.Java.Solution sol = new longest_happy_string.Java.Solution();
        return sol.longestDiverseString(a,b,c);
    }
}
