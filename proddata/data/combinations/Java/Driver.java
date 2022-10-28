package combinations.Java;

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
        String filePath = "proddata/data/combinations/testcases.txt";
        List<String> al = method(filePath);

        List<List<Integer>> out = new ArrayList<>();
        int input1[] = new int[0];
        int a = 0 , c = 0;
        boolean b = true;

        for (int i = 0; i< al.size();i++)
        {
            if(al.get(i).equals("input") )
            {
                input1 = Arrays.stream(al.get(i+1).split(" ")).mapToInt(Integer::parseInt).toArray();
                a = input1[0];
                c = input1[1];
            }
            else if(al.get(i).equals("output") || !al.get(i).equals("check"))
            {

                List<Integer> al2 = new ArrayList<>();
                if(!al.get(i+1).equals("output") && !al.get(i+1).equals("check"))
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
                List<List<Integer>> user_out = subsets(a,c);
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
                    System.out.println("n = "+a);
                    System.out.println("k = "+c);
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
                return x.get(i) - (y.get(i)) ;
            }
        }
        return Integer.compare(x.size(), y.size());
    };

    public static List<List<Integer>> subsets(int input1, int input2) {
        return new combination.Java.Solution().combine(input1, input2);
    }
}
