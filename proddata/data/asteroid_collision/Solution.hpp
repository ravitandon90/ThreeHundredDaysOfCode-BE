#include <bits/stdc++.h>
using namespace std;
class Solution
{
public:
    vector<int> asteroidCollision(vector<int> &asteroids)
    {
        vector<int> res;
        for (size_t i = 0; i < asteroids.size(); ++i)
        {
            if (asteroids[i] > 0 or res.empty() or res.back() < 0)
            {
                res.push_back(asteroids[i]);
            }
            else if (res.back() == -asteroids[i])
            {
                res.pop_back();
            }
            else if (res.back() < -asteroids[i])
            {
                res.pop_back();
                --i;
            }
        }
        return res;
    }
};
