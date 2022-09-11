package binary_search.Java;


import java.io.*;
import java.util.*;

public class Driver {
    private static List<String> method(String filePath) {
        StringBuilder builder = new StringBuilder();
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                str = str.replace("]","||");
                str = str.replace("[", "");
                str = str.replace(",", " ");
                al.add(str);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
//        System.out.println(al);
        // Returning a string arraylist
        return al;
    }

    public static void main(String[] args) {
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");
        ;
    }

    static boolean drivercode() {
        String filePath = "src/binary_search/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        int input1[] = new int[0];
        int input2=0;
        int output;
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {

                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                String in[] = st.nextElement().toString().split(" ");
                input1 = Arrays.stream(in).mapToInt(Integer::parseInt).toArray();
                input2 = Integer.parseInt(st.nextElement().toString().trim());

            } else {
                output = Integer.parseInt(al.get(i));

                int user_out = search(input1,input2);

                b = b & output == user_out ? true : false;
                if (b == false) {
                    System.out.println("Expected output  " + output);
                    System.out.println("Your output  " + user_out);
                    return b;
                }
            }
        }
        return b;
    }
    public static int search(int[] nums, int target) {
        Solution solution = new Solution();
        return solution.search(nums,target);
    }

}