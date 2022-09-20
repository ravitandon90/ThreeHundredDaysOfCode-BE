#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    bool vowel(char c)
    {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
            return true;
        return false;
    }
    string toGoatLatin(string s)
    {
        stringstream str(s);
        string word, ans = "";
        int cnt_a = 1;
        while (str >> word)
        {
            if (vowel(word[0]))
            {

                word = word + "ma";
            }

            else
            {
                char ch = word[0];
                word = word.substr(1, word.size() - 1);
                word += ch;
                word += "ma";
            }
            int count_a = cnt_a;
            while (count_a--)
            {
                word += "a";
            }

            ans += word;
            ans += " ";
            cnt_a++;
        }
        ans = ans.substr(0, ans.size() - 1);
        return ans;
    }
};