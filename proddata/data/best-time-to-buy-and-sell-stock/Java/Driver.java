package best_time_to_buy_and_sell_stocks.Java;

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
                str = str.replace("]","");
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
        String filePath = "src/best_time_to_buy_and_sell_stocks/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        int input1[] = new int[0];
        int output;
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                String input[] = al.get(i).split(" ");
                input1 = new int[input.length];
                int j = 0;
                for (String p:input) {
                    input1[j] = Integer.parseInt(p);
                    j++;
                }
            } else {
                output = Integer.parseInt(al.get(i));
                int user_out = user_out(input1);

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

    static int user_out(int prices[])
    {
        Solution solution = new Solution();
        return solution.maxProfit(prices);
    }
}

