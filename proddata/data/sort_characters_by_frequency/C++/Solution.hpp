#include <iostream>
#include <string>
using namespace std;

class Solution
{
public:
    string frequencySort(string s)
    {
        unordered_map<char, int> hash;
        for (auto ch : s)
            hash[ch]++;

        sort(s.begin(), s.end(), [&](char lhs, char rhs)
             {
            if (hash[lhs] != hash[rhs]) return hash[lhs] > hash[rhs];
            else return lhs < rhs; });

        return s;
    }
};