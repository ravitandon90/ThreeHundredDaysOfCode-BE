package binary_watch.Java;

import java.io.*;
import java.util.*;

public class Driver {
    private static List<String> method(String filePath) {
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
        String filePath = "proddata/data/binary_watch/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        List<String> out;
        int input=0;
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                input = Integer.parseInt(al.get(i));

            } else {
                String o[] = al.get(i).split(" ");
                out = Arrays.asList(o);

                List<String> user_out = readBinaryWatch(input);

                b = b & out == user_out ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input);
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }
            }
        }
        return b;
    }


    public static List<String> readBinaryWatch(int turnedOn) {
        binary_watch.Java.Solution sol = new binary_watch.Java.Solution();
        return sol.readBinaryWatch(turnedOn);
    }

}