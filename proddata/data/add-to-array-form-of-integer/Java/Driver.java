
package add_array_to_form_an_integer;

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
        System.out.println(al);
        // Returning a string arraylist
        return al;
    }

    public static void main(String[] args) {
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");;
    }

    static boolean drivercode() {
        String filePath = "D:\\java\\driver_codes\\src\\add_array_to_form_an_integer\\test cases.txt";
        List<String> al = method(filePath);
        String input1=null;
        String input2=null;
        String output;
        boolean b = true;
        for (int i = 0; i <=al.size()/2; i++) {
            if (i % 2 == 0) {
                System.out.println(" "+al.get(i));

            } else {
                String s = al.get(i);
                s.trim();
//                output = user_code(input1,input2);
//                System.out.println("expected output---"+output);
//                System.out.println("user output---"+user_ouput);
//                b = b & output.equals(s) ? true : false;
                if (b == false) {
                    System.out.println("Expected answer " + s);
                    System.out.println("Your answer " + "empty");
                }
            }
        }
        return b;
    }
    
//    static String user_code(String s1,String s2)
//    {
//        solution_addString solution = new solution_addString(s1, s2);
//        return solution.solution_addString(s1, s2);
//    }
}
