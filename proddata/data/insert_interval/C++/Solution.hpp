#include <vector>
#include <numeric>
#include <map>
#include <queue>

using namespace std;

class Solution {
public:
    vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
        vector<vector<int>> res;
        int n = intervals.size();
        bool added = false;
        
        for(int i=0;i<n;i++){
            if(intervals[i][1]<newInterval[0]){                
                res.push_back(intervals[i]);
            }
            else if(newInterval[1]<intervals[i][0]){
                res.push_back(newInterval);
                res.insert(res.end(),intervals.begin()+i,intervals.end());
                return res;

            }else{
               newInterval[0] = std::min(newInterval[0],intervals[i][0]);
                newInterval[1] = std::max(newInterval[1],intervals[i][1]);
            }
        }
        
        res.push_back(newInterval);
        return res;
    }
};