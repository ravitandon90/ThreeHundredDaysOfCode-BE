package interleaving_string.Java;
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
                al.add(str);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.println(al);
        return al;
    }

    public static void main(String[] args) {
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");
    }

    static boolean drivercode() {
        String filePath = "proddata/data/interleaving_string/testcases.txt";
        List<String> al = method(filePath);

        boolean out;
        String input[] = new String[0];
        String input1 = null;
        String input2 = null;
        String input3 = null;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                input = al.get(i).split(" ");
                input1 = input[0];
                input2 = input[1];
                input3 = input[2];
            } else {
                out = al.get(i).equals("true") ? true : false;
                boolean user_out = user(input1,input2,input3);
                b = b & out==(user_out) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input1);
                    System.out.println(input2);
                    System.out.println(input3);
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static boolean user(String s1, String s2, String s3) {
        interleaving_string.Java.Solution sol = new interleaving_string.Java.Solution();
        return sol.isInterleave(s1,s2,s3);
    }
}

