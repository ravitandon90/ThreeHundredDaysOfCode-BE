package course_schedule.Java;

import java.io.*;
import java.util.*;
import java.util.stream.*;

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
        ;
    }

    static boolean drivercode() {
        String filePath = "src/course_schedule/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        boolean out;
        int input1 = 0;
        int[][] input2 = new int[0][0];
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {
                StringTokenizer st = new StringTokenizer(al.get(i));
                input1 = Integer.parseInt(st.nextToken().replace(",", ""));
                String s = st.nextToken();
                s=s.replace("],[", "||");
                s=s.replace("[", "");
                s=s.replace("]", "");
                s=s.replace(",", " ");
                StringTokenizer st2 = new StringTokenizer(s,"||");
                List<List<Integer>> al2 = new ArrayList<>();
                while(st2.hasMoreTokens())
                {
                    String a[] = st2.nextToken().split(" ");
                    int c[] = Arrays.stream(a).mapToInt(Integer :: parseInt).toArray();
                    List<Integer> list = Arrays.stream(c).boxed().collect(Collectors.toList());
                    al2.add(list);
                }
                input2 = new int[al2.size()][2];
                for(int j = 0;j<input2.length;j++)
                {
                    for(int k = 0;k<input2[j].length;k++)
                    {
                        input2[j][k] = al2.get(j).get(k);
                    }
                }
                
            } else {

                out = al.get(i).equals("true") ? true : false;

                boolean user_out = schedule(input1, input2);

                b = b & out == user_out ? true : false;
                if (b == false) {
                    System.out.println("Expected output  " + out);
                    System.out.println("Your output  " + user_out);
                    return b;
                }
            }
        }
        return b;
    }
    
    public static boolean schedule(int n , int b[][])
    {
        Solution sol = new Solution();
        return sol.canFinish(n, b);
    }
    
}
