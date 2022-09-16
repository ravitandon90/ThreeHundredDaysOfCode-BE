using namespace std;

class Solution
{
public:
    int countSubstrings(string s, string t)
    {
        unordered_map<string, int> mp;
        for (int i = 0; i < t.size(); i++)
        {
            string st = "";
            for (int j = i; j < t.size(); j++)
            {
                st += t[j];
                mp[st]++;
            }
        }

        int ans = 0;
        for (int i = 0; i < s.size(); i++)
        {
            string st = "";
            for (int j = i; j < s.size(); j++)
            {
                st += s[j];

                for (int k = 0; k < st.size(); k++)
                {

                    char r = st[k];
                    for (int q = 0; q < 26; q++)
                    {
                        if (st[k] == q + 'a')
                        {

                            continue;
                        }

                        st[k] = q + 'a';
                        if (mp.find(st) != mp.end())
                        {

                            ans += mp[st];
                        }
                        st[k] = r;
                    }
                }
            }
        }

        return ans;
    }
};