#include <vector>
#include <numeric>
#include <algorithm>
using namespace std;
class Solution
{
public:
    int longestConsecutive(vector<int> &arr)
    {
        int n = arr.size();
        int c = 0;
        int max = 0;
        if (arr.size() == 0)
        {
            return 0;
        }
        sort(arr.begin(), arr.end());
        for (int i = 1; i < n; i++)
        {
            if ((arr[i] - arr[i - 1]) == 1)
            {
                c++;
                if (c > max)
                {
                    max = c;
                }
            }
            else if (arr[i] != arr[i - 1] && arr[i] - arr[i - 1] != 1)
                c = 0;
        }
        return max + 1;
    }
};