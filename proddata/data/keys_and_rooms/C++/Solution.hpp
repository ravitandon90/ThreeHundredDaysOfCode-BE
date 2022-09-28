#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;

class Solution
{
public:
    bool canVisitAllRooms(vector<vector<int>> &rooms)
    {
        int n = rooms.size();
        queue<int> q;
        vector<bool> visit(n, false);
        q.push(0);
        visit[0] = true;
        while (!q.empty())
        {
            int room = q.front();
            q.pop();
            for (auto room : rooms[room])
            {
                if (!visit[room])
                {
                    visit[room] = true;
                    q.push(room);
                }
            }
        }
        for (auto x : visit)
        {
            if (x == false)
            {
                return false;
            }
        }
        return true;
    }
};