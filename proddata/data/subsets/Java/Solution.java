package subsets.Java;

import java.util.*;

public class Solution {
    List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<List<Integer>>();
        ArrayList<Integer> a = new ArrayList<>();
        printSub(a,0,nums.length,nums);
        return res;
    }

    public void printSub(ArrayList al, int i, int n, int nums[]){
        if(i==n){
            // System.out.println(al+" --> "+ res);
            res.add(al);
            // System.out.println(res+"->");
            return;
        }
        al.add(nums[i]);
        printSub(new ArrayList<>(al),i+1,n,nums);
        al.remove(al.size()-1);
        printSub(new ArrayList<>(al),i+1,n,nums);
    }
}