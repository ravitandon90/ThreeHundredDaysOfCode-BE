class Solution
{
public:
    int findMaxConsecutiveOnes(vector<int> &nums)
    {
        int count1 = 0;
        int count = 0;
        int k = 0, k1 = 0;
        for (int i = 0; i < nums.size(); i++)
        {
            if (nums[i] == 1)
            {
                k1++;
                count1 = max(count1, k1);
                // cout<<"\n"<<k1;
            }
            else
                k1 = 0;
        
        }

        count1 = max(count1, k1);

        return count1;
    }
};
