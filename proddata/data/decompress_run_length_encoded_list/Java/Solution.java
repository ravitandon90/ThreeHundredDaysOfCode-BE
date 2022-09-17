package decompress_run_length_encoded_list.Java;

public class Solution {
    public int[] decompressRLElist(int[] nums) {
        int size = 0, n = nums.length, k=0;
        
        for(int i=0;i<n;i+=2)
            size += nums[i];
        
        int[] res = new int[size];
        
        for(int i=0;i<n;i+=2) {
            for(int j=0;j<nums[i];j++)
                res[k++] = nums[i+1];
        }
        
        return res;
    }
}
