package rotate_array.Java;

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
        String filePath = "proddata/data/rotate_array/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        int input1[] = new int[0];
        int input2 = 0;
        int[] output;
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
                output = Arrays.stream(a).mapToInt(Integer :: parseInt).toArray(); 
                user(input1, input2);  
                b = b & Arrays.equals(output, input1) ? true : false;
                if (b == false) {
                System.out.println("Test case");
                System.out.println(Arrays.toString(input1));
                System.out.println(input2);
                System.out.println("Your output " + Arrays.toString(input1));
                System.out.println("Expected output " + Arrays.toString(output));
                return b;
                }
            }
        }
        return b;
    }

    public static void user(int inpu1[], int input2)
    {
        rotate_array.Java.Solution sol = new rotate_array.Java.Solution();
        sol.rotate(inpu1, input2);
    }
}
