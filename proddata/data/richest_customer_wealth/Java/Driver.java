package richest_customer_wealth.Java;

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
        String filePath = "proddata/data/richest_customer_wealth/testcases.txt";
        List<String> al = method(filePath);

        int out = 0;
        int input1[][] = new int[0][0];

        boolean b = true;

        for (int i = 0; i< al.size();i++)
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
            else if(al.get(i).equals("output") || !al.get(i).equals("check") )
            {
                out = Integer.parseInt(al.get(i));
            }
            else if(al.get(i).equals("check"))
            {
                int user_out = subsets(input1);

                b = b & out == user_out;
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("accounts = "+Arrays.deepToString(input1));
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
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

    public static int subsets(int input[][]) {
        return new richest_customer_wealth.Java.Solution().maximumWealth(input);
    }
}
