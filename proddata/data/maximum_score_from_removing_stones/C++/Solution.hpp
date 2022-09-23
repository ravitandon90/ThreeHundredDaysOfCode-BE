#include <iostream>
#include <string>
#include <queue>
using namespace std;

class Solution
{
public:
    int maximumScore(int a, int b, int c)
    {
        int points = 0;
        priority_queue<int> pq;
        pq.push(a);
        pq.push(b);
        pq.push(c);
        while (pq.size() > 1)
        {
            int first = pq.top();
            pq.pop();
            int sec = pq.top();
            pq.pop();
            first--;
            sec--;
            if (first != 0)
                pq.push(first);
            if (sec != 0)
                pq.push(sec);
            points++;
        }
        return points;
    }
};