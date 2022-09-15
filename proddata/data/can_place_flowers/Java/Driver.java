package can-place-flowers.Java;

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
                str.trim();
                str = str.replace("],", "||");
                str = str.replace("[", "");
                str = str.replace(",", " ");
                al.add(str);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return al;
    }

    public static void main(String[] args) {
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");
        ;
    }

    static boolean drivercode() {
        String filePath = "proddata/data/can-place-flowers/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        boolean out;
        int input1[] = new int[0];
        int input2 = 0;
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {

                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                String s[] = st.nextElement().toString().split(" ");
                input1 = Arrays.stream(s).mapToInt(Integer::parseInt).toArray();
                input2 = Integer.parseInt(st.nextElement().toString().trim());
            } else {

                out = al.get(i).equals("true") ? true : false;

                boolean user_out = canPlaceFlowers(input1, input2);

                b = b & out == user_out ? true : false;
                if (b == false) {
                    System.out.println("Expected output  " + out);
                    System.out.println("Your output  " + user_out);
                    return b;
                }
            }
        }
        return b;
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        Solution sol = new Solution();
        return sol.canPlaceFlowers(flowerbed,n);
    }
}
