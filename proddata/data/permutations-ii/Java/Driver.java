package permutations_ii.Java;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Driver {
    private static List<String> method(String filePath) {
        StringBuilder builder = new StringBuilder();
        ArrayList<String> al = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {

            String str;
            while ((str = buffer.readLine()) != null) {
                str.trim();
                str = str.replace("nums = ","");
                str = str.replace("],[","||");
                str = str.replace("]","");
                str = str.replace("[", "");
                str = str.replace(",", " ");


                al.add(str);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
//        System.out.println(al);
        // Returning a string arraylist
        return al;
    }

    public static void main(String[] args) {
        System.out.println(drivercode() ? "Accepted" : "Wrong Answer");
        ;
    }

    static boolean drivercode() {
        String filePath = "src/permutations_ii/testcases.txt";
        List<String> al = method(filePath);

        int testcases = Integer.parseInt(al.remove(0));
        int input1[] = new int[0];
        List<List<Integer>> output;
        boolean b = true;
        for (int i = 0; i < 2 * testcases; i++) {
            if (i % 2 == 0) {

                String in[] = al.get(i).split(" ");
                input1 = Arrays.stream(in).mapToInt(Integer :: parseInt).toArray();

            } else {

                StringTokenizer st = new StringTokenizer(al.get(i),"||");
                output = new ArrayList<>();
                int n = st.countTokens();
                for (int j = 1; j <= n; j++) {
                    String out[] = st.nextElement().toString().split(" ");
                    int o[] = Arrays.stream(out).mapToInt(Integer :: parseInt).toArray();
                    List<Integer> al1 = Arrays.stream(o).boxed().collect(Collectors.toList());
                    output.add(al1);
                }
                List<List<Integer>> user_out = user_out(input1);
                for (List<Integer> p:output) {
                    Collections.sort(p);
                }
                for (List<Integer> p:user_out) {
                    Collections.sort(p);
                }
                boolean chec = true;
                for (int k=0;k<user_out.size();k++)
                {
                    chec = chec & output.get(i).equals(user_out.get(i));
                }
                b = b & chec;
                if (b == false) {
                    System.out.println("Expected output  " + output);
                    System.out.println("Your output  " + user_out);
                    return b;
                }
            }
        }
        return b;
    }

    public static List<List<Integer>> user_out(int[] nums) {
        Solution sol = new Solution();
        return sol.permuteUnique(nums);
    }
}
