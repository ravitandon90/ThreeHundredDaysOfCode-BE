package merge_sorted_array.Java;

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
        String filePath = "src/merge_sorted_array/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        int input1[] = new int[0];
        int input2 = 0;
        int input3[] = new int[0];
        int input4 = 0;
        int output[] = new int[0]; 
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                StringTokenizer st = new StringTokenizer(al.get(i)," ");
                    String s = st.nextToken();
                    s = s.replace("[", "");
                    s = s.replace(","," ");
                    s = s.replace("]", "");
                    input1 = Arrays.stream(s.split(" ")).mapToInt(Integer :: parseInt).toArray();
                    s = st.nextToken();
                    s=s.replace(",", "");
                    input2 = Integer.parseInt(s);
                    s=st.nextToken();
                    s = s.replace("[", "");
                    s = s.replace(","," ");
                    s = s.replace("]", "");
                    input3 = Arrays.stream(s.split(" ")).mapToInt(Integer :: parseInt).toArray();
                    s=st.nextToken();
                    input4 = Integer.parseInt(s);
            } else {
                String s = al.get(i);
                s = s.replace("[", "");
                s = s.replace(","," ");
                s = s.replace("]", "");
                String in[] = s.split(" ");
                output = Arrays.stream(in).mapToInt(Integer :: parseInt).toArray();
                Arrays.sort(output);
                user(input1,input2,input3,input4);
                b = b & Arrays.equals(output, input1) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(Arrays.toString(input1));
                    System.out.println(input2);
                    System.out.println(Arrays.toString(input3));
                    System.out.println(input4);
                    System.out.println("Your output  " + Arrays.toString(input1));
                    System.out.println("Expected output  " + Arrays.toString(output));
                    return b;
                }
            }
        }
        return b;
    }

    public static void user(int[] nums1, int m, int[] nums2, int n)
    {
        Solution sol = new Solution();
        sol.merge(nums1, m, nums2, n);
    }

}
