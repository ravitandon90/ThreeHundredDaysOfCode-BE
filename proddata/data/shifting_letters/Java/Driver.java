package shifting_letters.Java;

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
                str = str.replace("]","");
                str = str.replace("[","");
                str = str.replace(", ", "||");
                str = str.replace(","," ");
                str = str.replace("\"", "");
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
        String filePath = "proddata/data/shifting_letters/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        String out ;
        String input1=null;
        int[] input2 = new int[0];
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                input1 = st.nextElement().toString();
                input2 = Arrays.stream(st.nextElement().toString().split(" ")).mapToInt(Integer ::parseInt).toArray();
            } else {

                out = al.get(i);
                String user_out= user(input1,input2);

                b = b & out.equals(user_out)  ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input1);
                    System.out.println(Arrays.toString(input2));
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static String user(String s, int[] shifts)
    {
        shifting_letters.Java.Solution sol = new shifting_letters.Java.Solution();
        return sol.shiftingLetters(s,shifts);
    }

}
