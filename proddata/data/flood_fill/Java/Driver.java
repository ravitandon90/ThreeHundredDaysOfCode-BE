package flood_fill.Java;

import org.springframework.aop.target.LazyInitTargetSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Driver
{
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
        String filePath = "proddata/data/flood_fill/testcases.txt";
        List<String> al = method(filePath);

        int[][] out = new int[0][0];
        int input1[][] = null;
        int sr = 0 , sc = 0 , color = 0;
        boolean b = true;
        for (int i = 0; i<al.size();i++)
        {
            if(al.get(i).equals("input"))
            {
                List<List<Integer>> al1 = new ArrayList<>();
                while(!al.get(i).equals("output"))
                {
                    List<Integer> al2 = new ArrayList<>();
                    if(!al.get(i+1).equals("output"))
                    {
                        String s= al.get(i+1);
                        StringTokenizer st = new StringTokenizer(s);
                        int n = st.countTokens();
                        for(int j = 0;j<n;j++)
                        {
                            al2.add(Integer.parseInt(st.nextElement().toString()));
                        }
                        al1.add(al2);
                    }
                    i+=1;
                }
                List<Integer> in = al1.remove(al1.size()-1);
                sr = in.get(0);
                sc = in.get(1);
                color = in.get(2);
                input1 = new int[al1.size()][];
                for (int j = 0; j < input1.length; j++) {
                    input1[j] = new int[al1.get(j).size()];
                }
                for(int j=0; j<al1.size(); j++){
                    for (int k = 0; k < al1.get(j).size(); k++) {
                        input1[j][k] = al1.get(j).get(k);
                    }
                }
            }
            else if(al.get(i).equals("output") || !al.get(i).equals("check"))
            {
                List<List<Integer>> output = new ArrayList<>();
                List<Integer> al2;

                while(!al.get(i).equals("check"))
                {
                    al2 = new ArrayList<>();
//                    if(!al.get(i).equals("output"))
                    {
                        String s= al.get(i);
                        StringTokenizer st = new StringTokenizer(s);
                        int n = st.countTokens();
                        for(int j = 0;j<n;j++)
                        {
                            al2.add(Integer.parseInt(st.nextElement().toString()));
                        }
                        output.add(al2);
                    }
                    i+=1;
                }
                out = new int[output.size()][];
                for (int j = 0; j < out.length; j++) {
                    out[j] = new int[output.get(j).size()];
                }
                for(int j=0; j<output.size(); j++){
                    for (int k = 0; k < output.get(j).size(); k++) {
                        out[j][k] = output.get(j).get(k);
                    }
                }
                i--;
            }
            else if(al.get(i).equals("check"))
            {

                int[][] user_out = subsets(input1, sr , sc, color);

                b = b & Arrays.deepEquals(out,user_out);
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("image = "+ Arrays.deepToString(input1));
                    System.out.println("sr = " + sr);
                    System.out.println("sc = " + sc);
                    System.out.println("color = " + color);
                    System.out.println("Your output  " + Arrays.deepToString(user_out));
                    System.out.println("Expected output  " + Arrays.deepToString(out));
                    return b;
                }
            }
        }
        return b;
    }

    public static Comparator<List<Integer>> comparator = (x, y) -> {
        for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
            if (x.get(i) != y.get(i)) {
                return x.get(i) - y.get(i);
            }
        }
        return Integer.compare(x.size(), y.size());
    };

    public static int[][] subsets(int input[][], int a, int b , int c) {
        return new flood_fill.Java.Solution().floodFill(input, a, b, c);
    }
}
