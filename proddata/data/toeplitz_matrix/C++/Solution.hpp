#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;
class Solution {
public:
    bool isToeplitzMatrix(vector<vector<int>>& mat)
    {
        for(int i=0;i<mat.size();i++)
        {
            for(int j=0;j<mat[0].size();j++)
            {
                if(j>0&&i>0&&mat[i][j]!=mat[i-1][j-1])
                    return false;
            }
        }
        return true;
    }
};