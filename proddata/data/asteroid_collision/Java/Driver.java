package asteroid_collision.Java;

import java.io.*;
import java.util.*;

public class Driver {

    private static List<String> method(String filePath) {
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                str = str.replace("[","")
                         .replace(","," ")
                         .replace("]","");
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
        String filePath = "proddata/data/asteroid_collision/testcases.txt";
        List<String> al = method(filePath);
        int testcases = Integer.parseInt(al.remove(0));
        int input[] = new int[0];
        int out[];
        boolean b = true;
            for (int i = 0;i<al.size();i++)
            {
                if(i%2==0)
                {
                    input = Arrays.stream(al.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
                }
                else
                {
                    if(al.get(i).equals(""))
                    {
                        out = new int[0];
                    }
                    else
                    {
                        out = Arrays.stream(al.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
                    }
                    int[] user_out = user(input);
                    b = b & Arrays.equals(out,user_out);
                    if(b==false) {
                        System.out.println("Test case");
                        System.out.println(Arrays.toString(input));
                        System.out.println("Your output  " + Arrays.toString(user_out));
                        System.out.println("Expected output  " + Arrays.toString(out));
                        return b;
                    }
                }
            }

        return b;
    }

    public static int[] user(int[] num)
    {
        return new asteroid_collision.Java.Solution().asteroidCollision(num);

    }
}