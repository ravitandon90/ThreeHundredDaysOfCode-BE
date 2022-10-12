package uncommon_words_from_two_sentences.Java;

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
        String filePath = "proddata/data/uncommon_words_from_two_sentences/testcases.txt";
        List<String> al = method(filePath);
        int testcase = Integer.parseInt(al.remove(0));
        String[] out;
        String input1 = null;
        String input2 = null;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                input1 = st.nextToken();
                input2 = (st.nextToken());
            } else {
                out = (al.get(i).split(" "));
                String[] user_out = user(input1, input2);
                b = b & Arrays.equals(user_out,out) ? true : false;

                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("s1 = " + input1);
                    System.out.println("s2 = " + input2);
                    System.out.println("Your output " + Arrays.toString(user_out));
                    System.out.println("Expected output " + Arrays.toString(out));
                    return b;
                }
            }
        }
        return b;
    }

    public static String[] user(String s1, String s2) {
        return new uncommon_words_from_two_sentences.Java.Solution().uncommonFromSentences(s1, s2);
    }
}
