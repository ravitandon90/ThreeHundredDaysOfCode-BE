package kids_with_the_greatest_number_of_candies.Java;

import java.io.*;
import java.util.*;

public class Driver {
    private static List<String> method(String filePath) {
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
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
        String filePath = "src/kids_with_the_greatest_number_of_candies/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        int input1[] = new int[0];
        int input2 = 0;
        List<Boolean> output;
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                String s = al.get(i);
                s = s.replace("[", "");
                s = s.replace(",", " ");
                s = s.replace("]", "");
                StringTokenizer st = new StringTokenizer(s);
                int n = st.countTokens();
                input1 = new int[n-1];
                for(int j = 0;j<n-1;j++)
                {
                    input1[j] = Integer.parseInt(st.nextToken());
                }
                input2 = Integer.parseInt(st.nextToken().trim());
            } else {
                String s = al.get(i);
                s = s.replace("[", "");
                s = s.replace(",", " ");
                s = s.replace("]", "");
                String a[] = s.split(" ");
                Boolean o[] = new Boolean[a.length];
                for(int j=0;j<o.length;j++)
                {
                    o[j] = a[j].equals("true") ? true : false;
                }
                output = new ArrayList<>();
                for(boolean p : o)
                {
                    output.add(p);
                }
                List<Boolean> user_out = user(input1, input2);
                b = b & output==user_out ? true : false;
                if (b == false) {
                System.out.println("Test case");
                System.out.println(Arrays.toString(input1));
                System.out.println(input2);
                System.out.println("Your output " + user_out);
                System.out.println("Expected output " + (output));
                return b;
                }
            }
        }
        return b;
    }

    public static List<Boolean> user(int cadies[], int extra )
    {
        Solution sol = new Solution();
        return sol.kidsWithCandies(cadies, extra);
    }

}
