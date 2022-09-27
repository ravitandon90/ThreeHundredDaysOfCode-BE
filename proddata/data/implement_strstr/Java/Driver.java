package implement_strstr.Java;


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
                str = str.replace("\"","");
                str = str.replace(", "," ");
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
        String filePath = "proddata/data/implement_strstr/testcases.txt";
        List<String> al = method(filePath);

        int out;
        String input[] = new String[0];
        String input1 = null;
        String input2 = null;
        int testcases = Integer.parseInt(al.remove(0));
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                input = al.get(i).split(" ");
                input1 = input[0];
                input2 = input[1];
            } else {
                out = Integer.parseInt(al.get(i));
                int user_out = user(input1,input2);
                b = b & out==(user_out) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input1);
                    System.out.println(input2);
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static int user(String s1, String s2) {
        implement_strstr.Java.Solution sol = new implement_strstr.Java.Solution();
        return sol.strStr(s1,s2);
    }
}
