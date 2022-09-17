package intersection_of_two_arrays.Java;

import java.util.*;

public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> h1=new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            h1.add(nums1[i]);
        }
        HashSet<Integer> h2=new HashSet<>();
        for(int i=0;i<nums2.length;i++){
            if(h1.contains(nums2[i])){
                h2.add(nums2[i]);
                h1.remove(nums2[i]);
            }
        }
        int[] arr=new int[h2.size()];
        int index=0;
        for(int i: h2){
            arr[index++]=i;
        }
        return arr;
    }
}
