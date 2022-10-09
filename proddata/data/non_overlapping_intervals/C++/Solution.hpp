#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;

class Solution {
public:
    static bool cmp(vector<int> &a,vector<int> &b){
        return a[1]<b[1];
    }
    int eraseOverlapIntervals(vector<vector<int>>& intervals) {
        sort(intervals.begin(),intervals.end(),cmp);
        vector<int> res;
        res=intervals[0];
        int ans=0;
        for(int i=1;i<intervals.size();i++){
            if(res[1]>intervals[i][0]){
                ans++;
            }
            else
                res=intervals[i];
        }
        return ans;
    }
};