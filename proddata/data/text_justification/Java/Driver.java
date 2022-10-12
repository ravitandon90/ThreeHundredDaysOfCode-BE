package text_justification.Java;

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
                         .replace(", ","||")
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
        String filePath = "proddata/data/text_justification/testcases.txt";
        List<String> al = method(filePath);
        int testcase = Integer.parseInt(al.remove(0));
        List<String> out;
        String input1[] = new String[0];
        int maxWeidth = 0;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                int n = st.countTokens();
                input1 = new String[n-1];
                for (int j = 0;j<n-1;j++)
                {
                    input1[j] = st.nextToken();
                }
                maxWeidth = Integer.parseInt(st.nextToken());
            } else {
                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                out = new ArrayList<>();
                while(st.hasMoreTokens())out.add(st.nextToken());
                List<String> user_out = user(input1, maxWeidth);

                for (int j = 0; j<out.size();j++) {
                    b = b & out.get(j).equals(user_out.get(j));
                }

                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("words = " + Arrays.toString(input1));
                    System.out.println("maxWidth = " + maxWeidth);
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static List<String> user(String words[], int maxWidth) {
        return new text_justification.Java.Solution().fullJustify(words, maxWidth);
    }
}
