package find_if_path_exists_in_graph.Java;

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
        String filePath = "proddata/data/find_if_path_exists_in_graph/testcases.txt";
        List<String> al = method(filePath);
        int testcaes = Integer.parseInt(al.remove(0));
        int n = 0;
        int[][] edges = new int[0][0];
        int source = 0;
        int dest = 0;
        boolean out ;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                StringTokenizer st3 = new StringTokenizer(al.get(i),"~");
                n = Integer.parseInt(st3.nextToken());
                String s = st3.nextToken();
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
                edges = new int[input1.size()][];
                for (int j = 0; j < edges.length; j++) {
                    edges[j] = new int[input1.get(j).size()];
                }
                for(int j=0; j<input1.size(); j++){
                    for (int k = 0; k < input1.get(j).size(); k++) {
                        edges[j][k] = input1.get(j).get(k);
                    }
                }
                source = Integer.parseInt(st3.nextToken());
                dest = Integer.parseInt(st3.nextToken());
            } else {
                out = al.get(i).equals("true") ? true : false;
                boolean user_out = user(n,edges,source,dest);
                b = b & out==user_out ? true : false;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("n = "+n);
                    System.out.println("edge = "+Arrays.deepToString(edges));
                    System.out.println("source = "+source);
                    System.out.println("Destination = "+dest);
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static boolean user(int n, int nums[][], int src , int dest)
    {
        return new find_if_path_exists_in_graph.Java.Solution().validPath(n, nums, src, dest);
    }
}