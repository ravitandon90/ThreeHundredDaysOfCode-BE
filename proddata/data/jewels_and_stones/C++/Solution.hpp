#include <map>
using namespace std;

class Solution
{
public:
    int numJewelsInStones(string J, string S)
    {
        map<char, int> m;
        for (char n : S)
            m[n]++;

        int cnt = 0;
        for (char a : J)
        {
            cnt += m[a];
        }
        return cnt;
    }
};