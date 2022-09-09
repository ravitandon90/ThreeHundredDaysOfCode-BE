package add_binary.Java;

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
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");;
    }

    static boolean drivercode() {
        String filePath = "src/add_binary/test cases";
        List<String> al = method(filePath);
        String input1=null;
        String input2=null;
        String output;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                String s1 = al.get(i);
                String s[] = s1.split(" ");
                input1 = s[0];
                input2 = s[1];

            } else {
                String s = al.get(i);
                s.trim();
                output = user_code(input1,input2);
//                System.out.println("expected output---"+output);
//                System.out.println("user output---"+user_ouput);
                b = b & output.equals(s) ? true : false;
                if (b == false) {
                    System.out.println("Expected answer " + s);
                    System.out.println("Your answer " + output);
                    break;
                }
            }
        }
        return b;
    }

    static String user_code(String s1,String s2)
    {
        solution_addBinary solution = new solution_addBinary(s1, s2);
        return solution.solution_adBinary(s1, s2);
    }
}