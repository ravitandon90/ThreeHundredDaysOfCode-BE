#include <vector>
#include <numeric>
#include <map>
#include <queue>
using namespace std;
class Solution
{
public:
    bool checkWord(int index, int i, int j, vector<vector<char>> &board, string &word, int &m, int &n)
    {
        if (index == word.size())
        {
            return true;
        }
        if (i < 0 || j < 0 || i == m || j == n || board[i][j] != word[index] || board[i][j] == '!')
            return false;

        char c = board[i][j];
        board[i][j] = '!';
        bool up = checkWord(index + 1, i - 1, j, board, word, m, n);
        bool down = checkWord(index + 1, i + 1, j, board, word, m, n);
        bool left = checkWord(index + 1, i, j - 1, board, word, m, n);
        bool right = checkWord(index + 1, i, j + 1, board, word, m, n);
        board[i][j] = c;

        return left || right || up || down;
    }
    bool exist(vector<vector<char>> &board, string word)
    {
        if (word.size() == 0)
            return false;
        int m = board.size(), n = board[0].size();
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (checkWord(0, i, j, board, word, m, n))
                    return true;
            }
        }
        return false;
    }
};