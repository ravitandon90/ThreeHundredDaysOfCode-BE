#include <vector>
using namespace std;

typedef vector<int> v1i;
class Solution
{
public:
    int goNext(int index)
    {
        return index - (index & (-index));
    }
    int goParent(int index)
    {
        return index + (index & (-index));
    }
    int getSum(vector<int> &FenTree, int index)
    {
        int sum = 0;
        while (index > 0)
        {
            sum += FenTree[index];
            index = goNext(index);
        }
        return sum;
    }
    void update(vector<int> &FenTree, int index, int val, int MAX)
    {
        while (index <= MAX)
        {
            FenTree[index] += val;
            index = goParent(index);
        }
    }
    int numTeams(vector<int> &rating)
    {
        int n = rating.size();
        int mx = *max_element(rating.begin(), rating.end());
        v1i FT1(mx + 1, 0);
        v1i FT2(mx + 1, 0);
        v1i lsmall(n), rsmall(n), lgreat(n), rgreat(n);
        for (int i = 0; i < n; i++)
        {
            // for the left of array
            lsmall[i] = getSum(FT1, rating[i] - 1);
            lgreat[i] = getSum(FT1, mx) - getSum(FT1, rating[i]);
            update(FT1, rating[i], 1, mx);
            // for the right of array
            rsmall[n - 1 - i] = getSum(FT2, rating[n - 1 - i] - 1);
            rgreat[n - 1 - i] = getSum(FT2, mx) - getSum(FT2, rating[n - 1 - i]);
            update(FT2, rating[n - 1 - i], 1, mx);
        }
        int ans = 0;
        for (int i = 0; i < n; i++)
        {
            ans += lsmall[i] * rgreat[i] + lgreat[i] * rsmall[i];
        }
        return ans;
    }
};