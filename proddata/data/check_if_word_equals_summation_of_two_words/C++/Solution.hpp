#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    bool isSumEqual(string firstWord, string secondWord, string targetWord)
    {
        string st1 = "", st2 = "", st3 = "";

        for (int i = 0; i < firstWord.size(); i++)
        {
            int x = firstWord[i] - 97;
            string s = to_string(x);
            st1 = st1 + s;
        }
        int y1 = stoi(st1);

        for (int i = 0; i < secondWord.size(); i++)
        {
            int x = secondWord[i] - 97;
            string s = to_string(x);
            st2 = st2 + s;
        }
        int y2 = stoi(st2);

        for (int i = 0; i < targetWord.size(); i++)
        {
            int x = targetWord[i] - 97;
            string s = to_string(x);
            st3 = st3 + s;
        }
        int y3 = stoi(st3);

        if (y1 + y2 == y3)
        {
            return true;
        }
        return false;
    }
};