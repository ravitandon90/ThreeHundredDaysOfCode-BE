#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;
class Solution
{
public:
    int findJudge(int n, vector<vector<int>> &trust)
    {
        vector<vector<int>> trust_matrix(n, vector<int>(n, 0));
        int row = trust.size();
        for (int i = 0; i < row; i++)
        {
            int val1 = trust[i][0];
            int val2 = trust[i][1];
            trust_matrix[val1 - 1][val2 - 1] = 1;
        }
        stack<int> s;
        for (int i = 0; i < n; i++)
        {
            s.push(i);
        }
        while (s.size() >= 2)
        {
            int i = s.top();
            s.pop();
            int j = s.top();
            s.pop();

            if (trust_matrix[i][j] == 1)
            {
                s.push(j);
            }
            else
            {
                s.push(i);
            }
        }

        int pot = s.top();
        s.pop();
        int flag = pot + 1;
        for (int i = 0; i < n; i++)
        {
            if (i != pot)
            {
                if (trust_matrix[i][pot] == 0 || trust_matrix[pot][i] == 1)
                {
                    flag = -1;
                }
            }
        }
        return flag;
    }
};