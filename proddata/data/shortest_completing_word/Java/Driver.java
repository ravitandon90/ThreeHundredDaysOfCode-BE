package shortest_completing_word.Java;

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
                        .replace(", ","||")
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
        String filePath = "proddata/data/shortest_completing_word/testcases.txt";
        List<String> al = method(filePath);
        int testcase = Integer.parseInt(al.remove(0));
        String out;
        String input1 = null;
        String input2[] = new String[0];
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                input1 = st.nextToken();
                input2 = st.nextToken().split(" ");
            } else {
                out = al.get(i);
                String user_out = user(input1,input2);
                b = b & out.equals(user_out) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("licensePlate = " + input1);
                    System.out.println("words = "+Arrays.toString(input2));
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static String user(String licensePlate, String words[]) {
        return new shortest_completing_word.Java.Solution().shortestCompletingWord(licensePlate,words);
    }
}
