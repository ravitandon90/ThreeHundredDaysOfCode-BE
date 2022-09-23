#include <vector>
#include <numeric>
using namespace std;
class Solution {
public:
    bool canPlaceFlowers(vector<int>& nums, int n) {
        int no=0;
        if(nums.size()==1 && nums[0]==0)
            no=1;
        for(int i=0;i<nums.size();++i){
            if(i>0 && i<nums.size()-1 && nums[i]==0 && nums[i+1]==0 && nums[i-1]==0){
                nums[i]=1;
                no++;
            }
            else if(i==0 && i<nums.size()-1 && nums[i]==0 && nums[i+1]==0){
                nums[i]=1;
                no++;
            }
            else if(i==nums.size()-1 && i>0 && nums[i]==0 && nums[i-1]==0){
                nums[i]=1;
                no++;
            }
        }
        if(no>=n)
            return true;
        return false;
    }
};