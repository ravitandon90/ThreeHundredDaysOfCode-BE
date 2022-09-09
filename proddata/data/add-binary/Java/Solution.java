package add_binary.Java;
public class Solution {
    Solution(String s1, String s2) {
    }
    String solution_adBinary(String s1 , String s2) {

        String ans = add_binary(s1, s2);
        return ans;
    }
    public static String add_binary(String a,String b)
    {
        //write your code here
        StringBuilder stringBuilder = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i) - '0';
            if (j >= 0) sum += b.charAt(j) - '0';
            stringBuilder.append(sum % 2);
            carry = sum / 2; //formulate carry
            i--;  j--; // decrement both pointers
        }
        if (carry != 0) stringBuilder.append(carry);
        return stringBuilder.reverse().toString();
    }
}

