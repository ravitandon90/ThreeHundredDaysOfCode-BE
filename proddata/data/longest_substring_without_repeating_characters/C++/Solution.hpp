#include <iostream>
#include <string>
#include <set>

using namespace std;

class Solution
{
public:
    int lengthOfLongestSubstring(string s)
    {

        std::set<char> st;

        char a = 0;
        char b = 0;
        int maximum = 0;

        while (b < s.size())
        {
            if (st.find(s[b]) == st.end())
            {
                st.insert(s[b]);
                b++;

                int s = st.size();
                maximum = max(maximum, s);
            }
            else
            {
                st.erase(s[a]);
                a++;
            }
        }
        return maximum;
    }
};