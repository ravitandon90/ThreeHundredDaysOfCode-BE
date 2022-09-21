package add_to_array_form_of_Integer.Java;

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
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");;
    }

    static boolean drivercode()     {
        String filePath = "proddata/data/add_to_array_form_of_integer/test cases.txt";
        List<String> al = method(filePath);

        int input2;
        boolean b = true;
        int i = 0;
        while(!al.isEmpty())
        {
            String[] in1 = al.get(i).split(" ");
            int input1[] = new int[in1.length];
            for (int j = 0; j<input1.length;j++)
            {
                input1[j] = Integer.parseInt(in1[j]);
            }

            al.remove(i);

            input2=Integer.parseInt(al.get(i));
            al.remove(i);

            String out_Array[] = al.get(i).split(" ");
            al.remove(i);
            List<Integer> out = new ArrayList<>();
            for (int j = 0; j<out_Array.length;j++)
            {
                out.add(Integer.parseInt(out_Array[j]));
            }
            List<Integer> user_output = addToArrayForm(input1,input2);

            b = b & out.equals(user_output) ? true : false;

            if(b==false) {
                System.out.println("Test case");
                System.out.println(Arrays.toString(input1));
                System.out.println(input2);
                System.out.println("Your output  " + user_output);
                System.out.println("Expected output  " + out);
                return b;
            }
        }
        return b;
    }

    public static List<Integer> addToArrayForm(int[] num, int k)
    {
        add_to_array_form_of_Integer.Java.Solution solution = new add_to_array_form_of_Integer.Java.Solution();
        return solution.addToArrayForm(num,k);

    }
}