package keys_and_rooms.Java;

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
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");
        
    }

    static boolean drivercode() {
        String filePath = "proddata/data/keys_and_rooms/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        boolean out ;
        List<List<Integer>> input = new ArrayList<>();
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                String s = al.get(i);
                s = s.replace("],[", "||");
                s = s.replace("[", "");
                s = s.replace("]]", " ");
                StringTokenizer st = new StringTokenizer(s,"||");
                input = new ArrayList<>();
                
                while(st.hasMoreTokens())
                {
                    String in = st.nextToken().trim();
                    if(in.equals(""))
                    {
                        List<Integer> al2 = new ArrayList<>();
                        input.add(al2);
                    }
                    else
                    {
                        List<Integer> al2 = new ArrayList<>();
                        StringTokenizer st2 = new StringTokenizer(in,",");
                        while(st2.hasMoreTokens())
                        {
                            al2.add(Integer.parseInt(st2.nextToken()));
                        }
                        input.add(al2);
                    }
                }


            } else {

                out = al.get(i).equals("true") ? true : false;

                boolean user_out = user(input);

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
    
    public static boolean user(List<List<Integer>> al)
    {
        keys_and_rooms.Java.Solution sol = new keys_and_rooms.Java.Solution();
        return sol.canVisitAllRooms(al);
    }
    
}


