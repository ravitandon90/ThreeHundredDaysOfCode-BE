#include <vector>
#include <numeric>
using namespace std;
class Solution
{
public:
    int maxProfit(vector<int> &prices)
    {
        int mini = prices[0];
        int max_profit = 0;
        for (int i = 1; i < prices.size(); i++)
        {
            max_profit = max(prices[i] - mini, max_profit);
            if (prices[i] < mini)
                mini = prices[i];
        }
        return max_profit;
    }
};
