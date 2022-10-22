package merge_intervals.Java;

import java.io.*;
import java.util.*;

public class Driver {
    private static List<String> method(String filePath) {
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                str = str.replace(", ","~");
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
        String filePath = "proddata/data/merge_intervals/testcases.txt";
        List<String> al = method(filePath);
        int testcaes = Integer.parseInt(al.remove(0));
        int points[][] = new int[0][0];
        int out[][];
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                String s = al.get(i);
                s = s.replace("],[", "||");
                s = s.replace("[[", "").replace("[]","").replace(","," ");
                s = s.replace("]]", "");
                StringTokenizer st = new StringTokenizer(s,"||");
                List<List<Integer>> input1 = new ArrayList<>();
                while(st.hasMoreTokens())
                {
                    String in = st.nextToken().trim();
                    if(in.equals(""))
                    {
                        List<Integer> al2 = new ArrayList<>();
                        input1.add(al2);
                    }
                    else
                    {
                        List<Integer> al2 = new ArrayList<>();
                        StringTokenizer st2 = new StringTokenizer(in);
                        while(st2.hasMoreTokens())
                        {
                            al2.add(Integer.parseInt(st2.nextToken()));
                        }
                        input1.add(al2);
                    }
                }
                points = new int[input1.size()][];
                for (int j = 0; j < points.length; j++) {
                    points[j] = new int[input1.get(j).size()];
                }
                for(int j=0; j<input1.size(); j++){
                    for (int k = 0; k < input1.get(j).size(); k++) {
                        points[j][k] = input1.get(j).get(k);
                    }
                }
            } else {
                String s = al.get(i);
                s = s.replace("],[", "||");
                s = s.replace("[[", "").replace("[]","").replace(","," ");
                s = s.replace("]]", "");
                StringTokenizer st = new StringTokenizer(s,"||");
                List<List<Integer>> input1 = new ArrayList<>();
                while(st.hasMoreTokens())
                {
                    String in = st.nextToken().trim();
                    if(in.equals(""))
                    {
                        List<Integer> al2 = new ArrayList<>();
                        input1.add(al2);
                    }
                    else
                    {
                        List<Integer> al2 = new ArrayList<>();
                        StringTokenizer st2 = new StringTokenizer(in);
                        while(st2.hasMoreTokens())
                        {
                            al2.add(Integer.parseInt(st2.nextToken()));
                        }
                        input1.add(al2);
                    }
                }
                out = new int[input1.size()][];
                for (int j = 0; j < out.length; j++) {
                    out[j] = new int[input1.get(j).size()];
                }
                for(int j=0; j<input1.size(); j++){
                    for (int k = 0; k < input1.get(j).size(); k++) {
                        out[j][k] = input1.get(j).get(k);
                    }
                }
                int[][] user_out = user(points);
                b = b & Arrays.deepEquals(out,user_out);
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("intervals = "+ Arrays.deepToString(points));
                    System.out.println("Your output  " + Arrays.deepToString(user_out));
                    System.out.println("Expected output  " + Arrays.deepToString(out));
                    return b;
                }
            }
        }
        return b;
    }

    public static int[][] user(int nums[][])
    {
        return new merge_intervals.Java.Solution().merge(nums);
    }
}