package add_string.Java;

public class solution_addString
{
    solution_addString(String s1, String s2) {
    }
    String solution_addString(String s1 , String s2) {
        String ans = add_binary(s1, s2);
        return ans;
    }
    public static String add_binary(String num1,String num2)
    {
        //write your code here
        StringBuilder result = new StringBuilder();
        int i = num1.length() -1 ;
        int j = num2.length() -1 ;
        int carry = 0;
        while(i>=0 || j>=0){
            int sum = carry;
            if(i>=0) sum+= num1.charAt(i--) - '0';
            if(j>=0) sum+= num2.charAt(j--) - '0';
            carry = sum > 9 ? 1:0;
            result.append(sum%10);
        }
        if(carry!=0) result.append(carry);
        return result.reverse().toString();
    }
}
