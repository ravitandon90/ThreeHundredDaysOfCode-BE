#include <vector>
using namespace std;

class Solution
{
public:
    int countSubstrings(string s)
    {
        int k = 0;
        int count = 0;
        while (k < s.size())
        {
            int i = k - 1;
            int j = k;
            // even palindrome
            while (i >= 0 && j < s.size() && s[i] == s[j])
            {
                count++;
                i--;
                j++;
            }
            // odd palindrome
            i = k;
            j = k;
            while (i >= 0 && j < s.size() && s[i] == s[j])
            {
                count++;
                i--;
                j++;
            }
            k++;
        }
        return count;
    }
};