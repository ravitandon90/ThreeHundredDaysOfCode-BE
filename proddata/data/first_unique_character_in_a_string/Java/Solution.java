package first_unique_character_in_a_string.Java;

import java.util.*;

public class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();

        for(int i = 0; i<s.length();i++)
        {
            char ch = s.charAt(i);
            if(hm.containsKey(ch))
            {
                int val = hm.get(ch);
                hm.put(ch,val+1);
            }
            else
            {
                hm.put(ch,1);
            }
        }
        for(int i = 0; i<s.length();i++)
        {
            char ch = s.charAt(i);
            int cnt = hm.get(ch);
            if(cnt==1)
            {
                return i;
            }
        }
        return -1;
    }
}
