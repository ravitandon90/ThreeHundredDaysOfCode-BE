package number_of_islands.Java;

import java.io.*;
import java.util.*;

public class Driver {
    private static List<String> method(String filePath) {
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                str = str.replace(", ","~").replace("\"","");
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
        String filePath = "proddata/data/number_of_islands/testcases.txt";
        List<String> al = method(filePath);
        int testcaes = Integer.parseInt(al.remove(0));
        char points[][] = new char[0][0];
        int out;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                String s = al.get(i);
                s = s.replace("],[", "||");
                s = s.replace("[[", "").replace("[]","").replace(","," ");
                s = s.replace("]]", "");
                StringTokenizer st = new StringTokenizer(s,"||");
                List<List<Character>> input1 = new ArrayList<>();
                while(st.hasMoreTokens())
                {
                    String in = st.nextToken().trim();
                    if(in.equals(""))
                    {
                        List<Character> al2 = new ArrayList<>();
                        input1.add(al2);
                    }
                    else
                    {
                        List<Character> al2 = new ArrayList<>();
                        StringTokenizer st2 = new StringTokenizer(in);
                        while(st2.hasMoreTokens())
                        {
                            al2.add(st2.nextToken().charAt(0));
                        }
                        input1.add(al2);
                    }
                }
                points = new char[input1.size()][];
                for (int j = 0; j < points.length; j++) {
                    points[j] = new char[input1.get(j).size()];
                }
                for(int j=0; j<input1.size(); j++){
                    for (int k = 0; k < input1.get(j).size(); k++) {
                        points[j][k] = input1.get(j).get(k);
                    }
                }
            } else {
                out = Integer.parseInt(al.get(i));
                int user_out = user(points);
                b = b & out == user_out;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("grid = "+ Arrays.deepToString(points));
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static int user(char nums[][])
    {
        return new number_of_islands.Java.Solution().numIslands(nums);
    }
}