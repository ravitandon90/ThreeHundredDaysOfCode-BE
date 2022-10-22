package pascals_triangle.Java;

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
        String filePath = "proddata/data/pascals_triangle/testcases.txt";
        List<String> al = method(filePath);

        List<List<Integer>> out = new ArrayList<>();
        int input1=0;

        boolean b = true;

        for (int i = 0; i< al.size();i++)
        {
            if(al.get(i).equals("input"))
            {
                input1 = Integer.parseInt(al.get(i+1));
                i++;
            }
            else if(al.get(i).equals("output") || !al.get(i).equals("check"))
            {
                List<Integer> al2 = new ArrayList<>();
                if(!al.get(i+1).equals("") && !al.get(i+1).equals("check"))
                {
                    String s= al.get(i+1);
                    StringTokenizer st = new StringTokenizer(s);
                    int n = st.countTokens();
                    for(int j = 0;j<n;j++)
                    {
                        al2.add(Integer.parseInt(st.nextElement().toString()));
                    }
                    out.add(al2);
                }
            }
            else if(al.get(i).equals("check"))
            {
                List<List<Integer>> user_out = subsets(input1);
                boolean chec = true;
                chec = chec & out.size()==user_out.size();

                out.sort(comparator);
                out.stream().map(l -> {
                    Collections.sort(l, Comparator.naturalOrder()); return l;
                }).collect(Collectors.toList());

                user_out.sort(comparator);
                user_out.stream().map(l -> {
                    Collections.sort(l, Comparator.naturalOrder()); return l;
                }).collect(Collectors.toList());

                for (int k=0;k<user_out.size();k++)
                {
                    chec = chec & out.get(k).equals(user_out.get(k));
                }
                b = b & chec & user_out.equals(out);
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("numRows = "+input1);
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }

                out.clear();
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

    public static List<List<Integer>> subsets(int input) {
        return new pascal_triangle.Java.Solution().generate(input);
    }
}
