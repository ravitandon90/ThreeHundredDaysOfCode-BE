package longest_common_prefix.Java;

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
                str = str.replace(","," ");
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
        String filePath = "proddata/data/longest_common_prefix/testcases.txt";
        List<String> al = method(filePath);
        int testcases = Integer.parseInt(al.remove(0));
        String out;
        String input1[] = new String[0];
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                input1 = al.get(i).split(" ");
            } else {
                out = al.get(i);
                String user_out = user(input1);
                b = b & out==(user_out) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(Arrays.toString(input1));
                    if(user_out.equals(""))
                    {
                        System.out.println("Your output " + "\"\"");
                    }
                    else if(!user_out.equals(""))
                    {
                        System.out.println("Your output " + user_out);
                    }
                    else if(out.equals(""))
                    {
                        System.out.println("Expected output " + "\"\"");
                    }
                    else
                    {
                        System.out.println("Expected output " + out);
                    }
                    return b;
                }
            }
        }
        return b;
    }

    public static String user(String[] strs) {
        longest_common_prefix.Java.Solution sol = new longest_common_prefix.Java.Solution();
        return sol.longestCommonPrefix(strs);
    }
}
