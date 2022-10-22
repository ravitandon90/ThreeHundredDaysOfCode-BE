package word_search.Java;

import javax.security.auth.callback.CallbackHandler;
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
                str = str.replace(", ","~").replace("\"","");
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
        String filePath = "proddata/data/word_search/testcases.txt";
        List<String> al = method(filePath);
        int testcaes = Integer.parseInt(al.remove(0));
        char points[][] = new char[0][0];
        String word = null;
        boolean out;
        boolean b = true;
        for (int i = 0; i < al.size(); i++) {
            if (i % 2 == 0) {
                StringTokenizer st3 = new StringTokenizer(al.get(i),"~");
                String s = st3.nextToken();
                s = s.replace("],[", "||");
                s = s.replace("[[", "").replace("[]","").replace(","," ");
                s = s.replace("]]", "");
                StringTokenizer st = new StringTokenizer(s,"||");
                List<List<Character>> input1 = new ArrayList<>();
                while(st.hasMoreTokens())
                {
                    String in = st.nextToken().trim();
                    if(in.equals(""))
                    {
                        List<Character> al2 = new ArrayList<>();
                        input1.add(al2);
                    }
                    else
                    {
                        List<Character> al2 = new ArrayList<>();
                        StringTokenizer st2 = new StringTokenizer(in);
                        while(st2.hasMoreTokens())
                        {
                            al2.add((st2.nextToken().charAt(0)));
                        }
                        input1.add(al2);
                    }
                }
                points = new char[input1.size()][];
                for (int j = 0; j < points.length; j++) {
                    points[j] = new char[input1.get(j).size()];
                }
                for(int j=0; j<input1.size(); j++){
                    for (int k = 0; k < input1.get(j).size(); k++) {
                        points[j][k] = input1.get(j).get(k);
                    }
                }
                word = st3.nextToken();
            } else {
                out = al.get(i).equals("true") ? true : false;
                boolean user_out = user(points,word);
                b = b & out==(user_out);
                if (b == false) {
                    System.out.println("Test case");
                    System.out.println("board = "+ Arrays.deepToString(points));
                    System.out.println("word = "+word);
                    System.out.println("Your output  " + user_out);
                    System.out.println("Expected output  " + out);
                    return b;
                }
            }
        }
        return b;
    }

    public static boolean user(char matrix[][], String word)
    {
        return new word_Search.Java.Solution().exist(matrix, word);
    }
}