package add_to_array_form_of_Integer.Java;

import java.util.*;

public class solution_add_array {

    public solution_add_array(int num[] , int k) {

    }
    List<Integer> solution_addString(int num[] , int k) {
        return addToArrayForm(num, k);
    }

    public static List<Integer> addToArrayForm(int[] num, int k)
    {
        StringBuilder an = new StringBuilder();
        for(int i:num)
            an.append(Integer.toString(i));
        String str1 = an.toString();
        String str2 = Integer.toString(k);
        int diff = Math.abs(str1.length()-str2.length());
        if(str1.length()<str2.length()){
            for(int i=0;i<diff;i++)
                str1="0"+str1;
        }
        else if(str1.length()>str2.length()){
            for(int j=0;j<diff;j++)
                str2="0"+str2;
        }
        int carry=0;
        String ans= "";
        for(int y=str2.length()-1;y>=0;y--){
            int sum = Character.getNumericValue(str1.charAt(y))+Character.getNumericValue(str2.charAt(y))+carry;
            if(sum>=10){
                carry=1;
                ans=Integer.toString(sum%10)+ans;
            }
            else{
                carry=0;
                ans=Integer.toString(sum)+ans;
            }
        }
        if(carry==1)
            ans="1"+ans;
        ArrayList<Integer> list = new ArrayList<>();
        for(int z=0;z<ans.length();z++)
            list.add(Character.getNumericValue(ans.charAt(z)));
        return list;
    }
}