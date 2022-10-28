package reorder_data_in_log_files.Java;

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
                        .replace("[","")
                        .replace(",","||")
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
        String filePath = "proddata/data/reorder_data_in_log_files/testcases.txt";
        List<String> al = method(filePath);
        int testcases = Integer.parseInt(al.remove(0));
        String[] out;
        String input1[] = new String[0];
        boolean b = true;
        for (int i = 0;i< al.size();i++)
        {
            if(i % 2==0)
            {
                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                int n = st.countTokens();
                input1 = new String[n];
                for(int j = 0; j<n; j++)
                {
                    input1[j] = st.nextToken();
                }
            }
            else
            {
                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                int n = st.countTokens();
                out = new String[n];
                for(int j = 0; j<n; j++)
                {
                    out[j] = st.nextToken();
                }
                String[] user_out = user(input1);
                b = b & Arrays.equals(out,user_out) ? true : false;

                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("logs = " + Arrays.toString(input1));
                    System.out.println("Your output " + Arrays.toString(user_out));
                    System.out.println("Expected output " + Arrays.toString(out));
                    return b;
                }
            }
        }
        return b;
    }

    public static String[] user(String [] arr) {
        return new reorder_data_in_log_files.Java.Solution().reorderLogFiles(arr);
    }
}