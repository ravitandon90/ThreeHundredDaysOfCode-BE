package subarray_sum_equals_k.Java;

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
        return al;
    }

    public static void main(String[] args) {
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");
    }

    static boolean drivercode() {
        String filePath = "proddata/data/subarray_sum_equals_k/testcases.txt";
        List<String> al = method(filePath);
        int out;
        int input1[] = new int[0];
        int input2 = 0;
        boolean b = true;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < al.size(); i++) {
            sb.append(al.get(i)+"||");
        }
        StringTokenizer st = new StringTokenizer(sb.toString(),"||");
        int n = st.countTokens();
        for (int i = 1;i<=n;i++)
        {
            String s = st.nextToken();
            input1 = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
            s = st.nextToken();
            input2 = Integer.parseInt(s);
            s = st.nextToken();
            out = Integer.parseInt(s);
            int user_out = user(input1,input2);
            b = b & out==(user_out) ? true : false;
            if (b == false) {
                System.out.println("Test case");
                System.out.println("nums = " + Arrays.toString(input1));
                System.out.println("k = "+input2);
                System.out.println("Your output " + user_out);
                System.out.println("Expected output " + out);
                return b;
            }
        }
        return b;
    }

    public static int user(int nums[], int k) {
        return 0;
    }
}
