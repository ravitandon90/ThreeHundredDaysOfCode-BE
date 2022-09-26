package reverse_string.Java;

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
                str = str.replace("\"","")
                        .replace(",","")
                        .replace("[","")
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
        String filePath = "proddata/data/reverse_string/testcases.txt";
        List<String> al = method(filePath);
        int testcases = Integer.parseInt(al.remove(0));
        char[] out;
        char[] input = new char[0];
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                input = al.get(i).toCharArray();
            } else {
                out = al.get(i).toCharArray();
                user(input);
                b = b & Arrays.equals(out,input) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("s = "+Arrays.toString(input));
                    System.out.println("Your output " + Arrays.toString(input));
                    System.out.println("Expected output " + Arrays.toString(out));
                    return b;
                }
            }
        }
        return b;
    }

    public static void user(char s[]) {
        new reverse_string.Java.Solution().reverseString(s);
    }
}
