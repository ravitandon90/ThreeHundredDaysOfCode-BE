#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;

class Solution
{
    public:
        vector<int> spiralOrder(vector<vector < int>> &matrix)
        {

            vector<int> ans;
            int row = matrix.size();
            int col = matrix[0].size();

            int firstRow = 0, lastRow = row - 1;
            int firstCol = 0, lastCol = col - 1;

            int count = 0, n = row * col;

            while (count < n)
            {

                for (int i = firstCol; count < n && i <= lastCol; i++)
                {
                    ans.push_back(matrix[firstRow][i]);
                    count++;
                }
                firstRow++;

                for (int i = firstRow; count < n && i <= lastRow; i++)
                {
                    ans.push_back(matrix[i][lastCol]);
                    count++;
                }
                lastCol--;

                for (int i = lastCol; count < n && i >= firstCol; i--)
                {
                    ans.push_back(matrix[lastRow][i]);
                    count++;
                }
                lastRow--;

                for (int i = lastRow; count < n && i >= firstRow; i--)
                {
                    ans.push_back(matrix[i][firstCol]);
                    count++;
                }
                firstCol++;
            }

            return ans;
        }
};