package reorganize_string.Java;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Driver {
    private static List<String> method(String filePath) {
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                str.trim();
                str = str.replace("\"","")
                        .replace(","," ")
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
        String filePath = "proddata/data/reorganize_string/testcases.txt";
        List<String> al = method(filePath);
        String out;
        int testcases = Integer.parseInt(al.remove(0));
        String input = null;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                input = al.get(i);
            } else {
                out = al.get(i);
                String user_out = user(input);
                b = b & out.equals(user_out) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("s = "+input);
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static String user(String s) {
        return new reorganize_string.Java.Solution().reorganizeString(s);
    }
}
