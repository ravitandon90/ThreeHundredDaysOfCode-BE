package top_k_frequent_words.Java;

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
        String filePath = "proddata/data/top_k_frequent_words/testcases.txt";
        List<String> al = method(filePath);
        int testcase = Integer.parseInt(al.remove(0));
        List<String> out;
        String input1[] = new String[0];
        int k = 0;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                input1 = st.nextToken().split(" ");
                k = Integer.parseInt(st.nextToken());
            } else {
                out = List.of(al.get(i).split(" "));
                List<String> user_out = user(input1, k);
                if(out.size()!=user_out.size()) b = false;
                for (int j = 0;j<user_out.size();j++)
                {
                    b = b & user_out.get(j).equals(out.get(j));
                }

                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("words = " + Arrays.toString(input1));
                    System.out.println("k = " + k);
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static List<String> user(String words[], int k) {
        return new top_k_frequent_words.Java.Solution().topKFrequent(words, k);
    }
}
