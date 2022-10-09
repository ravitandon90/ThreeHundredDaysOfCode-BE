#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;

class Solution
{
    public:
        double maxAverageRatio(vector<vector < int>> &classes, int extraStudents)
        {

            using pi = pair<double, pair<int, int>> ;
            priority_queue<pair<double, pair<int, int>>> pq;

            for (int i = 0; i < classes.size(); i++)
            {
                int pass = classes[i][0];
                int total = classes[i][1];

                long double growth = (double)(pass + 1) / (total + 1) - (double)(pass) / (total);
                pq.push({ growth,
                    {
                        pass,
                        total
                    } });
            }

            while (extraStudents)
            {
                pi top = pq.top();
                pq.pop();
                extraStudents--;
                int pass = top.second.first;
                int total = top.second.second;
                pass++;
                total++;
                long double growth = (double)(pass + 1) / (total + 1) - (double)(pass) / (total);
                pq.push({ growth,
                    {
                        pass,
                        total
                    } });
            }

            double res = 0;
            while (!pq.empty())
            {
                res += (double)(pq.top().second.first) / (pq.top().second.second);
                pq.pop();
            }
            return (double) res / (classes.size());
        }
};