package reconstruct_itinerary.Java;

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
        String filePath = "proddata/data/reconstruct_itinerary/testcases.txt";
        List<String> al = method(filePath);

        List<String> out = new ArrayList<>();
        List<List<String>> input1 = new ArrayList<>();

        boolean b = true;

        for (int i = 0; i< al.size();i++)
        {
            if(al.get(i).equals("input") )
            {
                while(!al.get(i).equals("output"))
                {
                    List<String> al2 = new ArrayList<>();
                    if(!al.get(i+1).equals(""))
                    {
                        String s= al.get(i+1);
                        StringTokenizer st = new StringTokenizer(s);
                        int n = st.countTokens();
                        for(int j = 0;j<n;j++)
                        {
                            al2.add((st.nextElement().toString()));
                        }
                        input1.add(al2);
                    }
                    i++;
                }
            }
            else if(al.get(i).equals("output") || !al.get(i).equals("check"))
            {


                if(!al.get(i+1).equals(""))
                {
                    String s= al.get(i);
                    StringTokenizer st = new StringTokenizer(s);
                    int n = st.countTokens();
                    for(int j = 0;j<n;j++)
                    {
                        out.add((st.nextElement().toString()));
                    }

                }
            }
            else if(al.get(i).equals("check"))
            {
                input1.remove(input1.size()-1);
                List<String> user_out = subsets(input1);

                boolean chec = true;
                chec = chec & out.size()==user_out.size();

                for (int k=0;k<user_out.size();k++)
                {
                    chec = chec & out.get(k).equals(user_out.get(k));
                }
                b = b & chec & user_out.equals(out);
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("accounts = "+input1);
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }
                input1.clear();
                out.clear();
            }
        }
        return b;
    }

    public static Comparator<List<String>> comparator = (x, y) -> {
        for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
            if (x.get(i) != y.get(i)) {
                return x.get(i).compareTo(y.get(i)) ;
            }
        }
        return Integer.compare(x.size(), y.size());
    };

    public static List<String> subsets(List<List<String>> input) {
        return new reconstruct_itinerary.Java.Solution().findItinerary(input);
    }
}
