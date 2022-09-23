#include <vector>
#include <numeric>
using namespace std;
class Solution
{
public:
    int maxArea(vector<int> &height)
    {
        int area = 0;
        int w, h;
        int i = 0, j = height.size() - 1;
        while (i < j)
        {
            w = j - i;
            if (height[i] > height[j])
            {
                h = height[j];
                j--;
            }
            else
            {
                h = height[i];
                i++;
            }
            area = h * w > area ? h * w : area;
        }
        return area;
    }
};