package letter_tile_possibilities.Java;

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
                str = str.replace("\"","");
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
        String filePath = "proddata/data/letter_tile_possibilities/testcases.txt";
        List<String> al = method(filePath);
        int testcases = Integer.parseInt(al.remove(0));
        int out;
        String input1 = null;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                input1 = al.get(i);
            } else {
                out = Integer.parseInt(al.get(i));
                int user_out = user(input1);
                b = b & out==(user_out) ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input1);
                    System.out.println("Your output " + user_out);
                    System.out.println("Expected output " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static int user(String tiles) {
        letter_tile_possibilities.Java.Solution sol = new letter_tile_possibilities.Java.Solution();
        return sol.numTilePossibilities(tiles);
    }
}
