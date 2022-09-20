package permutations_ii.Java;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Driver {
    private static List<String> method(String filePath) {
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                str.trim();
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
        String filePath = "proddata/data/permutations_ii/testcases.txt";
        List<String> al = method(filePath);

        List<List<Integer>> out = new ArrayList<>();
        int[] input1 = new int[0];

        boolean b = true;

        for (int i = 0; i< al.size();i++)
        {
            if(al.get(i).equals("input"))
            {
                input1 = Arrays.stream(al.get(i+1).split(" ")).mapToInt(Integer :: parseInt).toArray();
                i++;
            }
            else if(!al.get(i).equals("output") && !al.get(i).equals("check"))
            {
                List<Integer> al2 = new ArrayList<>();
                if(!al.get(i+1).equals(""))
                {
                    String s= al.get(i);
                    int arr[] = Arrays.stream(s.split(" ")).mapToInt(Integer :: parseInt).toArray();
                    al2 = Arrays.stream(arr).boxed().collect(Collectors.toList());
                    out.add(al2);
                }
            }
            else if(al.get(i).equals("check"))
            {
                List<List<Integer>> user_out = user_out(input1);

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
                    System.out.println(Arrays.toString(input1));
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }

                out.clear();
            }
        }
        return b;
    }

    static class CustomComparator implements Comparator<List<Integer>>
    {
        @Override
        public int compare(List<Integer> o1,
                           List<Integer> o2)
        {
            String firstString_o1 = String.valueOf(o1.get(0));
            String firstString_o2 = String.valueOf(o2.get(0));
            return firstString_o1.compareTo(firstString_o2);
        }
    }

    public static List<List<Integer>> user_out(int[] nums) {
        permutations_ii.Java.Solution sol = new permutations_ii.Java.Solution();
        return sol.permuteUnique(nums);
    }
}
