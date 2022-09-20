package plus_one.Java;

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
        String filePath = "src/plus_one/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        int input1[] = new int[0];
        int output[] = new int[0]; 
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                    String s = al.get(i);
                    s = s.replace("[", "");
                    s = s.replace(","," ");
                    s = s.replace("]", "");
                    input1 = Arrays.stream(s.split(" ")).mapToInt(Integer :: parseInt).toArray();
                }
             else {
                String s = al.get(i);
                s = s.replace("[", "");
                s = s.replace(","," ");
                s = s.replace("]", "");
                String in[] = s.split(" ");
                output = Arrays.stream(in).mapToInt(Integer :: parseInt).toArray();
                int user[] = user_out(input1);
                b = b & Arrays.equals(output, user) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(Arrays.toString(input1));
                    System.out.println("Your output  " + Arrays.toString(user));
                    System.out.println("Expected output  " + Arrays.toString(output));
                    return b;
                }
            }
        }
        return b;
    }

    public static int[] user_out(int digits[])
    {
        Solution sol = new Solution();
        return sol.plusOne(digits);
    }

}
