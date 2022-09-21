package palindrome_partitioning.Java;

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
        ;
    }

    static boolean drivercode() {
        String filePath = "proddata/data/palindrome_partitioning/testcases.txt";
        List<String> al = method(filePath);

        List<List<String>> out = new ArrayList<>();
        String input1 = null;

        boolean b = true;

        for (int i = 0; i< al.size();i++)
        {
            if(al.get(i).equals("input"))
            {
                input1 = al.get(i+1);
                i++;

            }
            else if(!al.get(i).equals("output") && !al.get(i).equals("check"))
            {
                List<String> al2 = new ArrayList<>();
                if(!al.get(i+1).equals(""))
                {
                    String s= al.get(i);
                    StringTokenizer st = new StringTokenizer(s);
                    int n = st.countTokens();
                    for(int j = 0;j<n;j++)
                    {
                        al2.add(st.nextElement().toString());
                    }
                    out.add(al2);
                }
            }
            else if(al.get(i).equals("check"))
            {
                List<List<String>> user_out = user_out(input1);

                boolean chec = true;
                chec = chec & out.size()==user_out.size();

                Collections.sort(out, new CustomComparator());
                Collections.sort(user_out, new CustomComparator());
                for (int k=0;k<user_out.size();k++)
                {
                    chec = chec & out.get(k).equals(user_out.get(k));
                }
                b = b & chec & user_out.equals(out);
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println(input1);
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }

                out.clear();
            }
        }
        return b;
    }

    static class CustomComparator implements Comparator<List<String>>
    {
        @Override
        public int compare(List<String> o1,
                           List<String> o2)
        {
            String firstString_o1 = o1.get(0);
            String firstString_o2 = o2.get(0);
            return firstString_o1.compareTo(firstString_o2);
        }
    }

    public static List<List<String>> user_out(String s) {
        palindrome_partitioning.Java.Solution sol = new palindrome_partitioning.Java.Solution();
        return sol.partition(s);
    }

}
