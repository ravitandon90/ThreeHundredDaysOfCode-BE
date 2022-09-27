package count_number_of_teams.Java;

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
        String filePath = "proddata/data/count_number_of_teams/testcases.txt";
        List<String> al = method(filePath);

        int out;
        int[] input1=new int[0];

        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                al.get(i).trim();
                input1 = Arrays.stream(al.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
            } else {
                out = Integer.parseInt(al.get(i));
                int user_out= user(input1);
                b = b & out==user_out  ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(Arrays.toString(input1));
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static int user(int[] nums) {
        count_number_of_teams.Java.Solution sol = new count_number_of_teams.Java.Solution();
        return sol.numTeams(nums);
    }

}


