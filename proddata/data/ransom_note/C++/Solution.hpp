#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    bool canConstruct(string ransomNote, string magazine)
    {
        int n = ransomNote.size();
        int m = magazine.size();

        for (int i = 0; i < n; i++)
        {
            char c = ransomNote[i];
            int f = magazine.find(c);
            if (f == -1)
                return false;
            magazine.erase(magazine.begin() + f);
        }
        return true;
    }
};